package org.firstinspires.ftc.teamcode.STARTERBOTNOTREALBOTCODE;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Devices.DriveTrain;
import org.firstinspires.ftc.teamcode.Devices.FlyWheelScript;
import org.firstinspires.ftc.teamcode.Devices.MotorScript;
import org.firstinspires.ftc.teamcode.Global;

@TeleOp(name="REV Starter Bot Code")
public class STARTERBOTNOTMAINBOTTeleOp extends LinearOpMode {

    //Thanks, Jayden!
    //Simplified a little bit
    private Global global;
    private DriveTrain driveTrain;
    private MotorScript motorScript;
    private FlyWheelScript flyWheelScript;
    private DcMotorEx Intake;
    //Not my preferrec choice of
    private DcMotorEx Outake1;
    private DcMotorEx lDrive;
    private DcMotorEx rDrive;


    @Override
    public void runOpMode() throws InterruptedException {

        lDrive = hardwareMap.get(DcMotorEx.class, "lDrive");
        rDrive = hardwareMap.get(DcMotorEx.class, "rDrive");
        Intake = hardwareMap.get(DcMotorEx.class, "Intake");
        Outake1 = hardwareMap.get(DcMotorEx.class, "Outake1");

        waitForStart();
        resetRuntime();

        while (opModeIsActive()) {
            //use this if works
            float drivePower = -gamepad1.right_stick_y;
            float rotatePower = gamepad1.left_stick_x;
            double leftPower  = drivePower + rotatePower;
            double rightPower = drivePower - rotatePower;
            lDrive.setPower(leftPower);
            rDrive.setPower(rightPower);
            //if not, then:
            //lDrive.setPower(gamepad1.left_stick_y);
            //rDrive.setPower(gamepad1.right_stick_y);

            //not confident these will work
            motorScript.ControlMotorPwrBased(Intake,1, gamepad1.left_bumper);
            motorScript.ControlMotorPwrBased(Intake,-1, gamepad1.right_bumper);

            //flyWheelScript.SingleFlywheelTeleOp(Outake1, 1, gamepad1.a);
            if (gamepad1.right_trigger > 0.2) {
                Outake1.setPower(1);
            } else {
                Outake1.setPower(0);
            }
        }
    }
}

