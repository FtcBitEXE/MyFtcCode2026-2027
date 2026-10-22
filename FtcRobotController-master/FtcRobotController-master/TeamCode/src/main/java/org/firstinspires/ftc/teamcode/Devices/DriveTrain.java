package org.firstinspires.ftc.teamcode.Devices;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.linearOpMode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.Global;

import java.util.Arrays;
import java.util.List;

public class DriveTrain {

    private static Global global;

    //initializes movement that uses velocity
    //only use if using velocity based movement
    public static void posBasedMovementInit() {
        global.Fleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        global.Fleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        global.Fleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        global.Fright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        global.Fright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        global.Fright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        global.Bleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        global.Bleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        global.Bleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);




        global.Bright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        global.Bright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        global.Bright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }

    // drives using the setVelocity Function
    //vel must be a positive number
    // if F_B_L_R is 1 it will go forward
    // if F_B_L_R is 2 it will go backward
    // if F_B_L_R is 3 it will go left
    // if F_B_L_R is 4 it will go right
    public static void drivePosBased(int F_B_L_R, double vel) {
        if (F_B_L_R == 1) {
            global.Fleft.setVelocity(vel);
            global.Fright.setVelocity(vel);
            global.Bleft.setVelocity(vel);
            global.Bright.setVelocity(vel);
        } else if (F_B_L_R == 2) {
            global.Fleft.setVelocity(-vel);
            global.Fright.setVelocity(-vel);
            global.Bleft.setVelocity(-vel);
            global.Bright.setVelocity(-vel);
        } else if (F_B_L_R == 3) {
            global.Fleft.setVelocity(-vel);
            global.Fright.setVelocity(vel);
            global.Bleft.setVelocity(vel);
            global.Bright.setVelocity(-vel);
        } else if (F_B_L_R == 4) {
            global.Fleft.setVelocity(vel);
            global.Fright.setVelocity(-vel);
            global.Bleft.setVelocity(-vel);
            global.Bright.setVelocity(vel);
        }
    }

    // drives using the setPower Function
    // if F_B_L_R is 1 it will go forward
    // if F_B_L_R is 2 it will go backward
    // if F_B_L_R is 3 it will go left
    // if F_B_L_R is 4 it will go right
    public static void drivePwrBased(int F_B_L_R, double pwr) {

        if (F_B_L_R == 1) {
            global.Fleft.setPower(pwr);
            global.Fright.setPower(pwr);
            global.Bleft.setPower(pwr);
            global.Bright.setPower(pwr);
        } else if (F_B_L_R == 2) {
            global.Fleft.setPower(-pwr);
            global.Fright.setPower(-pwr);
            global.Bleft.setPower(-pwr);
            global.Bright.setPower(-pwr);
        } else if (F_B_L_R == 3) {
            global.Fleft.setPower(-pwr);
            global.Fright.setPower(pwr);
            global.Bleft.setPower(pwr);
            global.Bright.setPower(-pwr);
        } else if (F_B_L_R == 4) {
            global.Fleft.setPower(pwr);
            global.Fright.setPower(-pwr);
            global.Bleft.setPower(-pwr);
            global.Bright.setPower(pwr);
        }

    }

    public static void RotatePwrBased(boolean L_R, double pwr) {
        if (L_R) {
            global.Fleft.setPower(-pwr);
            global.Fright.setPower(pwr);
            global.Bleft.setPower(-pwr);
            global.Bright.setPower(pwr);
        } else if (!L_R) {
            global.Fleft.setPower(pwr);
            global.Fright.setPower(-pwr);
            global.Bleft.setPower(pwr);
            global.Bright.setPower(-pwr);
        }
    }

    public static void RotatePosBased(boolean L_R, double vel) {
        if (L_R) {
            global.Fleft.setVelocity(-vel);
            global.Fright.setVelocity(vel);
            global.Bleft.setVelocity(-vel);
            global.Bright.setVelocity(vel);
        } else if (!L_R) {
            global.Fleft.setVelocity(vel);
            global.Fright.setVelocity(-vel);
            global.Bleft.setVelocity(vel);
            global.Bright.setVelocity(-vel);
        }
    }



//
//      |---|  /---/  |---====--\  |----------|   /---------|  |---|   |---|         |---====--\        /--------\           /------\
//      |   | /   /   |   |  |  |  |---    ---|  |          /  |   |   |   |         |   |  |  |       /-----     \         / ,----, \
//      |   |/   /    |   '--'  /      |  |      |    -----    |   |---|   |         |   '--'  /           _/    _/        |  |    |  |
//      |       |     |       \        |  |       \        \   |           |         |       \          _/    _/           |  |    |  |
//      |   |\   \    |   |\   \       |  |        -----    |  |   |---|   |         |   |\   \        /   _/              |  |    |  |
//      |   | \   \   |   | \   \  |---    ---|  /          |  |   |   |   |  |---|  |   | \   \       |   -------|  |---|  \ '----' /
//      |---|  \---\  |---|  \---\ |----------|  |---------/   |---|   |---|  |---|  |---|  \---\      |----------|  |---|   \------/
//      FTC Season 2 (for me) :) :) :)
//      KRISH Methods here:
    public static void driveTeleOp(double y, double  x, double rx) {
        global.Fleft.setPower(y + x + rx);
        global.Fright.setPower(y - x - rx);
        global.Bleft.setPower(y - x + rx);
        global.Bright.setPower(y + x - rx);
        if (gamepad1.right_trigger > 0.2) {
            global.Intake.setPower(0.5); // INTAKE IN
        } else if (gamepad1.left_trigger > 0.2) {
            global.Intake.setPower(-0.75); // INTAKE OUT
        } else {
           global.Intake.setPower(0);
        }
    }

    public static void driveTeleOpLimelight(double y, double  x, double rx) {
        /*global.Fleft.setPower(y + x + rx);
        global.Fright.setPower(y - x - rx);
        global.Bleft.setPower(y - x + rx);
        global.Bright.setPower(y + x - rx);
        if (gamepad1.right_trigger > 0.2) {
            global.Intake.setPower(0.5); // INTAKE IN
        } else if (gamepad1.left_trigger > 0.2) {
            global.Intake.setPower(-0.75); // INTAKE OUT
        } else {
            global.Intake.setPower(0);
        }*/

        double dt = global.loopTimer.seconds();
        global.loopTimer.reset();

        // LIMELIGHT READ
        boolean llValid = false;
        double tx = 0, ty = 0;
        double rawDistanceMeters = -1;
        double distanceInches = -1;
        int tagId = -1;

        if (global.limelightEnabled) {
            LLResult result = global.limelight.getLatestResult();
            if (result != null && result.isValid()) {
                LLResultTypes.FiducialResult best = getBestTag(result, global.lockedTagId);

                if (best != null) {
                    tagId = best.getFiducialId();
                    global.lockedTagId = tagId;

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
            global.lockedTagId = -1;
        }

        if (llValid && rawDistanceMeters > 0) {
            updateDistanceFilter(rawDistanceMeters, dt, tagId);
            distanceInches = global.filteredDistanceMeters * 39.3701;
        } else {
            // Keep last distance briefly, then reset so stale data does not linger.
            if (global.distanceTimer.seconds() > global.DISTANCE_TIMEOUT_SEC) {
                resetDistanceFilter();
            }

            distanceInches = global.filteredDistanceMeters > 0
                    ? global.filteredDistanceMeters * 39.3701
                    : -1;
        }

        //if (autoAlign && llValid) {
        //    rot = calculateAutoAlignTurn(tx, dt);
        //} else if (autoAlign && !llValid) {
        //    rot = 0;
        //}

        // LIMELIGHT TOGGLE
        if (gamepad2.b && !global.last2B) {
            global.limelightEnabled = !global.limelightEnabled;

            if (global. limelightEnabled) {
                global.limelight.start();
                global.limelight.pipelineSwitch(0);
            } else {
                global.limelight.stop();
                global.autoAlign = false;
                global.lockedTagId = -1;
                resetDistanceFilter();
            }
        }
        global.last2B = gamepad2.b;

        // AUTO-ALIGN TOGGLE
        if (gamepad2.a && !global.last2A) {
            global.autoAlign = !global.autoAlign;
            if (!global.autoAlign) global.lastTxError = 0;
        }
        global.last2A = gamepad2.a;

        // TELEMETRY
        telemetry.addData("Limelight", global.limelightEnabled ? "ON" : "OFF");
        telemetry.addData("Auto Align", global.autoAlign ? "ON" : "OFF");
        telemetry.addData("LL Valid", llValid);
        telemetry.addData("Locked Tag ID", global.lockedTagId);
        telemetry.addData("Tag ID", tagId);
        telemetry.addData("tx (deg)", "%.2f", tx);
        telemetry.addData("ty (deg)", "%.2f", ty);

        telemetry.addData("Raw Dist (m)", rawDistanceMeters > 0
                ? String.format("%.3f", rawDistanceMeters)
                : "---");

        telemetry.addData("Filtered Dist (m)", global.filteredDistanceMeters > 0
                ? String.format("%.3f", global.filteredDistanceMeters)
                : "---");

        telemetry.addData("Filtered Dist (in)", distanceInches > 0
                ? String.format("%.2f", distanceInches)
                : "---");
    }
    // Selects the best AprilTag to target. Prefers the locked tag if still visible.
    public static LLResultTypes.FiducialResult getBestTag(LLResult result, int lockedTagId) {
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
            double txOffset = Math.abs(tag.getTargetXDegrees() - global.TX_TARGET);
            double score = txOffset;

            if (score < bestScore) {
                bestScore = score;
                best = tag;
            }
        }

        return best;
    }

    // Median pre-filter + exponential smoothing + outlier rejection.
    public static void updateDistanceFilter(double rawDistanceMeters, double dt, int tagId) {
        global.distanceTimer.reset();

        // If the tag changed, start the filter fresh.
        if (tagId != global.lastDistanceTagId) {
            resetDistanceFilter();
            global.lastDistanceTagId = tagId;
        }

        global.distanceMedianBuffer[global.distanceMedianIndex] = rawDistanceMeters;
        global.distanceMedianIndex = (global.distanceMedianIndex + 1) % global.DISTANCE_MEDIAN_SIZE;

        if (global.distanceMedianCount < global.DISTANCE_MEDIAN_SIZE) {
            global.distanceMedianCount++;
        }

        double median = median(global.distanceMedianBuffer, global.distanceMedianCount);

        if (global.filteredDistanceMeters <= 0) {
            global.filteredDistanceMeters = median;
            return;
        }

        // Ignore sudden impossible jumps unless they persist.
        if (Math.abs(median - global.filteredDistanceMeters) > global.MAX_DISTANCE_JUMP_METERS) {
            return;
        }

        double alpha = 1.0 - Math.exp(-Math.max(dt, 0.001) / global.DISTANCE_FILTER_TAU);
        global.filteredDistanceMeters += alpha * (median - global.filteredDistanceMeters);
    }

    public static double median(double[] values, int count) {
        if (count <= 0) return -1;

        double[] copy = Arrays.copyOf(values, count);
        Arrays.sort(copy);

        if (count % 2 == 1) {
            return copy[count / 2];
        } else {
            return (copy[count / 2 - 1] + copy[count / 2]) / 2.0;
        }
    }

    public static void resetDistanceFilter() {
        global.distanceMedianCount = 0;
        global.distanceMedianIndex = 0;
        global.filteredDistanceMeters = -1;
        global.distanceTimer.reset();
        global.lastDistanceTagId = -1;
    }

    // Calculates the turn power to center the AprilTag.
    public static double calculateAutoAlignTurn(double tx, double dt) {
        double error = tx - global.TX_TARGET;

        if (Math.abs(error) < global.TX_DEADBAND) {
            global.lastTxError = error;
            return 0;
        }

        double derivative = (error - global.lastTxError) / Math.max(dt, 0.001);
        double turn = (global.AUTO_ALIGN_KP * error) + (global.AUTO_ALIGN_KD * derivative);

        turn = Math.max(-global.AUTO_ALIGN_MAX_TURN, Math.min(global.AUTO_ALIGN_MAX_TURN, turn));
        global.lastTxError = error;
        return turn;
    }
    public static void motorInit(HardwareMap hardwareMap) {
        //Connects the variables to the hardware map
        global.Fleft = hardwareMap.get(DcMotorEx.class,"Fleft");
        global.Fright = hardwareMap.get(DcMotorEx.class,"Fright");
        global.Bleft = hardwareMap.get(DcMotorEx.class,"Bleft");
        global.Bright = hardwareMap.get(DcMotorEx.class,"Bright");
        //Sets direction to reverse (important when using mecanum wheels)
        global.Fright.setDirection(DcMotorSimple.Direction.REVERSE);
        global.Bright.setDirection(DcMotorSimple.Direction.REVERSE);
        //Motor stuff ig (thanks to CJ :))))
        for (DcMotorEx m : new DcMotorEx[]{global.Fleft, global.Fright, global.Bleft, global.Bright}) {
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        //Limelight
        global.limelight = hardwareMap.get(Limelight3A.class, "limelight");
        if (global.limelight == null) {
            throw new NullPointerException("Limelight named 'limelight' not found in hardwareMap");
        }
        // IMU init
        global.imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot orientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
        global.imu.initialize(new IMU.Parameters(orientation));
    }
    //-strafe is strafeLeft, strafe is strafeRight
    public static void strafe(double power, int ticks){
        global.Fleft.setTargetPosition(ticks);
        global.Fright.setTargetPosition(-ticks);
        global.Bleft.setTargetPosition(-ticks);
        global.Bright.setTargetPosition(ticks);
        global.Fleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        global.Fright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        global.Bleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        global.Bright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        global.Fleft.setPower(power);
        global.Fright.setPower(power);
        global.Bleft.setPower(power);
        global.Bright.setPower(power);
    }
    // forward is forward, -forward is backwards
    public static void forward(double power, int centimeters){
        int ticks = (int) (centimeters * Math.round(global.T_TO_D));

        global.Fleft.setTargetPosition(ticks);
        global.Fright.setTargetPosition(ticks);
        global.Bleft.setTargetPosition(ticks);
        global.Bright.setTargetPosition(ticks);
        global.Fleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        global.Fright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        global.Bleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        global.Bright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        global.Fleft.setPower(power);
        global.Fright.setPower(power);
        global.Bleft.setPower(power);
        global.Bright.setPower(power);
    }
    //diagonalRight can go northeast (diagonalRight) and southwest (-diagonalRight)
    public static void diagonalRight(double power, int ticks){
        global.Fleft.setTargetPosition(ticks);
        global.Fright.setTargetPosition(ticks);
        global.Bleft.setTargetPosition(ticks);
        global.Bright.setTargetPosition(ticks);
        global.Fleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        global.Fright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        global.Bleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        global.Bright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        global.Fleft.setPower(power);
        global.Bright.setPower(power);
    }
    //diagonalLeft can go northwest (diagonalLeft) and southeast (-diagonalLeft)
    public static void diagonalLeft(double power, int ticks){
        global.Fleft.setTargetPosition(ticks);
        global.Fright.setTargetPosition(ticks);
        global.Bleft.setTargetPosition(ticks);
        global.Bright.setTargetPosition(ticks);
        global.Fleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        global.Fright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        global.Bleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        global.Bright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        global.Fright.setPower(power);
        global.Bleft.setPower(power);
    }
    //turn is turning right, -turn is turning left
    public static void turn(double power, int ticks){
        global.Fleft.setTargetPosition(-ticks);
        global.Fright.setTargetPosition(ticks);
        global.Bleft.setTargetPosition(-ticks);
        global.Bright.setTargetPosition(ticks);
        global.Fleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        global.Fright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        global.Bleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        global.Bright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        global.Fleft.setPower(power);
        global.Bleft.setPower(power);
        global.Fright.setPower(power);
        global.Bright.setPower(power);
    }
    // Helpers
    // fwd: + forward | strafe: + right | turn: + CCW
    public static void setDrive(double fwd, double strafe, double turn) {
        global.Fleft .setPower(clamp(fwd + strafe - turn));
        global.Fright.setPower(clamp(fwd - strafe + turn));
        global.Bleft .setPower(clamp(fwd - strafe - turn));
        global.Bright.setPower(clamp(fwd + strafe + turn));
    }

    public static void stopDrive() {
        global.Fleft.setPower(0);
        global.Fright.setPower(0);
        global.Bleft.setPower(0);
        global.Bright.setPower(0);
    }

    public static void resetEncoder() {
        global.Fleft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        global.Fright.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        global.Bleft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        global.Bright.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }

    public static void resetDriveEncoders() {
        for (DcMotorEx m : new DcMotorEx[]{global.Fleft, global.Fright, global.Bleft, global.Bright}) {
            m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }
    public static int avgDriveTicks() {
        return (global.Fleft.getCurrentPosition()
                + global.Fright.getCurrentPosition()
                + global.Bleft.getCurrentPosition()
                + global.Bright.getCurrentPosition()) / 4;
    }

    public static double getHeading() {
        YawPitchRollAngles a = global.imu.getRobotYawPitchRollAngles();
        return a.getYaw(AngleUnit.DEGREES);
    }

    public static double normalizeAngle(double a) {
        while (a >  180) a -= 360;
        while (a < -180) a += 360;
        return a;
    }

    public static double clamp(double v) {
        return Math.max(-1, Math.min(1, v));
    }

    //sleeep is sleep plus resetEncoder combined so its easier to type.
    public void sleeep(int milliseconds){
        linearOpMode.sleep(milliseconds);
        resetEncoder();
    }

    public void driveStraight(double inches, double power, double targetHeading) {
        resetDriveEncoders();
        int targetTicks = (int)(inches * global.TICKS_PER_INCH);

        while (linearOpMode.opModeIsActive()) {
            int errorTicks = targetTicks - avgDriveTicks();
            if (Math.abs(errorTicks) <= global.ENCODER_TOL) break;

            // Forward magnitude with a gentle taper near the end
            double fwd = Math.signum(errorTicks)
                    * Math.min(power, 0.15 + Math.abs(errorTicks) * 0.0008);

            // IMU heading correction
            double headingError = normalizeAngle(targetHeading - getHeading());
            double turn = Math.max(-0.35, Math.min(0.35,
                    headingError * global.DRIVE_HEADING_KP));

            setDrive(fwd, 0, turn);

            telemetry.addData("Heading", getHeading());
            telemetry.addData("Ticks",   avgDriveTicks());
            //telemetry.addData("Acceleromoter", global.imu.);
            telemetry.update();
        }
        stopDrive();
    }

    // Turn in place using IMU
    public void turnToHeading(double targetHeading, double power) {
        while (linearOpMode.opModeIsActive()) {
            double error = normalizeAngle(targetHeading - getHeading());
            if (Math.abs(error) < global.HEADING_TOL_DEG) break;

            double turn = error * global.TURN_KP;
            turn = Math.max(-power, Math.min(power, turn));
            if (Math.abs(turn) < 0.12) turn = Math.signum(turn) * 0.12;

            setDrive(0, 0, turn);

            telemetry.addData("Heading", getHeading());
            telemetry.addData("Error",   error);
            telemetry.update();
        }
        stopDrive();
    }
}

