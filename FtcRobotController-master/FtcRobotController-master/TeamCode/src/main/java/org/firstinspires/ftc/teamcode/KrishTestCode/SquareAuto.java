//
//      |---|  /---/  |---====--\  |----------|   /---------|  |---|   |---|         |---====--\        /--------\           /------\
//      |   | /   /   |   |  |  |  |---    ---|  |          /  |   |   |   |         |   |  |  |       /-----     \         / ,----, \
//      |   |/   /    |   '--'  /      |  |      |    -----    |   |---|   |         |   '--'  /           _/    _/        |  |    |  |
//      |       |     |       \        |  |       \        \   |           |         |       \          _/    _/           |  |    |  |
//      |   |\   \    |   |\   \       |  |        -----    |  |   |---|   |         |   |\   \        /   _/              |  |    |  |
//      |   | \   \   |   | \   \  |---    ---|  /          |  |   |   |   |  |---|  |   | \   \       |   -------|  |---|  \ '----' /
//      |---|  \---\  |---|  \---\ |----------|  |---------/   |---|   |---|  |---|  |---|  \---\      |----------|  |---|   \------/
//      FTC Season 2 (for me) :) :) :)
package org.firstinspires.ftc.teamcode.KrishTestCode;
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
            //Insert launch code here
            robot.turn(0.8, -682);
            robot.sleeep(750);
            robot.strafe(1.0, -500);
            robot.sleeep(1000);
            robot.forward(0.7, 1400);
            robot.sleeep(1500);
            robot.forward(0.87, -1500);
            robot.sleeep(1500);
            robot.strafe(1.0, -150);
            robot.sleeep(500);
            robot.strafe(1.0, 500);
            robot.sleeep(1000);
            robot.turn(0.8, 682);
            robot.sleeep(750);
            limelight.wUintilLLscan(-1); //do not know tag yet. check on thursday
            //Insert launch code here
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
