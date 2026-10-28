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
    private DcMotorEx Outake2;


    @Override
    public void runOpMode() throws InterruptedException {

        global.Fleft = hardwareMap.get(DcMotorEx.class, "Fleft");
        global.Fright = hardwareMap.get(DcMotorEx.class, "Fright");
        global.Bright = hardwareMap.get(DcMotorEx.class, "Bright");
        global.Bleft = hardwareMap.get(DcMotorEx.class, "Bleft");
        Intake = hardwareMap.get(DcMotorEx.class, "Intake");
        Outake1 = hardwareMap.get(DcMotorEx.class, "Outake1");
        Outake2 = hardwareMap.get(DcMotorEx.class, "Outake2");

        waitForStart();
        resetRuntime();

        while (opModeIsActive()) {
            //Oooh, these actually look good! Could be simplified, but good enough!
            driveTrain.driveTeleOp(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            motorScript.ControlMotorPwrBased(Intake,1, gamepad1.left_bumper);
            motorScript.ControlMotorPwrBased(Intake,1, gamepad1.right_bumper);
            flyWheelScript.DoubleFlywheelPwrTeleop(Outake1, Outake2, 1, gamepad1.a);
        }
    }
}

