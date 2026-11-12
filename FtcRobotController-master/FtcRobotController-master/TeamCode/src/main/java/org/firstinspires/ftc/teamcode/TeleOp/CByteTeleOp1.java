package org.firstinspires.ftc.teamcode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Devices.DriveTrain;
import org.firstinspires.ftc.teamcode.Global;

@TeleOp
public class CByteTeleOp1 extends LinearOpMode {
    private static DriveTrain robot;
    private static Global global;
    public void runOpMode(){
        robot.motorInit(hardwareMap);
        robot.checkHardwareMap(hardwareMap);
        waitForStart();
        while (opModeIsActive()){
            robot.driveTeleOp(-gamepad1.right_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
            telemetry.addData("Fleft", global.Fleft.getCurrentPosition());
            telemetry.addData("Fright", global.Fright.getCurrentPosition());
            telemetry.addData("Bleft", global.Bleft.getCurrentPosition());
            telemetry.addData("Bright", global.Bright.getCurrentPosition());
            telemetry.update();
        }
    }
}