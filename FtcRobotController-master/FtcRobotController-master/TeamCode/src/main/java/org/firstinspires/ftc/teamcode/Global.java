package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Global {

    public static DcMotorEx FLeft;

    public static DcMotorEx FRight;

    public static DcMotorEx BLeft;

    public static DcMotorEx BRight;

    public static double speedMultiplyer;

    public static boolean limelightEnabled;
    public static boolean autoAlign;
    public static double TX_TARGET;
    public static double TX_DEADBAND;
    public static double AUTO_ALIGN_KP;
    public static double AUTO_ALIGN_KD;
    public static double AUTO_ALIGN_MAX_TURN;
    public static double lastTXError;

    public static double CAMERA_HEIGHT_IN;

    public static double TAG_HEIGHT_IN;

    public static double CAMERA_PITCH_DEG;


    public static double tx;
    public static double ty;
    public static double distance;
    public static int tagId;

    public static boolean llvaid = false;

    public static int lockedTagId = -1;

    public static double rawDistanceMeters = -1;

    public static double filteredDistanceMeters = -1;

    public static LLResultTypes.FiducialResult best;

    public ElapsedTime distanceTimer = new ElapsedTime();

    public int lastDistanceTagId = -1;
    public int distanceMedianIndex = 0;
    public int distanceMedianCount = 0;
}
