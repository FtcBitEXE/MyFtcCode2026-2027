package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.Global;

import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.List;

public class LimelightScript {

    private static Global global;

    public static void LimelightInit(Limelight3A limelight) {
        limelight.setPollRateHz(100);
        limelight.pipelineSwitch(0);
        limelight.start();
    }

    public static void LimelightTune(double speedMultiplayer, boolean limelightEnabled, boolean autoAlign, double TX_TARGET, double TX_DEADBAND, double AUTO_ALIGN_KP, double AUTO_ALIGN_KD, double AUTO_ALIGN_MAX_TURN, double lastTXError, double filteredDistanceMeters) {

        global.filteredDistanceMeters = filteredDistanceMeters;

        global.limelightEnabled = limelightEnabled;

        global.autoAlign = autoAlign;

        global.TX_TARGET = TX_TARGET;

        global.TX_DEADBAND = TX_DEADBAND;

        global.AUTO_ALIGN_KP = AUTO_ALIGN_KP;

        global.AUTO_ALIGN_KD = AUTO_ALIGN_KD;

        global.AUTO_ALIGN_MAX_TURN = AUTO_ALIGN_MAX_TURN;

        global.lastTXError = lastTXError;

        global.speedMultiplyer = speedMultiplayer;

        global.CAMERA_HEIGHT_IN = 0;

        global.TAG_HEIGHT_IN = 0;

        global.CAMERA_PITCH_DEG = 0;



    }

    public static void LimelightRead( Limelight3A limelight, boolean llvaid) {
        if (global.limelightEnabled) {
            LLResult result = limelight.getLatestResult();
            if (result != null && result.isValid()) {

                global.best = getBestTag(result, global.lockedTagId);

                if (global.best != null) {
                    global.tagId = global.best.getFiducialId();
                    global.lockedTagId = global.tagId;

                    global.tx = global.best.getTargetXDegrees();
                    global.ty = global.best.getTargetYDegrees();

                    Pose3D robotPoseTargetSpace = global.best.getRobotPoseTargetSpace();
                    if (robotPoseTargetSpace != null) {
                        double px = robotPoseTargetSpace.getPosition().x;
                        double py = robotPoseTargetSpace.getPosition().y;
                        double pz = robotPoseTargetSpace.getPosition().z;

                        global.rawDistanceMeters = Math.sqrt(px * px + py * py + pz * pz);

                    }
                    llvaid = true;

                    global.llvaid = llvaid;
                } else {
                    global.tx = result.getTx();
                    global.ty = result.getTy();
                    llvaid = true;

                    global.llvaid = llvaid;
                }




            }
        }

        if (!llvaid) {
            global.lockedTagId = -1;
        }

        if (llvaid && Global.rawDistanceMeters > 0) {
            updateDistanceFilter(global.rawDistanceMeters, dt, tagId)
        }
    }

    private static void updateDistanceFilter(double rawDistanceMeters, double dt, int tagId) {
        distanceTimer.reset();

        if (tagId != lastDistanceTagId) {
            resetDistanceFilter();
            lastDistanceTagId = tagId;
        }

        if (distanceMedianCount < DISTANCE_MEDIAN_SIZE) {
            distanceMedianCount++;
        }

        double median = median(distanceMedianBuffer, distanceMedianCount);

        if (global.filteredDistanceMeters <= 0) {
            global.filteredDistanceMeters = median;
            return;
        }

        if (Math.abs(median - global.filteredDistanceMeters) > MAX_DISTANCE_JUMP_METERS) {
            return;
        }

        double alpha = 1.0 - Math.exp(-Math.max(dt, 0.001) / DISTANCE_FILTER_TAU);
        global.filteredDistanceMeters += alpha * (median - global.filteredDistanceMeters);
    }

    private double median(double[] values, int count) {
        if (count <= 0) return -1;

        double[] copy = Arrays.copyOf(values, count);
        Arrays.sort(copy);

        if (count % 2 == 1) {
            return copy[count / 2]
        } else {
            return (copy[count / 2 - 1] + copy[count / 2]) / 2.0;
        }
    }

    private void resetDistanceFilter() {
        global.distanceMedianCount = 0;
        global.distanceMedianIndex = 0;
        global.filteredDistanceMeters = -1;
        global.distanceTimer.reset();
        global.lastDistanceTagId = -1;
    }

    private static LLResultTypes.FiducialResult getBestTag(LLResult result, int LockedTagID) {
        if (result == null) {
            return null;
        }

        List<LLResultTypes.FiducialResult> tags = result.getFiducialResults();

        if (tags == null || tags.isEmpty()) {
            return null;
        }

        if (LockedTagID != -1) {
            for (LLResultTypes.FiducialResult tag : tags) {
                if (tag.getFiducialId() == LockedTagID) {
                    return tag;
                }
            }
        }

        LLResultTypes.FiducialResult best = null;
        double bestScore = Double.MAX_VALUE;

        for (LLResultTypes.FiducialResult tag : tags) {
            double txOffset = Math.abs(tag.getTargetXDegrees() - global.TX_TARGET);
            double score = txOffset;

            if (score < bestScore) {
                bestScore = score;
                best = tag;
            }
        }

        return best;
    }

    private static double getDistanceInches(double ty) {
        double angleDeg = global.CAMERA_PITCH_DEG + ty;
        double heightDiff = global.TAG_HEIGHT_IN - global.CAMERA_HEIGHT_IN;

        if (Math.abs(angleDeg) < 0.1) {
            return -1;
        }

        if (Math.abs(heightDiff) < 0.1) {
            return -1;
        }

        double angleRad = Math.toRadians(angleDeg);
        double distance = heightDiff / Math.tan(angleRad);

        return Math.abs(distance);
    }

    private double calculateAutoAlignTurn(double tx, double dt) {
        double error = tx - global.TX_TARGET;

        if (Math.abs(error) < global.TX_DEADBAND) {
            global.lastTXError = error;
            return 0;
        }

        double derivitive = (error - global.lastTXError) / Math.max(dt, 0.001);
        double turn = (global.AUTO_ALIGN_KP * error) + (global.AUTO_ALIGN_KD * derivitive);

        turn = Math.max(-global.AUTO_ALIGN_MAX_TURN, Math.min(global.AUTO_ALIGN_MAX_TURN, turn));
        global.lastTXError = error;
        return turn;

    }



}
