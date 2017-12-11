package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto Park")
public class FTCRedAutoTimePark extends LinearOpMode {
   FTCRedTestHardware robot = new FTCRedTestHardware(); //Declare the Robot Hardware to be used
    private ElapsedTime runtime = new ElapsedTime(); //Creates a runtime to elapse during the autonomous
    @Override
    public void runOpMode() {
        robot.init(hardwareMap); //Initialize hardware

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        //Drives the robot forward at 1/10th speed for two seconds
        robot.leftGlypha.setPosition(.3);   //Brings upper left glyph servo mechanism to a middle position
        robot.leftGlyphb.setPosition(.7);   //Brings lower left glyph servo mechanism to a middle position
        robot.rightGlypha.setPosition(.7);  //Brings upper right glyph servo mechanism to a middle position
        robot.rightGlyphb.setPosition(.3);  //Brings lower right glyph servo mechanism to a middle position
        robot.frontLeftMotor.setPower(.2);  //Sets the front left motor to 1/10th forward speed
        robot.frontRightMotor.setPower(.2); //Sets the front right motor to 1/10th forward speed
        robot.rearLeftMotor.setPower(.2);   //Sets the rear left motor to 1/10th forward speed
        robot.rearRightMotor.setPower(.2);  //Sets the rear right motor to 1/10th forward speed
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2)) { //465 at the end after using encoders
            telemetry.addData("Path", "Leg 1: %2f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        //Parks robot
        robot.leftGlypha.setPosition(.7);   //Brings upper left glyph servo mechanism to a middle position
        robot.leftGlyphb.setPosition(.3);   //Brings lower left glyph servo mechanism to a middle position
        robot.rightGlypha.setPosition(.3);  //Brings upper right glyph servo mechanism to a middle position
        robot.rightGlyphb.setPosition(.7);  //Brings lower right glyph servo mechanism to a middle position
        robot.frontLeftMotor.setPower(0);   //Sets the front left motor to be stopped
        robot.rearLeftMotor.setPower(0);    //Sets the front right motor to be stopped
        robot.frontRightMotor.setPower(0);  //Sets the rear left motor to be stopped
        robot.rearRightMotor.setPower(0);   //Sets the rear right motor to be stopped
        telemetry.addData("Path", "Complete"); //displays that the autonomous has completed
        telemetry.update();

        //name="NeveRest 40 Gearmotor", ticksPerRev=1120, gearing=40, maxRPM=160, orientation=Rotation.CCW)
    }
}
