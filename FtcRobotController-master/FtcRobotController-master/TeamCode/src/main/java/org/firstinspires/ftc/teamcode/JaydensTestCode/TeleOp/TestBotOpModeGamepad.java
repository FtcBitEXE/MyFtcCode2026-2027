package org.firstinspires.ftc.teamcode.JaydensTestCode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Devices.DriveTrain;
import org.firstinspires.ftc.teamcode.Devices.FlyWheelScript;
import org.firstinspires.ftc.teamcode.Global;
import org.firstinspires.ftc.teamcode.Devices.MotorScript;

@TeleOp
public class TestBotOpModeGamepad extends LinearOpMode {

    private Global global;

    private DriveTrain driveTrain;

    private MotorScript motorScript;



    private DcMotorEx testMotor;

    private FlyWheelScript flyWheelScript;

    private DcMotorEx flywheel1;

    private DcMotorEx flywheel2;


    @Override
    public void runOpMode() throws InterruptedException {

        testMotor = hardwareMap.get(DcMotorEx.class, "roll");

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
            motorScript.ControlMotorPwrBased(testMotor, 0, !gamepad1.left_bumper);
            motorScript.ControlMotorPwrBased(testMotor, 0 , !gamepad1.right_bumper);
            flyWheelScript.SingleFlywheelTeleOp(flywheel1, 1300, gamepad1.a);


        }
    }
}
