package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by nmckelvey on 10/23/17.
*/
@TeleOp(name="FTC Red Test", group="FTCRed")
public class FTCRedTestOp extends OpMode {

    /* Declare OpMode members. */
    FTCRedTestHardware robot = new FTCRedTestHardware(); // use the class created to define a robot's hardware

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
        double encoderLim;
        double encoderVal;
        double ly;
        double ry;
        ch1 = gamepad1.right_stick_x;
        ch2 = gamepad1.right_stick_y;
        ch3 = -gamepad1.left_stick_y;
        ch4 = -gamepad1.left_stick_x;
        ly = -gamepad2.left_stick_y;
        ry = -gamepad2.right_stick_y;

        /*
        <<<<DRIVETRAIN>>>>
        */
        /**
         * Encoder for linear lift declarations
         encoderLim = -6000.00;
         encoderVal = robot.liftMotor.getCurrentPosition();
         telemetry.addData("DANK MEMES", encoderVal);
         **/
        robot.frontLeftMotor.setPower(ch3 + ch1 - ch4);
        robot.rearLeftMotor.setPower(ch3 + ch1 + ch4);
        robot.rearRightMotor.setPower(ch3 - ch1 + ch4);
        robot.frontRightMotor.setPower(ch3 - ch1 - ch4);
        /*
        <<<<LIFT MOTORS>>>>
        */
        /*
        if (gamepad1.a && ly > 0) {
            ch2 = -1;
            robot.lowerLiftMotor.setPower(ly);
        } if (gamepad1.b && ly > 0) {
            ch2 = -1;
            robot.lowerLiftMotor.setPower(-ly);
        }
        if (gamepad1.a && ry > 0) {
            ch2 = -1;
            robot.upperLiftMotor.setPower(ry);
        }
        if (gamepad1.b && ry > 0) {
            ch2 = -1;
            robot.upperLiftMotor.setPower(-ry);
        }
        */
        robot.lowerLiftMotor.setPower(ly);
        robot.upperLiftMotor.setPower(ry);

        if (gamepad2.right_bumper) {
            robot.rightGlyph.setPosition(.6);
            robot.leftGlyph.setPosition(0);

        } else if (gamepad2.left_bumper) {
            robot.rightGlyph.setPosition(0);
            robot.leftGlyph.setPosition(.6);
        }


            //robot.liftMotor.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
        /**
         *linear lift code
         if(encoderVal >= encoderLim && gamepad2.right_trigger > 0){
         robot.liftMotor.setPower(-gamepad2.right_trigger);
         } else if (gamepad2.left_trigger > 0) {
         robot.liftMotor.setPower(gamepad2.left_trigger);
         } else {
         robot.liftMotor.setPower(0);
         }
         /*
         <<<SERVOS>>>
         */

        /**
         if (gamepad2.y) {
         robot.leftClaw.setPosition(-robot.OPEN_SERVO);
         } else if (gamepad2.a) {
         robot.leftClaw.setPosition(robot.OPEN_SERVO);
         } else if (gamepad2.dpad_up) {
         robot.rightCLaw.setPosition(robot.MID_SERVO + 1);
         } else if (gamepad2.dpad_down) {
         robot.rightCLaw.setPosition(robot.MID_SERVO - 1);
         } else if (gamepad2.right_bumper) {
         robot.rightBeaconClaw.setPosition(robot.MID_SERVO + .6);
         } else if (gamepad2.b) {
         robot.rightBeaconClaw.setPosition(robot.MID_SERVO - .6);
         } else if (gamepad2.left_bumper) {
         robot.leftBeaconClaw.setPosition(robot.MID_SERVO - .6);
         } else if (gamepad2.dpad_left) {
         robot.leftBeaconClaw.setPosition(robot.MID_SERVO + .6);
         }
         */
        /*

     * Code to run ONCE after the driver hits STOP
     */
    }
}