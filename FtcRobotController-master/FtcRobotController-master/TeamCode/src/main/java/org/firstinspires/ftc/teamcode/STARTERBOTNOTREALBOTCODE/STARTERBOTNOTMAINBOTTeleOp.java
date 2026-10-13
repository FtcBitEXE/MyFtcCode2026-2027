package org.firstinspires.ftc.teamcode.STARTERBOTNOTREALBOTCODE;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Devices.DriveTrain;
import org.firstinspires.ftc.teamcode.Devices.FlyWheelScript;
import org.firstinspires.ftc.teamcode.Devices.LimelightScript;
import org.firstinspires.ftc.teamcode.Devices.MotorScript;
import org.firstinspires.ftc.teamcode.Devices.ServoScript;
import org.firstinspires.ftc.teamcode.Global;

public class STARTERBOTNOTMAINBOTTeleOp extends LinearOpMode {


    private Global global;

    private DriveTrain driveTrain;

    private MotorScript motorScript;

    private LimelightScript limelightScript;


    private FlyWheelScript flyWheelScript;

    private DcMotorEx Intake;
    private ServoScript servoScript;

    private DcMotorEx Outake1;

    private DcMotorEx Outake2;


    @Override
    public void runOpMode() throws InterruptedException {

        global.FLeft = hardwareMap.get(DcMotorEx.class, "FrontLeft");

        global.FRight = hardwareMap.get(DcMotorEx.class, "FrontRight");

        global.BRight = hardwareMap.get(DcMotorEx.class, "BackRight");


        global.BLeft = hardwareMap.get(DcMotorEx.class, "BackLeft");

        Intake = hardwareMap.get(DcMotorEx.class, "Intake");

        Outake1 = hardwareMap.get(DcMotorEx.class, "Outake1");

        Outake2 = hardwareMap.get(DcMotorEx.class, "Outake2");



        waitForStart();
        resetRuntime();


        while (opModeIsActive()) {

            driveTrain.driveTeleOp(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            motorScript.ControlMotorPwrBased(Intake,1, gamepad1.left_bumper);
            motorScript.ControlMotorPwrBased(Intake,1, gamepad1.right_bumper);
            flyWheelScript.DoubleFlywheelPwrTeleop(Outake1, Outake2, 1, gamepad1.a);
        }
    }
}

