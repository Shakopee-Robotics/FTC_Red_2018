package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto alliance Blue")
public class FTCRedAutoBlue extends LinearOpMode {
    FTCRedTestHardware robot = new FTCRedTestHardware(); //Declare the Robot Hardware to be used
    private ElapsedTime runtime = new ElapsedTime(); //Creates a runtime to elapse during the autonomous
    NormalizedColorSensor colorSensor; // bridges the NormalizedColorSensor class to the object "colorSensor"
    boolean colora; //creates a boolean to be used
    final double FS = .1; //Forward Speed
    final double BS = -.1; //Backwards Speed
    final double RSP = .5; //Postitve rotation speed
    final double RSN = -.5; //Negative rotation speed

    @Override
    public void runOpMode() {
        colora = true; //sets boolean "colora" as true
        colorSensor = hardwareMap.get(NormalizedColorSensor.class, "sensor_color"); //Maps colorSensor to our hardware map
        ((SwitchableLight) colorSensor).enableLight(true); //Turns on the light to the color sensor


        // hsvValues is an array that will hold the hue, saturation, and value information.
        float[] hsvValues = new float[3];
        final float values[] = hsvValues;

        robot.init(hardwareMap);
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    // adds text "Status: Ready to run" onto driver station
        telemetry.update();  // updates the driver station to display new data
        runtime.reset(); // Resets runtime to 0

        waitForStart();             // Wait for the game to start (driver presses PLAY)
        robot.leftGlypha.setPosition(.7);   //Brings upper left glyph servo mechanism to a middle position
        robot.leftGlyphb.setPosition(.3);   //Brings lower left glyph servo mechanism to a middle position
        robot.rightGlypha.setPosition(.3);  //Brings upper right glyph servo mechanism to a middle position
        robot.rightGlyphb.setPosition(.7);  //Brings lower right glyph servo mechanism to a middle position
        //sets all motors to the specified forward direction
        robot.frontLeftMotor.setPower(FS);
        robot.frontRightMotor.setPower(FS);
        robot.rearLeftMotor.setPower(FS);
        robot.rearRightMotor.setPower(FS);

        runtime.reset(); // Resets runtime to 0
        while (opModeIsActive() && (runtime.seconds() < 2)) {
            //display elapsed time
            telemetry.addData("Path", "Leg 1: %2f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        //Stops robot
        robot.frontLeftMotor.setPower(0);
        robot.rearLeftMotor.setPower(0);
        robot.frontRightMotor.setPower(0);
        robot.rearRightMotor.setPower(0);
        runtime.reset();    // Resets runtime to 0
        while (opModeIsActive()&& runtime.seconds() < 10) {
            //displays color sensor values on driver station device
            telemetry.addData("COLOR VAL ", colorSensor.getNormalizedColors().toString());
            telemetry.addData("COLOR VAL RED ", colorSensor.getNormalizedColors().red);
            NormalizedRGBA colors = colorSensor.getNormalizedColors();
            telemetry.addLine()
                    .addData("a", "%.3f", colors.alpha)
                    .addData("r", "%.3f", colors.red)
                    .addData("g", "%.3f", colors.green)
                    .addData("b", "%.3f", colors.blue);
            telemetry.update();
            if (colorSensor.getNormalizedColors().red > 0 && runtime.seconds() > 2) {
                colora = false;
                //Knocks jewel to the right off platform
                while (runtime.seconds() > 2 && runtime.seconds() < 5){
                    robot.rightGlypha.setPosition(.8);   //Sets the upper right glyph servo to an open position
                    robot.rightGlyphb.setPosition(.2);   //Sets the lower right glyph servo to an open position
                    robot.leftGlypha.setPosition(.2);    //Sets the upper left glyph servo to an open position
                    robot.leftGlyphb.setPosition(.8);    //Sets the upper left glyph servo to an open position
                    //rotates robot clockwise
                    robot.frontLeftMotor.setPower(RSP);
                    robot.rearLeftMotor.setPower(RSP);
                    robot.frontRightMotor.setPower(RSN);
                    robot.rearRightMotor.setPower(RSN);
                }

            } if (colorSensor.getNormalizedColors().blue > 0 && runtime.seconds() > 2 && colora == true) {
                //knocks jewels to the left off platform
                while (runtime.seconds() > 2 && runtime.seconds() < 5) {
                    robot.rightGlypha.setPosition(0);   //Sets the upper right glyph servo to a closed position
                    robot.rightGlyphb.setPosition(1);   //Sets the lower right glyph servo to a closed position
                    robot.leftGlypha.setPosition(1);    //Sets the upper left glyph servo to a closed position
                    robot.leftGlyphb.setPosition(0);    //Sets the upper left glyph servo to a closed position
                    //rotates robot counter-clockwise
                    robot.frontLeftMotor.setPower(RSN);
                    robot.rearLeftMotor.setPower(RSN);
                    robot.frontRightMotor.setPower(RSP);
                    robot.rearRightMotor.setPower(RSP);
                }
            } else if (colora == true && runtime.seconds() > 2.5) {
                //keeps robot in a stand-still if no color is detected
                robot.leftGlypha.setPosition(.7);   //Brings upper left glyph servo mechanism to a middle position
                robot.leftGlyphb.setPosition(.3);   //Brings lower left glyph servo mechanism to a middle position
                robot.rightGlypha.setPosition(.3);  //Brings upper right glyph servo mechanism to a middle position
                robot.rightGlyphb.setPosition(.7);  //Brings lower right glyph servo mechanism to a middle position
                //stops motors
                robot.frontLeftMotor.setPower(0);
                robot.rearLeftMotor.setPower(0);
                robot.frontRightMotor.setPower(0);
                robot.rearRightMotor.setPower(0);
            }
        }

    }

}
