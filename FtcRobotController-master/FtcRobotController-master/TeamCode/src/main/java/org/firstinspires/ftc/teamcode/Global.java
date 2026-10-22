package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Global {

    public static DcMotorEx Fleft;

    public static DcMotorEx Fright;

    public static DcMotorEx Bleft;

    public static DcMotorEx Bright;

    public static DcMotorEx Intake;

    public static IMU imu;
    public static Limelight3A limelight;
    public static final double TICKS_PER_REV   = 537.7;    // goBILDA 5203 13.7:1
    public static final double TICKS_PER_ROT   = 384.5;
    public static final double WHEEL_DIAM_IN   = 3.7795;   // 96 mm
    public static final double WHEEL_DIAM_CM   = WHEEL_DIAM_IN * 2.54;
    public static final double WHEEL_CIRC_CM   = WHEEL_DIAM_CM * Math.PI;
    public static final double T_TO_D = TICKS_PER_ROT/WHEEL_CIRC_CM;
    public static final double TICKS_PER_INCH  =
            TICKS_PER_REV / (WHEEL_DIAM_IN * Math.PI);
    public static final double DRIVE_POWER      = 0.25;
    public static final double TURN_POWER       = 0.45;
    public static final double DRIVE_HEADING_KP = 0.02;
    public static final double TURN_KP          = 0.015;
    public static final double HEADING_TOL_DEG  = 2.0;
    public static final int    ENCODER_TOL      = 20;
    // Limelight State
    public static boolean limelightEnabled = true;
    public static boolean autoAlign = false;

    // Limelight Auto-Align Tuning
    public static double TX_TARGET = 0.0;
    public static double TX_DEADBAND = 0.05;
    public static double AUTO_ALIGN_KP = 0.02;
    public static double AUTO_ALIGN_KD = 0.002;
    public static double AUTO_ALIGN_MAX_TURN = 0.35;
    public static double lastTxError = 0;

    // Distance filtering
    public static final int DISTANCE_MEDIAN_SIZE = 7;
    public static double[] distanceMedianBuffer = new double[DISTANCE_MEDIAN_SIZE];
    public static int distanceMedianCount = 0;
    public static int distanceMedianIndex = 0;

    public static double filteredDistanceMeters = -1;

    public static ElapsedTime distanceTimer = new ElapsedTime();

    public static final double DISTANCE_FILTER_TAU = 0.12;      // seconds, lower = faster response
    public static final double MAX_DISTANCE_JUMP_METERS = 0.45; // ~18 inches, outlier gate
    public static final double DISTANCE_TIMEOUT_SEC = 0.5;      // keep last value this long
    public static int lockedTagId = -1;
    public static int lastDistanceTagId = -1;

    // Edge Detection
    public static boolean last2A = false;
    public static boolean last2B = false;
    public static boolean last2Y = false;

    public static ElapsedTime loopTimer = new ElapsedTime();
}
