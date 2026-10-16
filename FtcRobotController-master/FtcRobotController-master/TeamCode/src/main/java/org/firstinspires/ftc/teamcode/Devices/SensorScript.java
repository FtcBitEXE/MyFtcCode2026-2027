package org.firstinspires.ftc.teamcode.Devices;

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

    public static void GetBuzz(NormalizedColorSensor colorSensor) {

        NormalizedRGBA color = colorSensor.getNormalizedColors();

        if (color.red >=210 && color.green >= 200) {

        }

    }


}
