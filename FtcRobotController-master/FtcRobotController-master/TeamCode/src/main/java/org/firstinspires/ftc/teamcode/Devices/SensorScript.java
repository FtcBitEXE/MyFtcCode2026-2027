package org.firstinspires.ftc.teamcode.Devices;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class SensorScript {

    public static NormalizedRGBA GetColor(NormalizedColorSensor colorSensor) {
        NormalizedRGBA color = colorSensor.getNormalizedColors();

        return color;
    }


    public static double GetDistance(DistanceSensor distanceSensor) {
        double Distance = distanceSensor.getDistance(DistanceUnit.CM);

        return Distance;

    }
    //Biobuzz specific

    public static String GetBuzz(NormalizedColorSensor colorSensor) {
        final float[] hsv_values = new float[3];
        NormalizedRGBA colors = colorSensor.getNormalizedColors();
        Color.colorToHSV(colors.toColor(), hsv_values);
        final float SAT_THRESHOLD = 0.30f;
        final float VAL_THRESHOLD = 0.10f;

        float hue = hsv_values[0]; // 0 - 360
        float sat = hsv_values[1]; // 0 - 1
        float val = hsv_values[2]; // 0 - 1
        String detected = "Empty";
        boolean detected_latly = false;

        if (sat >= SAT_THRESHOLD && val >= VAL_THRESHOLD) {
            if (!detected_latly) {
                if (hue >= 30f && hue < 150f) {
                    detected = "Pollen";
                    detected_latly = true;
                } else {
                    detected = "Empty";
                }
            }   else {
                return "late";
            }
        }

        return detected;


    }
}