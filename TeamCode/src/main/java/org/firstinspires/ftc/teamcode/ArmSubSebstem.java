package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArmSubSebstem {

    private final Telemetry telemetry;
    DcMotor armMotor;

    DcMotor slideMotor;
    Servo elbow;

    public ArmSubSebstem(HardwareMap hardwareMap, Telemetry telemetry){

        armMotor = hardwareMap.dcMotor.get("arm_motor");
        slideMotor = hardwareMap.dcMotor.get("slide_motor");


        this.telemetry = telemetry;

        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setTargetPosition(0);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideMotor.setTargetPosition(0);
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        elbow = hardwareMap.servo.get("elbow");

        telemetry.addLine("Arm is ready");

    }

    public void moveArm(double adjustment, String acshon){


        if (acshon == "sample"){

            armMotor.setTargetPosition(1700);

            if (armMotor.getCurrentPosition() >= 600) {

                slideMotor.setTargetPosition(1200);

            }

            if (armMotor.getCurrentPosition() >= 1500 && slideMotor.getCurrentPosition() >= 1090) {

                elbow.setPosition(0.25);

            }

        } else if (acshon == "home"){

            if (acshon == "sample") {
                elbow.setPosition(0.99);
                slideMotor.setTargetPosition(1);

                if (slideMotor.getCurrentPosition() <= 800) {

                    armMotor.setTargetPosition(10);

                }
            } else {

                elbow.setPosition(0.99);
                slideMotor.setTargetPosition(1);

                if (slideMotor.getCurrentPosition() <= 300){

                    armMotor.setTargetPosition(10);

                }

            }

        } else if (acshon == "spesement") {



        } else if (acshon == "pickup") {

            elbow.setPosition(0.25);
            slideMotor.setTargetPosition(600);
            armMotor.setTargetPosition(250);

        }

        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        armMotor.setPower(Math.abs(1));
        slideMotor.setPower(Math.abs(1));


        telemetry.addData("target", acshon);
        telemetry.addData("agustment",adjustment);
        telemetry.addData("arm target", armMotor.getTargetPosition());

    }
}
