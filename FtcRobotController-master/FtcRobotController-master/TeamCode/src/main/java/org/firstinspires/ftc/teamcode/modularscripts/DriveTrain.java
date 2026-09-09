package org.firstinspires.ftc.teamcode.modularscripts;

import com.qualcomm.robotcore.hardware.DcMotor;

public class DriveTrain {

    private static GlobalScript global;

    //initializes movement that uses velocity
    //only use if using velocity based movement
    public static void posBasedMovementInit() {
        global.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        global.FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        global.FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        global.FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        global.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        global.FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        global.FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        global.FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        global.BackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        global.BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        global.BackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        global.BackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        global.BackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        global.BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        global.BackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        global.BackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    // drives using the setVelocity Function
    //vel must be a positive number
    // if F_B_L_R is 1 it will go forward
    // if F_B_L_R is 2 it will go backward
    // if F_B_L_R is 3 it will go left
    // if F_B_L_R is 4 it will go right
    public static void drivePosBased(int F_B_L_R, double vel) {
        if (F_B_L_R == 1) {
            global.FrontLeft.setVelocity(vel);
            global.FrontRight.setVelocity(vel);
            global.BackLeft.setVelocity(vel);
            global.BackRight.setVelocity(vel);
        } else if (F_B_L_R == 2) {
            global.FrontLeft.setVelocity(-vel);
            global.FrontRight.setVelocity(-vel);
            global.BackLeft.setVelocity(-vel);
            global.BackRight.setVelocity(-vel);
        } else if (F_B_L_R == 3) {
            global.FrontLeft.setVelocity(-vel);
            global.FrontRight.setVelocity(vel);
            global.BackLeft.setVelocity(vel);
            global.BackRight.setVelocity(-vel);
        } else if (F_B_L_R == 4) {
            global.FrontLeft.setVelocity(vel);
            global.FrontRight.setVelocity(-vel);
            global.BackLeft.setVelocity(-vel);
            global.BackRight.setVelocity(vel);
        }
    }

    // drives using the setPower Function
    // if F_B_L_R is 1 it will go forward
    // if F_B_L_R is 2 it will go backward
    // if F_B_L_R is 3 it will go left
    // if F_B_L_R is 4 it will go right
    public static void drivePwrBased(int F_B_L_R, double pwr) {

        if (F_B_L_R == 1) {
            global.FrontLeft.setPower(pwr);
            global.FrontRight.setPower(pwr);
            global.BackLeft.setPower(pwr);
            global.BackRight.setPower(pwr);
        } else if (F_B_L_R == 2) {
            global.FrontLeft.setPower(-pwr);
            global.FrontRight.setPower(-pwr);
            global.BackLeft.setPower(-pwr);
            global.BackRight.setPower(-pwr);
        } else if (F_B_L_R == 3) {
            global.FrontLeft.setPower(-pwr);
            global.FrontRight.setPower(pwr);
            global.BackLeft.setPower(pwr);
            global.BackRight.setPower(-pwr);
        } else if (F_B_L_R == 4) {
            global.FrontLeft.setPower(pwr);
            global.FrontRight.setPower(-pwr);
            global.BackLeft.setPower(-pwr);
            global.BackRight.setPower(pwr);
        }

    }

    public static void RotatePwrBased(boolean L_R, double pwr) {
        if (L_R) {
            global.FrontLeft.setPower(-pwr);
            global.FrontRight.setPower(pwr);
            global.BackLeft.setPower(-pwr);
            global.BackRight.setPower(pwr);
        } else if (!L_R) {
            global.FrontLeft.setPower(pwr);
            global.FrontRight.setPower(-pwr);
            global.BackLeft.setPower(pwr);
            global.BackRight.setPower(-pwr);
        }
    }

    public static void RotatePosBased(boolean L_R, double vel) {
        if (L_R) {
            global.FrontLeft.setVelocity(-vel);
            global.FrontRight.setVelocity(vel);
            global.BackLeft.setVelocity(-vel);
            global.BackRight.setVelocity(vel);
        } else if (!L_R) {
            global.FrontLeft.setVelocity(vel);
            global.FrontRight.setVelocity(-vel);
            global.BackLeft.setVelocity(vel);
            global.BackRight.setVelocity(-vel);
        }
    }
}
