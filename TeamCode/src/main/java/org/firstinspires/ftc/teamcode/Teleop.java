package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class Teleop extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        double armSpeed = 7.5;

        double wristSpeed = 0.015;

        double forwhard;

        double strafe;

        double turn;

        String acshon = "";

        String gripperPos = "open";

        double armAjustment = 0;

        int slideAjustment = 0;

        double SlowPos = 1.0;

        double wristPos = 0.5;

        RobotState robotState = new RobotState();

        DriveSubsestem driveSubsestem = new DriveSubsestem(hardwareMap,telemetry);
        ArmSubSebstem armSubSebstem = new ArmSubSebstem(hardwareMap, telemetry);
        GripperSubsestem gripperSubsestem = new GripperSubsestem(hardwareMap,telemetry);

        ElapsedTime elapsedTime = new ElapsedTime();


        waitForStart();

        while (opModeIsActive()) {

            // Check if the slide is far out. If so, we slow down the drive speed to prevent tipping over
            if (robotState.getSlidePos() >= 50) {

                SlowPos = 0.3;

            } else {

                SlowPos = 1;

            }

            // Set the robot drive values
            strafe = (SlowPos * gamepad1.right_stick_x);
            forwhard = (SlowPos * gamepad1.right_stick_y);
            turn = (SlowPos * gamepad1.left_stick_x);

            if (gamepad1.b) {
                slideAjustment = 0;
                wristPos = 0.5;
                armAjustment = 0;
                acshon = "home";

            } else if (gamepad1.y) {
                slideAjustment = 0;
                wristPos = 0.5;
                armAjustment = 0;
                acshon = "sample";

            } else if (gamepad1.a && (!(acshon == "sample"))) {
                slideAjustment = 0;
                wristPos = 0.5;
                armAjustment = 0;
                acshon = "pickup";
            }

            // Move the wrist (left / right)
            if (gamepad1.start) {

                wristPos = wristPos + wristSpeed;

            } else if (gamepad1.back) {

                wristPos = wristPos - wristSpeed;

            }

            // Move the arm up / down
            if (gamepad1.right_bumper) {

                // Move arm upwards
                armAjustment = armAjustment + armSpeed;

            } else if (gamepad1.right_trigger >= 0.1) {

                // Move arm downwards
                armAjustment = armAjustment - armSpeed;

            }

            // Move the slide in / out
            if (gamepad1.left_bumper) {

                // Move slide out (extend)
                slideAjustment++;slideAjustment++;slideAjustment++;slideAjustment++;slideAjustment++;slideAjustment++;

            } else if (gamepad1.left_trigger >= 0.1) {

                // Move slide in (retract)
                slideAjustment--;slideAjustment--;slideAjustment--;slideAjustment--;slideAjustment--;slideAjustment--;

            }

            if (gamepad1.x) {

                if ((robotState.getArmPos() >= (robotState.getArmTarget() - 1400)) && (acshon == "spesementFinish" || acshon == "spesementRedy")) {

                    elapsedTime.reset();
                    acshon = "spesementFinish";

                } else {

                    slideAjustment = 0;
                    wristPos = 0.5;
                    armAjustment = 0;
                    acshon = "spesementRedy";

                }
                if ((robotState.getSlideTarget() >= (robotState.getSlideTarget() - 10)) && (acshon == "spesementFinish") && (elapsedTime.milliseconds() >= 200)) {

                    gripperPos = "open";

                }
            }

            driveSubsestem.drive(forwhard,strafe,turn);
            armSubSebstem.moveArm(((int) armAjustment),slideAjustment,acshon,robotState);
            gripperSubsestem.moveGripper(gamepad1,wristPos,robotState);

            telemetry.addData("gripper pos: ", gripperPos);
            telemetry.addData("triger: ",gamepad1.left_trigger);
            telemetry.addData("speed limet: ", SlowPos);
            telemetry.addData("object detected: ", robotState.getGripperDetectedObject());
            telemetry.update();

        }
    }
}

