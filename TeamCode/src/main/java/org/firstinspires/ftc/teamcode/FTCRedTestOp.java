package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by nmckelvey on 10/23/17.
*/
@TeleOp(name="FTC Red Test", group="FTCRed")
public class FTCRedTestOp extends OpMode {

    /* Declare OpMode members. */
    FTCRedTestHardware robot = new FTCRedTestHardware(); // use the class created to define a robot's hardware
    NormalizedColorSensor colorSensor;
    double glyphseta = 1;
    double glyphsetb = 0;
    double          clawOffset  = 0 ;                  // Servo mid position
    double    UP_SPEED;                 // sets rate to move servo
    double    DOWN_SPEED;       // sets rate to move servo
    boolean rgc;
    boolean hs;
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        // Initialize the hardware variables.
        robot.init(hardwareMap);
        rgc = true;
        hs = false;
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello FTC Red Driver");    //
        //Setting Encoder limit Values
        uEncoderMax = 1250.00;
        uEncoderMin = -20.0;
        lEncoderMax = 1900;
        lEncoderMin = -20;
        robot.init(hardwareMap);
        colorSensor = hardwareMap.get(NormalizedColorSensor.class, "sensor_color");
        ((SwitchableLight)colorSensor).enableLight(true);
        // Send telemetry message to signify robot waiting;
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
        robot.rightGlypha.setPosition(.65);   //Sets the upper right glyph servo to an open position
        robot.rightGlyphb.setPosition(.35);   //Sets the lower right glyph servo to an open position
        robot.leftGlypha.setPosition(.35);    //Sets the upper left glyph servo to an open position
        robot.leftGlyphb.setPosition(.65);    //Sets the upper left glyph servo to an open position
        robot.frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        //Sets the gamepad joysticks to shorter doubles
        telemetry.addData("Color arm", robot.colorArm.getPosition());
        telemetry.addData("right glypha", robot.rightGlypha.getPosition());
        ch1 = gamepad1.right_stick_x;
        ch2 = gamepad1.right_stick_y;
        ch3 = -gamepad1.left_stick_y;
        ch4 = -gamepad1.left_stick_x;
        ly = -gamepad2.left_stick_y;
        ry = -gamepad2.right_stick_y;
            //Sets the drivetrain and lift encoders to shorter doubles
            flEncoderVal = robot.frontLeftMotor.getCurrentPosition();
            frEncoderVal = robot.frontRightMotor.getCurrentPosition();
            uEncoderVal = robot.upperLiftMotor.getCurrentPosition();
            lEncoderVal = robot.lowerLiftMotor.getCurrentPosition();
        //Adds gamepad2 joystick y-values to the driverstation
        telemetry.addData("left stick", ly);
        telemetry.addData("right stick", ry);
            //adds encoder values to display on driver station
            telemetry.addData("Lower Lift Encoder", lEncoderVal);
            telemetry.addData("Upper Lift Encoder", uEncoderVal);
            telemetry.addData("right drive", frEncoderVal);
            telemetry.addData("left drive", flEncoderVal);

        telemetry.addData("COLOR VAL ", colorSensor.getNormalizedColors().toString());
        colory = colorSensor.getNormalizedColors().red;
        telemetry.addData("COLOR VAL RED ", colory);


        /*
        <<<<DRIVETRAIN>>>>
        */
        /**
         * Drivetrain uses mecanum wheels that move laterally
         * on the x and y plane, allowing for more agile movement
         * while staying inside the same parameters are typical wheels.
         * The motors respond differently to each input from
         * the user's control, utilizing the normal maneuvers seen
         * from regular wheels in a tank drive, while also being
         * able to move side to side by reversing the front
         * right motor and the rear left motor from the front
         * left motor and the rear right motor.
         */

            robot.frontLeftMotor.setPower((ch3 + ch1 - ch4));
            robot.rearLeftMotor.setPower(ch3 + ch1 + ch4);
            robot.rearRightMotor.setPower((ch3 - ch1 - ch4));
            robot.frontRightMotor.setPower(ch3 - ch1 + ch4);


        /*
        <<<<LIFT MOTORS>>>>
        */
        /**
         * This code implements the lift motor encoders
         * to keep the lift motors from reaching farther bounds
         * than they were intended, to keep from robot destruction
         */
        if ((ly < 0 && lEncoderVal > lEncoderMin) || (ly >= 0 && lEncoderVal <= lEncoderMax)){
            robot.lowerLiftMotor.setPower(ly);
        } else{
            robot.lowerLiftMotor.setPower(0);
        } if ((ry <= 0 && uEncoderVal > uEncoderMin) || (ry >= 0 && uEncoderVal < uEncoderMax)) {
            robot.upperLiftMotor.setPower(ry);
        } else {
            robot.upperLiftMotor.setPower(0);
        }
        /*
        <<<<SERVOS>>>>
         */
        if(gamepad2.dpad_up){
            clawOffset = 0;
        } else if(gamepad2.dpad_down){
            clawOffset = 1;
        }
        /*
        if(gamepad2.dpad_left && rgc == true){
            rgc = false;
        } else if(gamepad2.dpad_left && rgc == false){
            rgc = true;
        }
        */
        if (gamepad2.right_bumper ) {
            robot.rightGlypha.setPosition(.65);   //Sets the upper right glyph servo to an open position
            robot.rightGlyphb.setPosition(.35);   //Sets the lower right glyph servo to an open position
            robot.leftGlypha.setPosition(.35);    //Sets the upper left glyph servo to an open position
            robot.leftGlyphb.setPosition(.65);    //Sets the upper left glyph servo to an open position
        } else if (gamepad2.left_bumper ) {
            robot.rightGlypha.setPosition(0);   //Sets the upper right glyph servo to a closed position
            robot.rightGlyphb.setPosition(1);   //Sets the lower right glyph servo to a closed position
            robot.leftGlypha.setPosition(1);    //Sets the upper left glyph servo to a closed position
            robot.leftGlyphb.setPosition(0);    //Sets the upper left glyph servo to a closed position
        } else if(gamepad2.a ){
            robot.leftGlypha.setPosition(.7);   //Brings upper left glyph servo mechanism to a middle position
            robot.leftGlyphb.setPosition(.3);   //Brings lower left glyph servo mechanism to a middle position
            robot.rightGlypha.setPosition(.3);  //Brings upper right glyph servo mechanism to a middle position
            robot.rightGlyphb.setPosition(.7);  //Brings lower right glyph servo mechanism to a middle position
        }
        if(gamepad2.start){
            //Resets the lift encoders in the rare case they become off-sync during a match
            robot.upperLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.lowerLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.upperLiftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.lowerLiftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        clawOffset = Range.clip(clawOffset, 0, 1);
        UP_SPEED =  (gamepad2.left_trigger  * 0.01);
        DOWN_SPEED = (gamepad2.right_trigger  * 0.01);
        telemetry.addData("UP_SPEED ", UP_SPEED);
        telemetry.addData("down speed ", DOWN_SPEED);
        telemetry.update();
        robot.colorArm.setPosition(0 + clawOffset);
        if (gamepad2.left_trigger > 0){
            clawOffset -= UP_SPEED;
        } else if (gamepad2.right_trigger > 0){
            clawOffset += DOWN_SPEED;
        }


    /*
     * Code to run ONCE after the driver hits STOP
     */
    }
    //double declarations
    private double ch1;
    private double ch2;
    private double ch3;
    private double ch4;
    private double lEncoderMax;
    private double lEncoderMin;
    private double uEncoderMin;
    private double lEncoderVal;
    private double uEncoderMax;
    private double uEncoderVal;
    private double ly;
    private double ry;

    private double flEncoderVal;
    private double frEncoderVal;
    private double colory;


}