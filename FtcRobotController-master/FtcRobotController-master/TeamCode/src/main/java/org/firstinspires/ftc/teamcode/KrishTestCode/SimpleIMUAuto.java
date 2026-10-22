
package org.firstinspires.ftc.teamcode.KrishTestCode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@Disabled//(name = "SimpleIMUAuto", group = "Autonomous")
public class SimpleIMUAuto extends LinearOpMode {

    private DcMotorEx Fleft, Bleft, Fright, Bright;
    private IMU imu;
    private static final double TICKS_PER_REV   = 537.7;    // goBILDA 5203 13.7:1
    private static final double WHEEL_DIAM_IN   = 3.7795;   // 96 mm
    private static final double TICKS_PER_INCH  =
            TICKS_PER_REV / (WHEEL_DIAM_IN * Math.PI);

    private static final double DRIVE_POWER      = 0.25;
    private static final double TURN_POWER       = 0.45;
    private static final double DRIVE_HEADING_KP = 0.02;
    private static final double TURN_KP          = 0.015;
    private static final double HEADING_TOL_DEG  = 2.0;
    private static final int    ENCODER_TOL      = 20;

    @Override
    public void runOpMode() {

        // Hardware map (matches your TeleOp)
        Fleft  = hardwareMap.get(DcMotorEx.class, "Fleft");
        Fright = hardwareMap.get(DcMotorEx.class, "Fright");
        Bleft  = hardwareMap.get(DcMotorEx.class, "Bleft");
        Bright = hardwareMap.get(DcMotorEx.class, "Bright");

        Fleft.setDirection(DcMotorSimple.Direction.REVERSE);
        Bleft.setDirection(DcMotorSimple.Direction.REVERSE);

        for (DcMotorEx m : new DcMotorEx[]{Fleft, Fright, Bleft, Bright}) {
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        // IMU init
        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot orientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
        imu.initialize(new IMU.Parameters(orientation));

        telemetry.addLine("Ready. Press play.");
        telemetry.update();

        waitForStart();
        imu.resetYaw();   // treat current facing as heading 0

        // AUTO SEQUENCE
        driveStraight(12, DRIVE_POWER, 0);    // fwd 24" holding heading 0
        turnToHeading(90, TURN_POWER);        // CCW turn to 90°
    }

    // Drive straight using encoder distance + IMU heading hold
    private void driveStraight(double inches, double power, double targetHeading) {
        resetEncoders();
        int targetTicks = (int)(inches * TICKS_PER_INCH);

        while (opModeIsActive()) {
            int errorTicks = targetTicks - avgDriveTicks();
            if (Math.abs(errorTicks) <= ENCODER_TOL) break;

            // Forward magnitude with a gentle taper near the end
            double fwd = Math.signum(errorTicks)
                    * Math.min(power, 0.15 + Math.abs(errorTicks) * 0.0008);

            // IMU heading correction
            double headingError = normalizeAngle(targetHeading - getHeading());
            double turn = Math.max(-0.35, Math.min(0.35,
                    headingError * DRIVE_HEADING_KP));

            setDrive(fwd, 0, turn);

            telemetry.addData("Heading", getHeading());
            telemetry.addData("Ticks",   avgDriveTicks());
            telemetry.update();
        }
        stopDrive();
    }

    // Turn in place using IMU
    private void turnToHeading(double targetHeading, double power) {
        while (opModeIsActive()) {
            double error = normalizeAngle(targetHeading - getHeading());
            if (Math.abs(error) < HEADING_TOL_DEG) break;

            double turn = error * TURN_KP;
            turn = Math.max(-power, Math.min(power, turn));
            if (Math.abs(turn) < 0.12) turn = Math.signum(turn) * 0.12;

            setDrive(0, 0, turn);

            telemetry.addData("Heading", getHeading());
            telemetry.addData("Error",   error);
            telemetry.update();
        }
        stopDrive();
    }

    // Helpers
    // fwd: + forward | strafe: + right | turn: + CCW
    private void setDrive(double fwd, double strafe, double turn) {
        Fleft .setPower(clamp(fwd + strafe - turn));
        Fright.setPower(clamp(fwd - strafe + turn));
        Bleft .setPower(clamp(fwd - strafe - turn));
        Bright.setPower(clamp(fwd + strafe + turn));
    }

    private void stopDrive() {
        Fleft.setPower(0);
        Fright.setPower(0);
        Bleft.setPower(0);
        Bright.setPower(0);
    }

    private void resetEncoders() {
        for (DcMotorEx m : new DcMotorEx[]{Fleft, Fright, Bleft, Bright}) {
            m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    private int avgDriveTicks() {
        return (Fleft.getCurrentPosition()
                + Fright.getCurrentPosition()
                + Bleft.getCurrentPosition()
                + Bright.getCurrentPosition()) / 4;
    }

    private double getHeading() {
        YawPitchRollAngles a = imu.getRobotYawPitchRollAngles();
        return a.getYaw(AngleUnit.DEGREES);
    }

    private double normalizeAngle(double a) {
        while (a >  180) a -= 360;
        while (a < -180) a += 360;
        return a;
    }

    private double clamp(double v) {
        return Math.max(-1, Math.min(1, v));
    }
}

