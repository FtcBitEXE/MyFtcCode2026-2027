package org.firstinspires.ftc.teamcode.KrishTestCode;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Devices.DriveTrain;
import org.firstinspires.ftc.teamcode.Global;
@TeleOp
public class testDrivetrain extends LinearOpMode {
    private static DriveTrain robot;
    private static Global global;
    public void runOpMode(){
        //robot.motorInit(hardwareMap);
        global.limelight = hardwareMap.get(Limelight3A.class, "limelight");
        if (global.limelight == null) {
            throw new NullPointerException("Limelight named 'limelight' not found in hardwareMap");
        }
        waitForStart();
        while (opModeIsActive()){
            robot.driveTeleOpLimelight(-gamepad1.right_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
            telemetry.addData("Fleft", global.Fleft.getCurrentPosition());
            telemetry.addData("Fright", global.Fright.getCurrentPosition());
            telemetry.addData("Bleft", global.Bleft.getCurrentPosition());
            telemetry.addData("Bright", global.Bright.getCurrentPosition());
            telemetry.update();
        }
    }
}