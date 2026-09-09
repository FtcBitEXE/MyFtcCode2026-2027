package org.firstinspires.ftc.teamcode.modularscripts;


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
        }
    }


    public void ControlMotorPwrBased(DcMotorEx Motor, double pwr, boolean button) {
        if (button) {
            Motor.setPower(pwr);
        }
    }
}
