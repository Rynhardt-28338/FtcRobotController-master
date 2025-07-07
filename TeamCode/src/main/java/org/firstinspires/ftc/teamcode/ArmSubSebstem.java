package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
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

        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideMotor.setTargetPosition(0);
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        elbow = hardwareMap.servo.get("elbow");

        telemetry.addLine("Arm is ready");

    }

    public void moveArm(double adjustment, String acshon){

        elbow.setPosition(0.6);

        if (acshon == "scor"){

            armMotor.setTargetPosition(3000);
            slideMotor.setTargetPosition(100);

            elbow.setPosition(0.6);

            telemetry.addLine("up");

        }

        if (acshon == "home"){

            elbow.setPosition(0.9);

            armMotor.setTargetPosition(10);
            slideMotor.setTargetPosition(1);

        }

        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        telemetry.addData("target", acshon);
        telemetry.addData("agustment",adjustment);

    }
}
