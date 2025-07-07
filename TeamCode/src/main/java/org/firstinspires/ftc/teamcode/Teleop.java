package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
public class Teleop extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        double forwhard;

        double strafe;

        double turn;

        String acshon = "";

        DriveSubsestem driveSubsestem = new DriveSubsestem(hardwareMap,telemetry);
        ArmSubSebstem armSubSebstem = new ArmSubSebstem(hardwareMap, telemetry);

        waitForStart();

        while (opModeIsActive()) {

            strafe = gamepad1.right_stick_x;

            forwhard = gamepad1.right_stick_y;

            turn = gamepad1.left_stick_x;

            driveSubsestem.drive(forwhard,strafe,turn);

            if (gamepad1.dpad_down) {

                acshon = "home";

            } else if (gamepad1.dpad_up) {

                acshon = "scor";

            }


            armSubSebstem.moveArm(0,acshon);


            telemetry.update();

        }

    }



}
