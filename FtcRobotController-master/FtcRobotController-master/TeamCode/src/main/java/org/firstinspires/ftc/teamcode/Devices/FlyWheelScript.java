package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class FlyWheelScript {




    public static void FlywheelInit(DcMotorEx flywheel) {
        flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public static void DoubleFlywheelVelTeleop(DcMotorEx flywheel, DcMotorEx flywheel2, double targetVel, int bankVel, int maxVel, int farVel, int B_F, boolean button) {
        if (button) {
            flywheel.setVelocity(bankVel);
            if (flywheel.getVelocity() >= bankVel - 50) {
                flywheel2.setPower(1);
            } else {
                flywheel2.setPower(0);
            }
        } else {
            flywheel.setVelocity(0);
            flywheel2.setPower(0);
        }
    }

    public static void DoubleFlywheelVel(DcMotorEx flywheel, DcMotorEx flywheel2, double targetVel, int bankVel, int maxVel, int farVel, int B_F) {

        flywheel.setVelocity(bankVel);
        if (flywheel.getVelocity() >= bankVel-50) {
            flywheel2.setPower(1);
        } else {
            flywheel2.setPower(0);
        }

    }

    public static void DoubleFlywheelStop(DcMotorEx flywheel, DcMotorEx flywheel2) {
        flywheel.setVelocity(0);
        flywheel2.setPower(0);
    }
    
    
    public static void SingleFlywheelTeleOp(DcMotorEx flywheel, int targetVel, boolean button) {
        if (button) {
            flywheel.setVelocity(targetVel);
        } else if (!button && flywheel.getVelocity() == targetVel) {
            flywheel.setVelocity(0);
        }

    }
}
