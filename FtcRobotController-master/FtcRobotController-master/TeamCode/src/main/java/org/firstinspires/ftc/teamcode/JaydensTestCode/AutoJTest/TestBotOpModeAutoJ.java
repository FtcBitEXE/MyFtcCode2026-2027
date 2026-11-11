package org.firstinspires.ftc.teamcode.JaydensTestCode.AutoJTest;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

import org.firstinspires.ftc.teamcode.Devices.DriveTrain;
import org.firstinspires.ftc.teamcode.Devices.SensorScript;
import org.firstinspires.ftc.teamcode.Global;

@Autonomous
public class TestBotOpModeAutoJ extends LinearOpMode {

    private Global global;

    private DriveTrain driveTrain;

    private SensorScript sensorScript;

    private NormalizedColorSensor colorSensor1;
    private NormalizedColorSensor colorSensor2;
    private NormalizedColorSensor colorSensor3;
    private NormalizedColorSensor colorSensor4;

    private String nectar1 = "Empty";
    private String nectar2 = "Empty";
    private String nectar3 = "Empty";
    private String nectar4 = "Empty";




    @Override
    public void runOpMode() throws InterruptedException {
        //global.FLeft = hardwareMap.get(DcMotorEx.class, "FrontLeft");

        //global.FRight = hardwareMap.get(DcMotorEx.class, "FrontRight");

        //global.BRight = hardwareMap.get(DcMotorEx.class, "BackRight");

       // global.BLeft = hardwareMap.get(DcMotorEx.class, "BackLeft");



        colorSensor1 = hardwareMap.get(NormalizedColorSensor.class, "colSens1");
        colorSensor2 = hardwareMap.get(NormalizedColorSensor.class, "colSens2");
        colorSensor3 = hardwareMap.get(NormalizedColorSensor.class, "colSens3");
        colorSensor4 = hardwareMap.get(NormalizedColorSensor.class, "colSens4");


        //global.FRight.setDirection(DcMotorSimple.Direction.REVERSE);
        //global.BRight.setDirection(DcMotorSimple.Direction.REVERSE);


        waitForStart();
        resetRuntime();

        /*driveTrain.drivePwrBased(1, 0.8);
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
        driveTrain.RotatePwrBased(false, 0.0);*/

        while (opModeIsActive()) {

            telemetry.addData("TestBot", "Hello World");

            nectar1 = sensorScript.GetBuzz(colorSensor1);
            nectar2 = sensorScript.GetBuzz(colorSensor2);
            nectar3 = sensorScript.GetBuzz(colorSensor3);
            nectar4 = sensorScript.GetBuzz(colorSensor4);

            if (nectar1 != "Late" || nectar2 != "late" || nectar3 != "late" || nectar4 != "late") {
                telemetry.addData("Pollen Slot 1", nectar1);
                telemetry.addData("Pollen Slot 2", nectar2);
                telemetry.addData("Pollen Slot 3", nectar3);
                telemetry.addData("Pollen Slot 4", nectar4);
            } else{
                sleep(500);
            }



            telemetry.update();

        }

    }
}
