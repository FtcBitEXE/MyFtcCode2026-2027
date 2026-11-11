package org.firstinspires.ftc.teamcode.JaydensTestCode.AutoJTest;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.VoltageSensor;

@TeleOp(name = "ColorTeleOp3", group = "Linear OpMode")
public class CLORTEST extends LinearOpMode {

    DcMotorEx Fleft, Fright, Bright, Bleft, Intake, Launch;
    Servo MrServo;
    Limelight3A limelight;
    private NormalizedColorSensor color_sensor_v3;
    private final float[] hsv_values = new float[3];
    private static final float SAT_THRESHOLD = 0.30f;
    private static final float VAL_THRESHOLD = 0.10f;

    @Override
    public void runOpMode() {
        MrServo = hardwareMap.get(Servo.class, "MrServo");
        Fleft = hardwareMap.get(DcMotorEx.class, "Fleft");
        Fright = hardwareMap.get(DcMotorEx.class, "Fright");
        Bleft = hardwareMap.get(DcMotorEx.class,"Bleft");
        Bright = hardwareMap.get(DcMotorEx.class,"Bright");
        Intake = hardwareMap.get(DcMotorEx.class, "Intake");

        // Initialize the sensor from the hardware map FIRST
        color_sensor_v3 = hardwareMap.get(NormalizedColorSensor.class, "color_sensor_v3");

        if (color_sensor_v3 instanceof SwitchableLight) {
            ((SwitchableLight) color_sensor_v3).enableLight(true);
        }
        color_sensor_v3.setGain(2.40f);

        MrServo.setPosition(0.35);

        telemetry.addData("Status", "INITIATED");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            NormalizedRGBA colors = color_sensor_v3.getNormalizedColors();
            Color.colorToHSV(colors.toColor(), hsv_values);

            float hue = hsv_values[0]; // 0 - 360
            float sat = hsv_values[1]; // 0 - 1
            float val = hsv_values[2]; // 0 - 1

            String detected = "NONE";

            if (sat >= SAT_THRESHOLD && val >= VAL_THRESHOLD) {
                if (hue < 30f || hue >= 330f || hue >= 180f && hue <= 260f) {
                    detected = "Nectar";
                    MrServo.setPosition(0.85);
                } else if (hue >= 30f && hue < 150f) {
                    detected = "Pollen";
                    MrServo.setPosition(0.35);
                } else {
                    MrServo.setPosition(0.35);
                }

                telemetry.addData("Detected", detected);
                telemetry.addData("Mr. Servo", MrServo.getPosition());
                telemetry.update();
            }
        }
    }
}