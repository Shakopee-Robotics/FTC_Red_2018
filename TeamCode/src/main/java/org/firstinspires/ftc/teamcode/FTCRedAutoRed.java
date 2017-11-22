package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by nmckelvey on 11/18/17.
 */

@Autonomous(name="Auto Jewel Red")
public class FTCRedAutoRed extends LinearOpMode {
    FTCRedHardware2 robot = new FTCRedHardware2(); // gets the FTCRedHardware2 class
    private ElapsedTime runtime = new ElapsedTime(); // Creates new time elapse
    NormalizedColorSensor colorSensor; // bridges the NormalizedColorSensor class to the object "colorSensor"

    double JU;
    double JD;
    double LO;
    double LC;
    double RO;
    double RC;
    boolean colora;

    @Override
    public void runOpMode() {
        LO = .2; //leftGlyph Open position
        RO = .8; //rightGlyph open position
        LC = 1; // leftGlyph closed position
        RC = 0; //rightGlyph closed position
        JU = 1;
        JD = .3;
        colora = true; //sets variable "colora" as true
        colorSensor = hardwareMap.get(NormalizedColorSensor.class, "sensor_color"); //
        ((SwitchableLight) colorSensor).enableLight(true);


        // hsvValues is an array that will hold the hue, saturation, and value information.
        float[] hsvValues = new float[3];
        final float values[] = hsvValues;

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);


        robot.init(hardwareMap);
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    // adds text "Status: Ready to run" onto driver station
        telemetry.update();  // updates the driver station to display new data
        runtime.reset(); // Resets runtime to 0

        waitForStart();             // Wait for the game to start (driver presses PLAY)
        robot.leftGlyph.setPosition(.6);  //Brings left glyph servo mechanism to a middle position
        robot.rightGlyph.setPosition(.4);  //Brings right glyph servo mechanism to a middle position
        //sets all motors to 2/10ths full speed in the forward direction
        robot.frontLeftMotor.setPower(.2);
        robot.frontRightMotor.setPower(.2);
        robot.rearLeftMotor.setPower(.2);
        robot.rearRightMotor.setPower(.2);

        runtime.reset(); // Resets runtime to 0
        while (opModeIsActive() && (runtime.seconds() < 2)) {
            telemetry.addData("Path", "Leg 1: %2f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.frontLeftMotor.setPower(0);
        robot.rearLeftMotor.setPower(0);
        robot.frontRightMotor.setPower(0);
        robot.rearRightMotor.setPower(0);
        runtime.reset();    // Resets runtime to 0
        while (opModeIsActive()&& runtime.seconds() < 10) {
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
                robot.rightGlyph.setPosition(.4);
                robot.frontLeftMotor.setPower(-.5);
                robot.rearLeftMotor.setPower(-.5);
                robot.frontRightMotor.setPower(.5);
                robot.rearRightMotor.setPower(.5);
                colora = false;

            } if (colorSensor.getNormalizedColors().red <= 0 && runtime.seconds() > 2 && colora == true) {
                robot.rightGlyph.setPosition(.8);
                robot.leftGlyph.setPosition(0);
                robot.frontLeftMotor.setPower(.5);
                robot.rearLeftMotor.setPower(.5);
                robot.frontRightMotor.setPower(-.5);
                robot.rearRightMotor.setPower(-.5);
            } else if (colora == false && runtime.seconds() > 2.5) {
                robot.rightGlyph.setPosition(.4);
                robot.leftGlyph.setPosition(.6);
                robot.frontLeftMotor.setPower(0);
                robot.rearLeftMotor.setPower(0);
                robot.frontRightMotor.setPower(0);
                robot.rearRightMotor.setPower(0);
            }
        }

    }

}
