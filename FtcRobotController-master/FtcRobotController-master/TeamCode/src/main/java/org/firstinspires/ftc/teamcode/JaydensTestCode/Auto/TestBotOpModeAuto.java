package org.firstinspires.ftc.teamcode.JaydensTestCode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Devices.DriveTrain;
import org.firstinspires.ftc.teamcode.Global;

@Autonomous
public class TestBotOpModeAuto extends LinearOpMode {

    private Global global;

    private DriveTrain driveTrain;

    @Override
    public void runOpMode() throws InterruptedException {
        global.FLeft = hardwareMap.get(DcMotorEx.class, "FrontLeft");

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
