package org.firstinspires.ftc.teamcode;
/**
 * Created by natemckelvey on 10/14/16.
 */

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * This file provides basic Telop driving for a simple mecanum drive robot.
 * This OpMode uses the FTCRedHardware1 hardware class to define the devices on the robot.
 * All device access is managed through the FTCRedHardware1 class.
 * This particular OpMode executes a code to run the mecanum wheels to allow for them to work correctly
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */


@TeleOp(name="FTC Red Mec-Op", group="FTCRed")
public class FTCRedMecanumDrive extends OpMode {

    /* Declare OpMode members. */
    FTCRedHardware1 robot = new FTCRedHardware1(); // use the class created to define a robot's hardware

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello FTC Red Driver");    //
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double ch1;
        double ch2;
        double ch3;
        double ch4;
        double ly;
        double ry;

        ch1 = gamepad1.right_stick_x;
        ch2 = -gamepad1.right_stick_y;
        ch3 = -gamepad1.left_stick_y;
        ch4 = -gamepad1.left_stick_x;
        ly = gamepad2.left_stick_y;
        ry = gamepad2.right_stick_y;

        /*
        <<<<DRIVETRAIN>>>>
        */
        robot.frontLeftMotor.setPower(ch3 + ch1 - ch4);
        robot.rearLeftMotor.setPower(ch3 + ch1 + ch4);
        robot.rearRightMotor.setPower(ch3 - ch1 + ch4);
        robot.frontRightMotor.setPower(ch3 - ch1 - ch4);
        /*
        <<<<LIFT MOTORS>>>>
        */
        robot.lowerLiftMotor.setPower(ly);
        robot.upperLiftMotor.setPower(ry);



     /*
     * Code to run ONCE after the driver hits STOP
     */
    }
}