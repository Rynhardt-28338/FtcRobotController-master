package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp
public class Teleop extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        double forwhard;

        double strafe;

        double turn;

        String acshon = "";

        String gripperPos = "";

        DriveSubsestem driveSubsestem = new DriveSubsestem(hardwareMap,telemetry);
        ArmSubSebstem armSubSebstem = new ArmSubSebstem(hardwareMap, telemetry);
        GripperSubsestem gripperSubsestem = new GripperSubsestem(hardwareMap,telemetry);

        waitForStart();

        while (opModeIsActive()) {

            strafe = gamepad1.right_stick_x;

            forwhard = gamepad1.right_stick_y;

            turn = gamepad1.left_stick_x;

            driveSubsestem.drive(forwhard,strafe,turn);

            if (gamepad1.b) {

                acshon = "home";

            } else if (gamepad1.y) {

                acshon = "sample";

            } else if (gamepad1.x) {

                acshon = "spesement";

            } else if (gamepad1.a) {

                acshon = "pickup";

            } else  if (gamepad1.right_bumper) {

                gripperPos = "open";

            } else {

                gripperPos = "closed";

            }

            armSubSebstem.moveArm(0,acshon);
            gripperSubsestem.moveGripper(gripperPos,0.5);

            telemetry.update();

        }

    }



}
