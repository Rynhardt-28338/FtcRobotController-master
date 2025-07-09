package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Teleop extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        boolean wasCiralPresed;

        double forwhard;

        double strafe;

        double turn;

        String acshon = "";

        String gripperPos = "";

        double armAjustment = 0;

        double SlowPos = 1.0;

        RobotState robotState = new RobotState();

        DriveSubsestem driveSubsestem = new DriveSubsestem(hardwareMap,telemetry);
        ArmSubSebstem armSubSebstem = new ArmSubSebstem(hardwareMap, telemetry);
        GripperSubsestem gripperSubsestem = new GripperSubsestem(hardwareMap,telemetry);

        waitForStart();

        while (opModeIsActive()) {

            if (robotState.getSlidePos() >= 50) {

                SlowPos = 0.3;

            } else {

                SlowPos = 1;

            }

            strafe = (SlowPos * gamepad1.right_stick_x);

            forwhard = (SlowPos * gamepad1.right_stick_y);

            turn = (SlowPos * gamepad1.left_stick_x);

            if (gamepad1.b) {

                armAjustment = 0;
                acshon = "home";

            } else if (gamepad1.y) {

                armAjustment = 0;
                acshon = "sample";

            } else if (gamepad1.x) {

                armAjustment = 0;
                acshon = "spesement";

            } else if (gamepad1.a) {

                armAjustment = 0;
                acshon = "pickup";

            } else  if (gamepad1.circle) {

                gripperPos = "open";

            } else if (gamepad1.start) {

                gripperPos = "closed";

            } else if (gamepad1.left_trigger >= 0.1) {

                armAjustment = armAjustment - 0.8;

            }

            driveSubsestem.drive(forwhard,strafe,turn);
            armSubSebstem.moveArm(((int) armAjustment),acshon,robotState);
            gripperSubsestem.moveGripper(gripperPos,0.5,robotState);

            telemetry.addData("triger: ",gamepad1.left_trigger);
            telemetry.addData("speed limet: ", SlowPos);
            telemetry.update();

        }
    }
}

