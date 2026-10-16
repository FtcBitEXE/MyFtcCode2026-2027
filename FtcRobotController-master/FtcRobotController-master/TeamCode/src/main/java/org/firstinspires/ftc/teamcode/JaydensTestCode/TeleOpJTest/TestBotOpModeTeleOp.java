package org.firstinspires.ftc.teamcode.JaydensTestCode.TeleOpJTest;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Devices.DriveTrain;
import org.firstinspires.ftc.teamcode.Devices.FlyWheelScript;
import org.firstinspires.ftc.teamcode.Devices.SensorScript;
import org.firstinspires.ftc.teamcode.Devices.ServoScript;
import org.firstinspires.ftc.teamcode.Global;
import org.firstinspires.ftc.teamcode.Devices.MotorScript;

@TeleOp
public class TestBotOpModeTeleOp extends LinearOpMode {

    private Global global;

    private DriveTrain driveTrain;

    private MotorScript motorScript;

   // private LimelightScript limelightScript;

    private DcMotorEx testMotor;

    private FlyWheelScript flyWheelScript;

    private ServoScript servoScript;

    private DcMotorEx flywheel1;

    private DcMotorEx flywheel2;

    private Servo testServo;

    private DcMotorEx spin;

    private Limelight3A limelight;

    private SensorScript sensorScript;

    private NormalizedColorSensor colorSensor;

    private DistanceSensor distanceSensor;


    @Override
    public void runOpMode() throws InterruptedException {

        //global.FLeft = hardwareMap.get(DcMotorEx.class, "FrontLeft");

        //global.FRight = hardwareMap.get(DcMotorEx.class, "FrontRight");

        //global.BRight = hardwareMap.get(DcMotorEx.class, "BackRight");

        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        colorSensor = hardwareMap.get(NormalizedColorSensor.class, "colorSensor");
        distanceSensor = hardwareMap.get(DistanceSensor.class, "distanceSensor");

        //global.BLeft = hardwareMap.get(DcMotorEx.class, "BackLeft");

        //limelightScript.LimelightTune(0.75, true, false, 0.0, 0.05, 0.02, 0.002, 0.35, 0);




        waitForStart();
        resetRuntime();

        
        while (opModeIsActive()) {
            telemetry.addData("tagId", global.tagId);
            telemetry.addData("best", global.best);
            telemetry.addData("llvaid", global.llvaid);
            telemetry.addData("Red", sensorScript.GetColor(colorSensor).red);
            telemetry.addData("Green", sensorScript.GetColor(colorSensor).green);
            telemetry.addData("Blue", sensorScript.GetColor(colorSensor).blue);
            telemetry.addData("Distance", SensorScript.GetDistance(distanceSensor));

            telemetry.update();
            //driveTrain.driveTeleOp(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

            //limelightScript.LimelightRead(limelight, global.llvaid);



        }
    }
}
