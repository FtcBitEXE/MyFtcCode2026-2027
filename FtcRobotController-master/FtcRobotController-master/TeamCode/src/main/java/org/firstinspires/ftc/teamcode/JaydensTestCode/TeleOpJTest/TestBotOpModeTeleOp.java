package org.firstinspires.ftc.teamcode.JaydensTestCode.TeleOpJTest;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Devices.DriveTrain;
import org.firstinspires.ftc.teamcode.Devices.FlyWheelScript;
import org.firstinspires.ftc.teamcode.Devices.ServoScript;
import org.firstinspires.ftc.teamcode.Global;
import org.firstinspires.ftc.teamcode.Devices.MotorScript;

@TeleOp
public class TestBotOpModeTeleOp extends LinearOpMode {

    private Global global;

    private DriveTrain driveTrain;

    private MotorScript motorScript;



    private DcMotorEx testMotor;

    private FlyWheelScript flyWheelScript;

    private ServoScript servoScript;

    private DcMotorEx flywheel1;

    private DcMotorEx flywheel2;

    private Servo testServo;


    @Override
    public void runOpMode() throws InterruptedException {

        testMotor = hardwareMap.get(DcMotorEx.class, "roll");

        global.FLeft = hardwareMap.get(DcMotorEx.class, "FrontLeft");

        global.FRight = hardwareMap.get(DcMotorEx.class, "FrontRight");

        global.BRight = hardwareMap.get(DcMotorEx.class, "BackRight");

        global.BLeft = hardwareMap.get(DcMotorEx.class, "BackLeft");

        flywheel1 = hardwareMap.get(DcMotorEx.class, "flywheel1");
        flywheel2 = hardwareMap.get(DcMotorEx.class, "flywheel2");

        testServo = hardwareMap.get(Servo.class, "testServo");

        global.FRight.setDirection(DcMotorSimple.Direction.REVERSE);
        global.BRight.setDirection(DcMotorSimple.Direction.REVERSE);
        
        //driveTrain.posBasedMovementInit();



        waitForStart();
        resetRuntime();

        
        while (opModeIsActive()) {
            telemetry.addData("Y"+-gamepad1.left_stick_y+"X"+gamepad1.left_stick_x+"RX"+gamepad1.right_stick_x, "okay");
            telemetry.update();
            driveTrain.driveTeleOp(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            motorScript.ControlMotorPwrBased(testMotor, 1, gamepad1.left_bumper);
            motorScript.ControlMotorPwrBased(testMotor, -1, gamepad1.right_bumper);
            motorScript.ControlMotorPwrBased(testMotor, 0, !gamepad1.left_bumper);
            motorScript.ControlMotorPwrBased(testMotor, 0 , !gamepad1.right_bumper);
            flyWheelScript.DoubleFlywheelVelTeleop(flywheel1, flywheel2, 8 , gamepad1.a);
            servoScript.ServoTurnDegTeleOp(testServo, 0.45, gamepad1.b);

        }
    }
}
