package org.firstinspires.ftc.teamcode.Devices;


import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class MotorScript {


    public void setMotorDir(DcMotorEx Motor, int dir) {
        if (dir == 1) {
            Motor.setDirection(DcMotorSimple.Direction.FORWARD);
        } else if (dir == 2) {
            Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }


    public void ControlMotorPosBased(DcMotorEx Motor, double vel, boolean button) {
        if (button) {
            Motor.setVelocity(vel);
        }else if (!button) {
            Motor.setVelocity(0);
        }
    }


    public static void ControlMotorPwrBased(DcMotorEx Motor, double pwr,  boolean button) {
        if (button) {
            Motor.setPower(pwr);
        } else if (!button) {
            Motor.setPower(0);
        }
    }
}
