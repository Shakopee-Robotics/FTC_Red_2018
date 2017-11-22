package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by nmckelvey on 11/18/17.
 */

@Autonomous(name="Auto Park")
public class FTCRedAutoTimePark extends LinearOpMode {
   FTCRedHardware2 robot = new FTCRedHardware2();
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        robot.leftGlyph.setPosition(.2);
        robot.rightGlyph.setPosition(.8);
        robot.frontLeftMotor.setPower(.1);
        robot.frontRightMotor.setPower(.1);
        robot.rearLeftMotor.setPower(.1);
        robot.rearRightMotor.setPower(.1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2)) {
            telemetry.addData("Path", "Leg 1: %2f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.leftGlyph.setPosition(.2);
        robot.rightGlyph.setPosition(.8);
        robot.frontLeftMotor.setPower(.5);
        robot.rearLeftMotor.setPower(.5);
        robot.frontRightMotor.setPower(-.5);
        robot.rearRightMotor.setPower(-.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.25)) {
            telemetry.addData("Path", "Leg 1: %2f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.leftGlyph.setPosition(.2);
        robot.rightGlyph.setPosition(.8);
        robot.frontLeftMotor.setPower(0);
        robot.rearLeftMotor.setPower(0);
        robot.frontRightMotor.setPower(0);
        robot.rearRightMotor.setPower(0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }
}
