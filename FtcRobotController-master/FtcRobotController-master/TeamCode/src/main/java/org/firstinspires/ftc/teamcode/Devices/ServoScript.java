package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.Servo;

public class ServoScript {

    public static void ServoTurnDegTeleOp(Servo servo, double deg, boolean button) {
        if (button) {
            servo.setPosition(deg);
        } else {
            servo.setPosition(0);
        }
    }
}
