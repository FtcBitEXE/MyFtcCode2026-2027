package org.firstinspires.ftc.teamcode.KrishTestCode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

import java.util.Arrays;
import java.util.List;

@TeleOp(name = "LimeTeleOp5", group = "Linear OpMode")
public class LimeTeleOp5 extends LinearOpMode {

    // Motors
    //DcMotorEx Fleft, Bleft, Fright, Bright;
    //DcMotorEx SpinDexer, Top_Launch, Bottom_Launch, Intake;

    // Servos
    //Servo LeftKicker, RightKicker;

    // Limelight
    Limelight3A limelight;

    // Variables
    double speedMultiplier = 0.75;

    // Launcher
    double LAUNCH_POWER = 1.0;
    boolean launcherOn = false;

    // Kicker
    double KICK_READY_POS = 0.25;
    double KICK_UP_POS = 0.5;
    double BUMP_DURATION = 150;
    enum KickerState { READY, BUMPING }
    KickerState kickerState = KickerState.READY;
    ElapsedTime kickerTimer = new ElapsedTime();

    // Limelight State
    boolean limelightEnabled = true;
    boolean autoAlign = false;

    // Limelight Auto-Align Tuning
    double TX_TARGET = 0.0;
    double TX_DEADBAND = 0.05;
    double AUTO_ALIGN_KP = 0.02;
    double AUTO_ALIGN_KD = 0.002;
    double AUTO_ALIGN_MAX_TURN = 0.35;
    double lastTxError = 0;

    // Distance filtering
    static final int DISTANCE_MEDIAN_SIZE = 7;
    double[] distanceMedianBuffer = new double[DISTANCE_MEDIAN_SIZE];
    int distanceMedianCount = 0;
    int distanceMedianIndex = 0;

    double filteredDistanceMeters = -1;

    ElapsedTime distanceTimer = new ElapsedTime();

    static final double DISTANCE_FILTER_TAU = 0.12;      // seconds, lower = faster response
    static final double MAX_DISTANCE_JUMP_METERS = 0.45; // ~18 inches, outlier gate
    static final double DISTANCE_TIMEOUT_SEC = 0.5;      // keep last value this long

    int lockedTagId = -1;
    int lastDistanceTagId = -1;

    // Edge Detection
    boolean last2A = false;
    boolean last2B = false;
    boolean last2Y = false;

    ElapsedTime loopTimer = new ElapsedTime();

    @Override
    public void runOpMode() {/*
        // Hardware Mapping
        Fleft = hardwareMap.get(DcMotorEx.class, "Fleft");
        if (Fleft == null) {
            throw new NullPointerException("DcMotorEx named 'Fleft' not found in hardwareMap");
        }
        Fright = hardwareMap.get(DcMotorEx.class, "Fright");
        if (Fright == null) {
            throw new NullPointerException("DcMotorEx named 'Fright' not found in hardwareMap");
        }
        Bleft = hardwareMap.get(DcMotorEx.class, "Bleft");
        if (Bleft == null) {
            throw new NullPointerException("DcMotorEx named 'Bleft' not found in hardwareMap");
        }
        Bright = hardwareMap.get(DcMotorEx.class, "Bright");
        if (Bright == null) {
            throw new NullPointerException("DcMotorEx named 'Bright' not found in hardwareMap");
        }

        Intake = hardwareMap.get(DcMotorEx.class, "Intake");
        if (Intake == null) {
            throw new NullPointerException("DcMotorEx named 'Intake' not found in hardwareMap");
        }
        SpinDexer = hardwareMap.get(DcMotorEx.class, "SpinDexer");
        if (SpinDexer == null) {
            throw new NullPointerException("DcMotorEx named 'SpinDexer' not found in hardwareMap");
        }
        Top_Launch = hardwareMap.get(DcMotorEx.class, "Top_Launch");
        if (Top_Launch == null) {
            throw new NullPointerException("DcMotorEx named 'Top Launch' not found in hardwareMap");
        }
        Bottom_Launch = hardwareMap.get(DcMotorEx.class, "Bottom_Launch");
        if (Bottom_Launch == null) {
            throw new NullPointerException("DcMotorEx named 'Bottom_Launch' not found in hardwareMap");
        }

        LeftKicker = hardwareMap.get(Servo.class, "LK");
        if (LeftKicker == null) {
            throw new NullPointerException("Servo named 'LK' not found in hardwareMap");
        }
        RightKicker = hardwareMap.get(Servo.class, "RK");
        if (RightKicker == null) {
            throw new NullPointerException("Servo named 'RK' not found in hardwareMap");
        }
        */

        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        if (limelight == null) {
            throw new NullPointerException("Limelight named 'limelight' not found in hardwareMap");
        }

        // Motor Directions
        /*
        Fleft.setDirection(DcMotorSimple.Direction.REVERSE);
        Bleft.setDirection(DcMotorSimple.Direction.REVERSE);
        SpinDexer.setDirection(DcMotorSimple.Direction.REVERSE);
        Top_Launch.setDirection(DcMotorSimple.Direction.FORWARD);
        Bottom_Launch.setDirection(DcMotorSimple.Direction.REVERSE);

        LeftKicker.setDirection(Servo.Direction.FORWARD);
        RightKicker.setDirection(Servo.Direction.REVERSE);

        // Motor Modes
        SpinDexer.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Top_Launch.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Bottom_Launch.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        */

        // Limelight Init
        limelight.setPollRateHz(100);
        limelight.pipelineSwitch(0);
        limelight.start();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        loopTimer.reset();
        distanceTimer.reset();

        while (opModeIsActive()) {
            double dt = loopTimer.seconds();
            loopTimer.reset();

            // LIMELIGHT READ
            boolean llValid = false;
            double tx = 0, ty = 0;
            double rawDistanceMeters = -1;
            double distanceInches = -1;
            int tagId = -1;

            if (limelightEnabled) {
                LLResult result = limelight.getLatestResult();
                if (result != null && result.isValid()) {
                    LLResultTypes.FiducialResult best = getBestTag(result, lockedTagId);

                    if (best != null) {
                        tagId = best.getFiducialId();
                        lockedTagId = tagId;

                        tx = best.getTargetXDegrees();
                        ty = best.getTargetYDegrees();

                        // Use full 3D distance from robot pose in target space.
                        // This is much more reliable than only using .y.
                        Pose3D robotPoseTargetSpace = best.getRobotPoseTargetSpace();
                        if (robotPoseTargetSpace != null) {
                            double px = robotPoseTargetSpace.getPosition().x;
                            double py = robotPoseTargetSpace.getPosition().y;
                            double pz = robotPoseTargetSpace.getPosition().z;

                            rawDistanceMeters = Math.sqrt(px * px + py * py + pz * pz);
                        }

                        llValid = true;
                    } else {
                        // Fallback to overall target data if no fiducial array
                        tx = result.getTx();
                        ty = result.getTy();
                        llValid = true;
                    }
                }
            }

            if (!llValid) {
                lockedTagId = -1;
            }

            if (llValid && rawDistanceMeters > 0) {
                updateDistanceFilter(rawDistanceMeters, dt, tagId);
                distanceInches = filteredDistanceMeters * 39.3701;
            } else {
                // Keep last distance briefly, then reset so stale data does not linger.
                if (distanceTimer.seconds() > DISTANCE_TIMEOUT_SEC) {
                    resetDistanceFilter();
                }

                distanceInches = filteredDistanceMeters > 0
                        ? filteredDistanceMeters * 39.3701
                        : -1;
            }

            //if (autoAlign && llValid) {
            //    rot = calculateAutoAlignTurn(tx, dt);
            //} else if (autoAlign && !llValid) {
            //    rot = 0;
            //}

            // LIMELIGHT TOGGLE
            if (gamepad2.b && !last2B) {
                limelightEnabled = !limelightEnabled;

                if (limelightEnabled) {
                    limelight.start();
                    limelight.pipelineSwitch(0);
                } else {
                    limelight.stop();
                    autoAlign = false;
                    lockedTagId = -1;
                    resetDistanceFilter();
                }
            }
            last2B = gamepad2.b;

            // AUTO-ALIGN TOGGLE
            if (gamepad2.a && !last2A) {
                autoAlign = !autoAlign;
                if (!autoAlign) lastTxError = 0;
            }
            last2A = gamepad2.a;

            // TELEMETRY
            telemetry.addData("Limelight", limelightEnabled ? "ON" : "OFF");
            telemetry.addData("Auto Align", autoAlign ? "ON" : "OFF");
            telemetry.addData("LL Valid", llValid);
            telemetry.addData("Locked Tag ID", lockedTagId);
            telemetry.addData("Tag ID", tagId);
            telemetry.addData("tx (deg)", "%.2f", tx);
            telemetry.addData("ty (deg)", "%.2f", ty);

            telemetry.addData("Raw Dist (m)", rawDistanceMeters > 0
                    ? String.format("%.3f", rawDistanceMeters)
                    : "---");

            telemetry.addData("Filtered Dist (m)", filteredDistanceMeters > 0
                    ? String.format("%.3f", filteredDistanceMeters)
                    : "---");

            telemetry.addData("Filtered Dist (in)", distanceInches > 0
                    ? String.format("%.2f", distanceInches)
                    : "---");

            telemetry.addData("Launcher", launcherOn ? "ON" : "OFF");
            telemetry.addData("Launch Power", LAUNCH_POWER);
            telemetry.update();
        }
    }

    // Selects the best AprilTag to target. Prefers the locked tag if still visible.
    private LLResultTypes.FiducialResult getBestTag(LLResult result, int lockedTagId) {
        List<LLResultTypes.FiducialResult> tags = result.getFiducialResults();
        if (tags == null || tags.isEmpty()) return null;

        // Keep the same tag when possible so distance does not jump between tags.
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
            double score = txOffset;

            if (score < bestScore) {
                bestScore = score;
                best = tag;
            }
        }

        return best;
    }

    // Median pre-filter + exponential smoothing + outlier rejection.
    private void updateDistanceFilter(double rawDistanceMeters, double dt, int tagId) {
        distanceTimer.reset();

        // If the tag changed, start the filter fresh.
        if (tagId != lastDistanceTagId) {
            resetDistanceFilter();
            lastDistanceTagId = tagId;
        }

        distanceMedianBuffer[distanceMedianIndex] = rawDistanceMeters;
        distanceMedianIndex = (distanceMedianIndex + 1) % DISTANCE_MEDIAN_SIZE;

        if (distanceMedianCount < DISTANCE_MEDIAN_SIZE) {
            distanceMedianCount++;
        }

        double median = median(distanceMedianBuffer, distanceMedianCount);

        if (filteredDistanceMeters <= 0) {
            filteredDistanceMeters = median;
            return;
        }

        // Ignore sudden impossible jumps unless they persist.
        if (Math.abs(median - filteredDistanceMeters) > MAX_DISTANCE_JUMP_METERS) {
            return;
        }

        double alpha = 1.0 - Math.exp(-Math.max(dt, 0.001) / DISTANCE_FILTER_TAU);
        filteredDistanceMeters += alpha * (median - filteredDistanceMeters);
    }

    private double median(double[] values, int count) {
        if (count <= 0) return -1;

        double[] copy = Arrays.copyOf(values, count);
        Arrays.sort(copy);

        if (count % 2 == 1) {
            return copy[count / 2];
        } else {
            return (copy[count / 2 - 1] + copy[count / 2]) / 2.0;
        }
    }

    private void resetDistanceFilter() {
        distanceMedianCount = 0;
        distanceMedianIndex = 0;
        filteredDistanceMeters = -1;
        distanceTimer.reset();
        lastDistanceTagId = -1;
    }

    // Calculates the turn power to center the AprilTag.
    private double calculateAutoAlignTurn(double tx, double dt) {
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

/*
    // KICKER
    private void handleKicker() {
        switch (kickerState) {
            case READY:
                if (gamepad2.right_trigger > 0.5) {
                    fireOneShot();
                } else {
                    LeftKicker.setPosition(KICK_READY_POS);
                    RightKicker.setPosition(KICK_READY_POS);
                }
                break;
            case BUMPING:
                if (kickerTimer.milliseconds() > BUMP_DURATION) {
                    kickerState = KickerState.READY;
                }
                break;
        }
    }
/*
    private void fireOneShot() {
        LeftKicker.setPosition(KICK_UP_POS);
        RightKicker.setPosition(KICK_UP_POS);
        kickerTimer.reset();
        kickerState = KickerState.BUMPING;
    }*/
}