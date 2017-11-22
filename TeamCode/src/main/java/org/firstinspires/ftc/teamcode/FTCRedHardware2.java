package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by nmckelvey on 10/23/17.
 */

public class FTCRedHardware2 {
    /**
     * This is NOT an opmode.
     *
     * This class can be used to define all the specific hardware for a single robot.
     * In this case that robot is a FTC RED'S SICK MECANUM DRIVEN DEATHMACHINE.
     * See your mom and other classes starting with "Not A Phase" for usage examples.
     *
     * This hardware class assumes the following device names have been configured on the robot:
     * Note:  All names are lower case and some have single spaces between words.
     *
     * Motor channel:  Left  drive motor:        "left_drive"
     * Motor channel:  Right drive motor:        "right_drive"
     * Motor channel:  Manipulator drive motor:  "left_arm"
     * Servo channel:  Servo to open left claw:  "left_hand"
     * Servo channel:  Servo to open right claw: "right_hand"
     */

    /* Public OpMode members. */
    public DcMotor frontLeftMotor   = null;
    public DcMotor frontRightMotor  = null;
    public DcMotor rearLeftMotor    = null;
    public DcMotor rearRightMotor   = null;
    public DcMotor lowerLiftMotor   = null;
    public DcMotor upperLiftMotor   = null;
    public Servo leftGlyph          = null;
    public Servo rightGlyph         = null;
    public Servo jewel              = null;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();
    public static final double MID_SERVO  =  0.0 ;
    public static final double ARM_UP   =  0.6 ;
    public static final double ARM_DOWN = -0.6 ;

    /* Constructor */
    public FTCRedHardware2(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        frontLeftMotor   = hwMap.dcMotor.get("fleft");
        frontRightMotor  = hwMap.get(DcMotor.class,"fright");
        rearLeftMotor   = hwMap.get(DcMotor.class,"rleft");
        rearRightMotor  = hwMap.get(DcMotor.class,"rright");
        lowerLiftMotor  = hwMap.get(DcMotor.class,"llift");
        upperLiftMotor  = hwMap.get(DcMotor.class,"ulift");
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        rearRightMotor.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        upperLiftMotor.setDirection(DcMotor.Direction.FORWARD);
        lowerLiftMotor.setDirection(DcMotor.Direction.REVERSE);
        // Set all motors to zero power
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);
        lowerLiftMotor.setPower(0);
        upperLiftMotor.setPower(0);
        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        upperLiftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lowerLiftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //lowerLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      //  upperLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // Define and initialize ALL installed servos.
        leftGlyph  = hwMap.get(Servo.class, "gleft");
        rightGlyph = hwMap.get(Servo.class, "gright");
        leftGlyph.setPosition(0);
        rightGlyph.setPosition(1);
        jewel = hwMap.get(Servo.class, "jew");
        jewel.setPosition(1);

    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */
    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}
  /*

        leftClaw = hwMap.servo.get("left_claw");
        leftClaw.setPosition(OPEN_SERVO);
        rightCLaw = hwMap.servo.get("right_claw");
        rightCLaw.setPosition(MID_SERVO);
        rightBeaconClaw = hwMap.servo.get("right_bc");
        rightBeaconClaw.setPosition(MID_SERVO);
        leftBeaconClaw = hwMap.servo.get("left_bc");
        leftBeaconClaw .setPosition(CLOSE_SERVO);
        */


