package org.firstinspires.ftc.teamcode.testbot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.modularscripts.DriveTrain;
import org.firstinspires.ftc.teamcode.modularscripts.GlobalScript;
import org.firstinspires.ftc.teamcode.modularscripts.MotorScript;

@TeleOp
public class TestBotOpModeGamepad extends LinearOpMode {

    private GlobalScript global;

    private DriveTrain driveTrain;

    private MotorScript motorScript;

    private DcMotorEx testMotor;


    @Override
    public void runOpMode() throws InterruptedException {

        testMotor = hardwareMap.get(DcMotorEx.class, "ball_rotation_wheel");

        global.FrontLeft = hardwareMap.get(DcMotorEx.class, "FrontLeft");

        global.FrontRight = hardwareMap.get(DcMotorEx.class, "FrontRight");

        global.BackRight = hardwareMap.get(DcMotorEx.class, "BackRight");

        global.BackLeft = hardwareMap.get(DcMotorEx.class, "BackLeft");

        global.FrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        global.BackRight.setDirection(DcMotorSimple.Direction.REVERSE);
        
        //driveTrain.posBasedMovementInit();


        waitForStart();
        resetRuntime();

        
        while (opModeIsActive()) {
            telemetry.addData("Y"+-gamepad1.left_stick_y+"X"+gamepad1.left_stick_x+"RX"+gamepad1.right_stick_x, "okay");
            telemetry.update();
            driveTrain.driveTeleOp(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            motorScript.ControlMotorPwrBased(testMotor, 1, gamepad1.left_bumper);
            motorScript.ControlMotorPwrBased(testMotor, -1, gamepad1.right_bumper);
        }
    }
}
