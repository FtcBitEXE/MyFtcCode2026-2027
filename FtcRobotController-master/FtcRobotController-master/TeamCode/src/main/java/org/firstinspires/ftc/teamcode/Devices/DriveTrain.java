package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Global;

public class DriveTrain {

    private static Global global;

    //initializes movement that uses velocity
    //only use if using velocity based movement
    public static void posBasedMovementInit() {
        global.FLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        global.FLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        global.FLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        global.FRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        global.FRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        global.FRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        global.BLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        global.BLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        global.BLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);




        global.BRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        global.BRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        global.BRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }

    // drives using the setVelocity Function
    //vel must be a positive number
    // if F_B_L_R is 1 it will go forward
    // if F_B_L_R is 2 it will go backward
    // if F_B_L_R is 3 it will go left
    // if F_B_L_R is 4 it will go right
    public static void drivePosBased(int F_B_L_R, double vel) {
        if (F_B_L_R == 1) {
            global.FLeft.setVelocity(vel);
            global.FRight.setVelocity(vel);
            global.BLeft.setVelocity(vel);
            global.BRight.setVelocity(vel);
        } else if (F_B_L_R == 2) {
            global.FLeft.setVelocity(-vel);
            global.FRight.setVelocity(-vel);
            global.BLeft.setVelocity(-vel);
            global.BRight.setVelocity(-vel);
        } else if (F_B_L_R == 3) {
            global.FLeft.setVelocity(-vel);
            global.FRight.setVelocity(vel);
            global.BLeft.setVelocity(vel);
            global.BRight.setVelocity(-vel);
        } else if (F_B_L_R == 4) {
            global.FLeft.setVelocity(vel);
            global.FRight.setVelocity(-vel);
            global.BLeft.setVelocity(-vel);
            global.BRight.setVelocity(vel);
        }
    }

    // drives using the setPower Function
    // if F_B_L_R is 1 it will go forward
    // if F_B_L_R is 2 it will go backward
    // if F_B_L_R is 3 it will go left
    // if F_B_L_R is 4 it will go right
    public static void drivePwrBased(int F_B_L_R, double pwr) {

        if (F_B_L_R == 1) {
            global.FLeft.setPower(pwr);
            global.FRight.setPower(pwr);
            global.BLeft.setPower(pwr);
            global.BRight.setPower(pwr);
        } else if (F_B_L_R == 2) {
            global.FLeft.setPower(-pwr);
            global.FRight.setPower(-pwr);
            global.BLeft.setPower(-pwr);
            global.BRight.setPower(-pwr);
        } else if (F_B_L_R == 3) {
            global.FLeft.setPower(-pwr);
            global.FRight.setPower(pwr);
            global.BLeft.setPower(pwr);
            global.BRight.setPower(-pwr);
        } else if (F_B_L_R == 4) {
            global.FLeft.setPower(pwr);
            global.FRight.setPower(-pwr);
            global.BLeft.setPower(-pwr);
            global.BRight.setPower(pwr);
        }

    }

    public static void RotatePwrBased(boolean L_R, double pwr) {
        if (L_R) {
            global.FLeft.setPower(-pwr);
            global.FRight.setPower(pwr);
            global.BLeft.setPower(-pwr);
            global.BRight.setPower(pwr);
        } else if (!L_R) {
            global.FLeft.setPower(pwr);
            global.FRight.setPower(-pwr);
            global.BLeft.setPower(pwr);
            global.BRight.setPower(-pwr);
        }
    }

    public static void RotatePosBased(boolean L_R, double vel) {
        if (L_R) {
            global.FLeft.setVelocity(-vel);
            global.FRight.setVelocity(vel);
            global.BLeft.setVelocity(-vel);
            global.BRight.setVelocity(vel);
        } else if (!L_R) {
            global.FLeft.setVelocity(vel);
            global.FRight.setVelocity(-vel);
            global.BLeft.setVelocity(vel);
            global.BRight.setVelocity(-vel);
        }
    }

    public static void driveTeleOp(double y, double  x, double rx) {
        global.FLeft.setPower(y + x + rx);
        global.FRight.setPower(y - x + rx);
        global.BLeft.setPower(y - x - rx);
        global.BRight.setPower(y + x - rx);
    }



    //Khrish Methods Here:
}
