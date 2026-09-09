package org.firstinspires.ftc.teamcode.testbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.modularscripts.DriveTrain;
import org.firstinspires.ftc.teamcode.modularscripts.GlobalScript;

@Autonomous
public class TestBotOpModeAuto extends LinearOpMode {

    private GlobalScript global;

    private DriveTrain driveTrain;

    @Override
    public void runOpMode() throws InterruptedException {
        global.FrontLeft = hardwareMap.get(DcMotorEx.class, "FrontLeft");

        global.FrontRight = hardwareMap.get(DcMotorEx.class, "FrontRight");

        global.BackRight = hardwareMap.get(DcMotorEx.class, "BackRight");

        global.BackLeft = hardwareMap.get(DcMotorEx.class, "BackLeft");


        driveTrain.drivePwrBased(1, 0.8);
        sleep(500);
        driveTrain.drivePwrBased(2, 0.8);
        sleep(500);
        driveTrain.drivePwrBased(3, 0.8);
        sleep(500);
        driveTrain.drivePwrBased(4, 0.8);
        sleep(500);
        driveTrain.RotatePwrBased(true, 0.8);
        sleep(500);
        driveTrain.RotatePwrBased(false, 0.8);

        waitForStart();
        resetRuntime();

        while (opModeIsActive()) {

            telemetry.addData("TestBot", "Hello World");

        }

    }
}
