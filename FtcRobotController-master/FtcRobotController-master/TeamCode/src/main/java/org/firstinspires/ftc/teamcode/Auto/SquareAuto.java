//
//      |---|  /---/  |---====--\  |----------|   /---------|  |---|   |---|         |---====--\        /--------\           /------\
//      |   | /   /   |   |  |  |  |---    ---|  |          /  |   |   |   |         |   |  |  |       /-----     \         / ,----, \
//      |   |/   /    |   '--'  /      |  |      |    -----    |   |---|   |         |   '--'  /           _/    _/        |  |    |  |
//      |       |     |       \        |  |       \        \   |           |         |       \          _/    _/           |  |    |  |
//      |   |\   \    |   |\   \       |  |        -----    |  |   |---|   |         |   |\   \        /   _/              |  |    |  |
//      |   | \   \   |   | \   \  |---    ---|  /          |  |   |   |   |  |---|  |   | \   \       |   -------|  |---|  \ '----' /
//      |---|  \---\  |---|  \---\ |----------|  |---------/   |---|   |---|  |---|  |---|  \---\      |----------|  |---|   \------/
//      FTC Season 2 (for me) :) :) :)
package org.firstinspires.ftc.teamcode.Auto;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Devices.DriveTrain;
import org.firstinspires.ftc.teamcode.Devices.LimeLight;
import org.firstinspires.ftc.teamcode.Global;
@Autonomous
public class SquareAuto extends LinearOpMode {
    private static DriveTrain robot;
    private static Global global;
    private static LimeLight limelight;
    public void runOpMode(){
        robot.motorInit(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        global.imu.resetYaw();
        if (opModeIsActive()) {
            telemetry.clearAll();
            telemetry.addData("Status", "Program Running");
            telemetry.update();
            robot.forward(0.8, 350);
            robot.sleeep(750);
            global.Launch1.setPower(0.8);
            global.Launch2.setPower(0.8);
            global.Transfer.setPower(1);
            sleep(2000);
            global.Launch1.setPower(0);
            global.Launch2.setPower(0);
            global.Transfer.setPower(0);
            robot.turn(0.8, -682);
            robot.sleeep(750);
            robot.strafe(1.0, -500);
            robot.sleeep(1000);
            global.Intake.setPower(0.8);
            robot.forward(0.7, 1400);
            robot.sleeep(2000);
            global.Intake.setPower(0);
            robot.forward(0.87, -1500);
            robot.sleeep(1500);
            robot.strafe(1.0, -150);
            robot.sleeep(500);
            robot.strafe(1.0, 500);
            robot.sleeep(1000);
            robot.turn(0.8, 682);
            robot.sleeep(750);
            limelight.wUintilLLscan(35); //unsure if work
            global.Launch1.setPower(0.8);
            global.Launch2.setPower(0.8);
            global.Transfer.setPower(1);
            sleep(2000);
            global.Launch1.setPower(0);
            global.Launch2.setPower(0);
            global.Transfer.setPower(0);
            robot.strafe(1.0, -1300);
            robot.sleeep(1000);
            robot.forward(0.8, 2300);
            robot.sleeep(2000);
            robot.strafe(1.0, -300);
            robot.sleeep(1000);
            telemetry.clearAll();
            telemetry.addData("Status", "Program Ended");
            telemetry.update();

        }
    }
}
