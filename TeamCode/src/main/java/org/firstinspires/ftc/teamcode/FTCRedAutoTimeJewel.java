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

@Autonomous(name="Auto Jewel")
public class FTCRedAutoTimeJewel extends LinearOpMode {
    FTCRedHardware2 robot = new FTCRedHardware2();
    private ElapsedTime runtime = new ElapsedTime();
    NormalizedColorSensor colorSensor;

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
        colorSensor = hardwareMap.get(NormalizedColorSensor.class, "sensor_color");
        ((SwitchableLight)colorSensor).enableLight(true);


        // hsvValues is an array that will hold the hue, saturation, and value information.
        float[] hsvValues = new float[3];
        final float values[] = hsvValues;

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);


        robot.init(hardwareMap);
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        robot.leftGlyph.setPosition(LO);
        robot.rightGlyph.setPosition(RO);
        robot.jewel.setPosition(JU);
        robot.frontLeftMotor.setPower(.5);
        robot.frontRightMotor.setPower(.5);
        robot.rearLeftMotor.setPower(.1);
        robot.rearRightMotor.setPower(.1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1)) {
            telemetry.addData("Path", "Leg 1: %2f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.leftGlyph.setPosition(LO);
        robot.rightGlyph.setPosition(RO);
        robot.jewel.setPosition(JU);
        robot.frontLeftMotor.setPower(-.5);
        robot.rearLeftMotor.setPower(-.5);
        robot.frontRightMotor.setPower(.5);
        robot.rearRightMotor.setPower(.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < .5)) {
            telemetry.addData("Path", "Leg 1: %2f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.leftGlyph.setPosition(LO);
        robot.rightGlyph.setPosition(RO);
        robot.jewel.setPosition(JU);
        robot.frontLeftMotor.setPower(-.5);
        robot.rearLeftMotor.setPower(-.5);
        robot.frontRightMotor.setPower(-.4);
        robot.rearRightMotor.setPower(-.4);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < .5)) {
            telemetry.addData("Path", "Leg 1: %2f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        robot.leftGlyph.setPosition(LO);
        robot.rightGlyph.setPosition(RO);
        robot.jewel.setPosition(JD);
        robot.frontLeftMotor.setPower(0);
        robot.rearLeftMotor.setPower(0);
        robot.frontRightMotor.setPower(0);
        robot.rearRightMotor.setPower(0);
        colora = true;
        // Read the sensor
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
            if(colorSensor.getNormalizedColors().red > 0 && runtime.seconds() > 2){
                robot.frontLeftMotor.setPower(.1);
                robot.frontRightMotor.setPower(.1);
                robot.rearLeftMotor.setPower(.1);
                robot.rearRightMotor.setPower(.1);
                colora = false;
            } if (colorSensor.getNormalizedColors().red <= 0 && runtime.seconds() > 2 && colora == true){
                robot.frontLeftMotor.setPower(-.1);
                robot.rearLeftMotor.setPower(-.1);
                robot.frontRightMotor.setPower(-.1);
                robot.rearRightMotor.setPower(-.1);
                colora = false;
            } else if(colora == false && runtime.seconds() > 4
                    ){
                robot.frontLeftMotor.setPower(0);
                robot.rearLeftMotor.setPower(0);
                robot.frontRightMotor.setPower(0);
                robot.rearRightMotor.setPower(0);
            }

        }
        // telemetry.addData("Path", "Complete");
       // telemetry.update();
    }
}
