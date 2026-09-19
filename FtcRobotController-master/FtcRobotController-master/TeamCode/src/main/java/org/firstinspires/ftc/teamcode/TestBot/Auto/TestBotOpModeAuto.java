package org.firstinspires.ftc.teamcode.TestBot.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.DeviceScripts.DriveTrain;
import org.firstinspires.ftc.teamcode.GlobalScript;

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

        global.FrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        global.BackRight.setDirection(DcMotorSimple.Direction.REVERSE);


        waitForStart();
        resetRuntime();

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
        sleep(500);
        driveTrain.RotatePwrBased(false, 0.0);

        while (opModeIsActive()) {

            telemetry.addData("TestBot", "Hello World");

        }

    }
}
