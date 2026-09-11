package org.firstinspires.ftc.teamcode.testbot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.modularscripts.DriveTrain;
import org.firstinspires.ftc.teamcode.modularscripts.GlobalScript;

@TeleOp
public class TestBotOpModeGamepad extends LinearOpMode {

    private GlobalScript global;

    private DriveTrain driveTrain;

    private Gamepad player1 = new Gamepad();

    @Override
    public void runOpMode() throws InterruptedException {

        global.FrontLeft = hardwareMap.get(DcMotorEx.class, "FrontLeft");

        global.FrontRight = hardwareMap.get(DcMotorEx.class, "FrontRight");

        global.BackRight = hardwareMap.get(DcMotorEx.class, "BackRight");

        global.BackLeft = hardwareMap.get(DcMotorEx.class, "BackLeft");

        global.FrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        global.BackRight.setDirection(DcMotorSimple.Direction.REVERSE);


        waitForStart();
        resetRuntime();


        while (opModeIsActive()) {
            if (player1.left_stick_y >= 0.1) {
                driveTrain.drivePwrBased(1, 0.8);
            } else if (player1.left_stick_y <= 0) {
                driveTrain.drivePwrBased(2, 0.8);
            } else if (player1.left_stick_x <= 0) {
                driveTrain.drivePwrBased(3, 0.8);
            } else if (player1.left_stick_x >= 0.1) {
                driveTrain.drivePwrBased(4, 0.8);
            }


            if (player1.right_stick_y >= 0.1) {
                driveTrain.RotatePwrBased(false, 0.8);
            } else if (player1.right_stick_y <= 0) {
                driveTrain.RotatePwrBased(true, 0.8);
            }
        }
    }
}
