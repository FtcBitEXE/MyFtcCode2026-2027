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
import org.firstinspires.ftc.teamcode.Global;

//Makes program show up in the Autonomous tab on the Driver Hub
@Autonomous
public class NonagonAuto extends LinearOpMode {

    private static DriveTrain robot;
    private static Global global;

    public void runOpMode(){

        robot.motorInit(hardwareMap);

        //Prints on driver hub "Status: Initialized"
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //Waits for the program to start
        waitForStart();
        global.imu.resetYaw();
        if (opModeIsActive()) {
            //Prints on driver hub "Status: Program Running
            telemetry.clearAll();
            telemetry.addData("Status", "Program Running");
            telemetry.update();

            //Mves forward to get ready to launch
            robot.forward(0.6, 350);
            robot.sleeep(750);
            //Turns toward goal to launch
            robot.turn(0.6, 220);
            robot.sleeep(500);
            //Insert launch code here
            //robot.pewpew(launchnumber, launchpid oh no);
            //Turns to the flower to collect
            robot.turn(0.6, -565);
            robot.sleeep(500);
            //Moves to flower to collect
            robot.forward(0.6, 1000);
            robot.sleeep(1000);
            //Collect from flower code
            //robot.collectfromflowerithinkbecauseiamwritingthisinthepastsoidontknowwatthiswillbecalledbutanyaysigottogetbacktoworksothisthingymajigisforcollectingfromtheflowersoyeahbyebyefellowreader!!();
            //Move backwards to not hit flower
            robot.forward(0.6, -200);
            robot.sleeep(500);
            //Turns to face Hive
            robot.turn(0.6, 400);
            robot.sleeep(500);
            //Move forward
            robot.forward(0.6, 2300);
            robot.sleeep(2000);
            //Turn to correct position
            robot.turn(0.6, -150);
            robot.sleeep(500);
            //Strafes to hive
            robot.strafe(1.0, 1150);
            robot.sleeep(1000);
            //Turns around
            robot.turn(0.6, 1364);
            robot.sleeep(2000);
            //Moves backwards to launch
            robot.forward(0.6, -400);
            robot.sleeep(650);
            //aunchlay Odecay
            //robot.pewpew(launchnumber, launchpid oh no);
            //Strafe to the Loading Area
            //Move forward
            robot.forward(0.6, 400);
            robot.sleeep(650);
            //Strafe to the Loading Area
            robot.strafe(1.0, 2000);
            robot.sleeep(2000);



            //Prints on driver hub "Status: Program Ended"
            telemetry.clearAll();
            telemetry.addData("Status", "Program Ended");
            telemetry.update();

        }
    }
}
