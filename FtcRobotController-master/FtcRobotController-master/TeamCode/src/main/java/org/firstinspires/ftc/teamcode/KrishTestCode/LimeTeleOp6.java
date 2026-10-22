package org.firstinspires.ftc.teamcode.KrishTestCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Devices.LimeLight;

@TeleOp(name = "LimeTeleOp6", group = "Linear OpMode")
public class LimeTeleOp6 extends LinearOpMode {

    // Motors
    //DcMotorEx Fleft, Bleft, Fright, Bright;
    //DcMotorEx SpinDexer, Top_Launch, Bottom_Launch, Intake;

    // Servos
    //Servo LeftKicker, RightKicker;

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
    boolean autoAlign = false;

    // Edge Detection
    boolean last2A = false;
    boolean last2B = false;
    boolean last2Y = false;

    ElapsedTime loopTimer = new ElapsedTime();

    @Override
    public void runOpMode() {
        /*
        // Hardware Mapping
        Fleft = hardwareMap.get(DcMotorEx.class, "Fleft");
        if (Fleft == null) throw new NullPointerException("DcMotorEx named 'Fleft' not found");
        Fright = hardwareMap.get(DcMotorEx.class, "Fright");
        if (Fright == null) throw new NullPointerException("DcMotorEx named 'Fright' not found");
        Bleft = hardwareMap.get(DcMotorEx.class, "Bleft");
        if (Bleft == null) throw new NullPointerException("DcMotorEx named 'Bleft' not found");
        Bright = hardwareMap.get(DcMotorEx.class, "Bright");
        if (Bright == null) throw new NullPointerException("DcMotorEx named 'Bright' not found");

        Intake = hardwareMap.get(DcMotorEx.class, "Intake");
        if (Intake == null) throw new NullPointerException("DcMotorEx named 'Intake' not found");
        SpinDexer = hardwareMap.get(DcMotorEx.class, "SpinDexer");
        if (SpinDexer == null) throw new NullPointerException("DcMotorEx named 'SpinDexer' not found");
        Top_Launch = hardwareMap.get(DcMotorEx.class, "Top_Launch");
        if (Top_Launch == null) throw new NullPointerException("DcMotorEx named 'Top Launch' not found");
        Bottom_Launch = hardwareMap.get(DcMotorEx.class, "Bottom_Launch");
        if (Bottom_Launch == null) throw new NullPointerException("DcMotorEx named 'Bottom_Launch' not found");

        LeftKicker = hardwareMap.get(Servo.class, "LK");
        if (LeftKicker == null) throw new NullPointerException("Servo named 'LK' not found");
        RightKicker = hardwareMap.get(Servo.class, "RK");
        if (RightKicker == null) throw new NullPointerException("Servo named 'RK' not found");

        Fleft.setDirection(DcMotorSimple.Direction.REVERSE);
        Bleft.setDirection(DcMotorSimple.Direction.REVERSE);
        SpinDexer.setDirection(DcMotorSimple.Direction.REVERSE);
        Top_Launch.setDirection(DcMotorSimple.Direction.FORWARD);
        Bottom_Launch.setDirection(DcMotorSimple.Direction.REVERSE);

        LeftKicker.setDirection(Servo.Direction.FORWARD);
        RightKicker.setDirection(Servo.Direction.REVERSE);

        SpinDexer.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Top_Launch.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Bottom_Launch.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        */

        // Globalized Limelight init
        LimeLight.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        loopTimer.reset();

        while (opModeIsActive()) {
            double dt = loopTimer.seconds();
            loopTimer.reset();

            // One call does all of this:
            // Read
            // Lock tag
            // Filter distance
            // Timeout handling
            LimeLight.update(dt);

            boolean llValid = LimeLight.valid;
            double tx = LimeLight.tx;
            double ty = LimeLight.ty;
            double rawDist = LimeLight.rawDistanceMeters;
            double distanceInches = LimeLight.getDistanceInches();
            int tagId = LimeLight.tagId;

            // DRIVE & STRAFE
            double y = -gamepad1.right_stick_y;
            double x = gamepad1.right_stick_x;
            double rot = gamepad1.left_stick_x;

            if (autoAlign && llValid) {
                rot = LimeLight.calculateAutoAlignTurn(dt);
            } else if (autoAlign && !llValid) {
                rot = 0;
            }

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rot), 1);
            //Fleft.setPower(((y + x + rot) / denominator) * speedMultiplier);
            //Fright.setPower(((y - x - rot) / denominator) * speedMultiplier);
            //Bleft.setPower(((y - x + rot) / denominator) * speedMultiplier);
            //Bright.setPower(((y + x - rot) / denominator) * speedMultiplier);

            // INTAKE
            if (gamepad1.right_trigger > 0.2) {
                //Intake.setPower(0.75);
            } else if (gamepad1.left_trigger > 0.2) {
                //Intake.setPower(-0.75);
            } else {
                //Intake.setPower(0);
            }

            // LIMELIGHT TOGGLE
            if (gamepad2.b && !last2B) {
                if (LimeLight.isEnabled()) {
                    LimeLight.stop();
                    autoAlign = false;
                } else {
                    LimeLight.start();
                }
            }
            last2B = gamepad2.b;

            // AUTO-ALIGN TOGGLE
            if (gamepad2.a && !last2A) {
                autoAlign = !autoAlign;
                if (!autoAlign) LimeLight.resetLastTxError();
            }
            last2A = gamepad2.a;

            // LAUNCHER
            if (gamepad2.y && !last2Y) launcherOn = !launcherOn;
            last2Y = gamepad2.y;

            if (launcherOn) {
                //Top_Launch.setPower(LAUNCH_POWER);
                //Bottom_Launch.setPower(LAUNCH_POWER);
            } else {
                //Top_Launch.setPower(0);
                //Bottom_Launch.setPower(0);
            }

            // KICKER
            //handleKicker();

            // TELEMETRY
            telemetry.addData("Limelight", LimeLight.isEnabled() ? "ON" : "OFF");
            telemetry.addData("Auto Align", autoAlign ? "ON" : "OFF");
            telemetry.addData("LL Valid", llValid);
            telemetry.addData("Locked Tag ID", LimeLight.getLockedTagId());
            telemetry.addData("Tag ID", tagId);
            telemetry.addData("tx (deg)", "%.2f", tx);
            telemetry.addData("ty (deg)", "%.2f", ty);

            telemetry.addData("Raw Dist (m)", rawDist > 0 ? String.format("%.3f", rawDist) : "---");
            telemetry.addData("Filtered Dist (m)", LimeLight.getFilteredDistanceMeters() > 0
                    ? String.format("%.3f", LimeLight.getFilteredDistanceMeters()) : "---");
            telemetry.addData("Filtered Dist (in)", distanceInches > 0
                    ? String.format("%.2f", distanceInches) : "---");

            telemetry.addData("Launcher", launcherOn ? "ON" : "OFF");
            telemetry.addData("Launch Power", LAUNCH_POWER);
            telemetry.update();
        }
    }
}