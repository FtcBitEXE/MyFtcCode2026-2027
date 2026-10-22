package org.firstinspires.ftc.teamcode.Devices;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.linearOpMode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.Global;

import java.util.Arrays;
import java.util.List;

public class LimeLight {

    private static Global global;
    // Tuning
    public static double TX_TARGET = 0.0;
    public static double TX_DEADBAND = 0.05;
    public static double AUTO_ALIGN_KP = 0.02;
    public static double AUTO_ALIGN_KD = 0.002;
    public static double AUTO_ALIGN_MAX_TURN = 0.35;

    public static final int DISTANCE_MEDIAN_SIZE = 7;
    public static final double DISTANCE_FILTER_TAU = 0.12;
    public static final double MAX_DISTANCE_JUMP_METERS = 0.45;
    public static final double DISTANCE_TIMEOUT_SEC = 0.5;

    // Latest cached values
    public static boolean valid = false;
    public static double tx = 0;
    public static double ty = 0;
    public static double rawDistanceMeters = -1;
    public static double distanceInches = -1;
    public static int tagId = -1;

    // Internal state
    private static boolean enabled = false;
    private static int lockedTagId = -1;
    private static int lastDistanceTagId = -1;
    private static double lastTxError = 0;

    private static final double[] distanceMedianBuffer = new double[DISTANCE_MEDIAN_SIZE];
    private static int distanceMedianCount = 0;
    private static int distanceMedianIndex = 0;
    private static double filteredDistanceMeters = -1;

    private static final ElapsedTime distanceTimer = new ElapsedTime();



    public static void init(HardwareMap hardwareMap) {
        Global.limelight = hardwareMap.get(Limelight3A.class, "limelight");
        if (Global.limelight == null) {
            throw new NullPointerException("Limelight named 'limelight' not found in hardwareMap");
        }
        Global.limelight.setPollRateHz(100);
        Global.limelight.pipelineSwitch(0);
        Global.limelight.start();
        enabled = true;
        distanceTimer.reset();
    }

    public static void start() {
        if (Global.limelight == null) return;
        Global.limelight.start();
        Global.limelight.pipelineSwitch(0);
        enabled = true;
    }

    public static void stop() {
        if (Global.limelight != null) Global.limelight.stop();
        enabled = false;
        lockedTagId = -1;
        lastTxError = 0;
        resetDistanceFilter();
    }

    public static boolean isEnabled() {
        return enabled;
    }

    // Per-loop update
    public static void update(double dt) {
        valid = false;
        tx = 0;
        ty = 0;
        rawDistanceMeters = -1;
        distanceInches = -1;
        tagId = -1;


        if (enabled && Global.limelight != null) {
            LLResult result = Global.limelight.getLatestResult();
            if (result != null && result.isValid()) {
                LLResultTypes.FiducialResult best = getBestTag(result, lockedTagId);

                if (best != null) {
                    tagId = best.getFiducialId();
                    lockedTagId = tagId;

                    tx = best.getTargetXDegrees();
                    ty = best.getTargetYDegrees();

                    Pose3D pose = best.getRobotPoseTargetSpace();
                    if (pose != null) {
                        double px = pose.getPosition().x;
                        double py = pose.getPosition().y;
                        double pz = pose.getPosition().z;
                        rawDistanceMeters = Math.sqrt(px * px + py * py + pz * pz);
                    }
                    valid = true;
                } else {
                    // Fallback to overall target data if no fiducial array
                    tx = result.getTx();
                    ty = result.getTy();
                    valid = true;
                }
            }
        }

        if (!valid) {
            lockedTagId = -1;
        }

        if (valid && rawDistanceMeters > 0) {
            updateDistanceFilter(rawDistanceMeters, dt, tagId);
            distanceInches = filteredDistanceMeters * 39.3701;
        } else {
            if (distanceTimer.seconds() > DISTANCE_TIMEOUT_SEC) {
                resetDistanceFilter();
            }
            distanceInches = filteredDistanceMeters > 0
                    ? filteredDistanceMeters * 39.3701
                    : -1;
        }
    }

    // Accessors
    public static double getFilteredDistanceMeters() { return filteredDistanceMeters; }
    public static double getDistanceInches()         { return distanceInches; }
    public static int    getLockedTagId()            { return lockedTagId; }

    // Auto-align
    public static double calculateAutoAlignTurn(double dt) {
        double error = tx - TX_TARGET;

        if (Math.abs(error) < TX_DEADBAND) {
            lastTxError = error;
            return 0;
        }

        double derivative = (error - lastTxError) / Math.max(dt, 0.001);
        double turn = (AUTO_ALIGN_KP * error) + (AUTO_ALIGN_KD * derivative);
        turn = Math.max(-AUTO_ALIGN_MAX_TURN, Math.min(AUTO_ALIGN_MAX_TURN, turn));

        lastTxError = error;
        return turn;
    }

    public static void resetLastTxError() {
        lastTxError = 0;
    }

    // Internals
    private static LLResultTypes.FiducialResult getBestTag(LLResult result, int lockedTagId) {
        List<LLResultTypes.FiducialResult> tags = result.getFiducialResults();
        if (tags == null || tags.isEmpty()) return null;

        if (lockedTagId != -1) {
            for (LLResultTypes.FiducialResult tag : tags) {
                if (tag.getFiducialId() == lockedTagId) {
                    return tag;
                }
            }
        }

        LLResultTypes.FiducialResult best = null;
        double bestScore = Double.MAX_VALUE;
        for (LLResultTypes.FiducialResult tag : tags) {
            double txOffset = Math.abs(tag.getTargetXDegrees() - TX_TARGET);
            if (txOffset < bestScore) {
                bestScore = txOffset;
                best = tag;
            }
        }
        return best;
    }

    public static void updateDistanceFilter(double rawDistanceMeters, double dt, int tagId) {
        distanceTimer.reset();

        if (tagId != lastDistanceTagId) {
            resetDistanceFilter();
            lastDistanceTagId = tagId;
        }

        distanceMedianBuffer[distanceMedianIndex] = rawDistanceMeters;
        distanceMedianIndex = (distanceMedianIndex + 1) % DISTANCE_MEDIAN_SIZE;
        if (distanceMedianCount < DISTANCE_MEDIAN_SIZE) distanceMedianCount++;

        double median = median(distanceMedianBuffer, distanceMedianCount);

        if (filteredDistanceMeters <= 0) {
            filteredDistanceMeters = median;
            return;
        }

        if (Math.abs(median - filteredDistanceMeters) > MAX_DISTANCE_JUMP_METERS) {
            return;
        }

        double alpha = 1.0 - Math.exp(-Math.max(dt, 0.001) / DISTANCE_FILTER_TAU);
        filteredDistanceMeters += alpha * (median - filteredDistanceMeters);
    }

    public static double median(double[] values, int count) {
        if (count <= 0) return -1;
        double[] copy = Arrays.copyOf(values, count);
        Arrays.sort(copy);
        if (count % 2 == 1) return copy[count / 2];
        return (copy[count / 2 - 1] + copy[count / 2]) / 2.0;
    }

    public static void resetDistanceFilter() {
        distanceMedianCount = 0;
        distanceMedianIndex = 0;
        filteredDistanceMeters = -1;
        distanceTimer.reset();
        lastDistanceTagId = -1;
    }

    public static void wUintilLLscan(int LLtag){
        int tagId2 = getLLtagID();
        while (linearOpMode.opModeIsActive() && !(tagId2 == LLtag)) {
            tagId2 = getLLtagID();
        }
    }

    public static int getLLtagID(){
        return tagId;
    }

}