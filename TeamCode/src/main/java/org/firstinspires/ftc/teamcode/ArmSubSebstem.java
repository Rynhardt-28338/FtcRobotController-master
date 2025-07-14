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
    int armPos = 0;
    int slidePos = 0;

    public ArmSubSebstem(HardwareMap hardwareMap, Telemetry telemetry){

        armMotor = hardwareMap.dcMotor.get("arm_motor");
        slideMotor = hardwareMap.dcMotor.get("slide_motor");

        this.telemetry = telemetry;

        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setTargetPosition(0);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
   //     armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideMotor.setTargetPosition(0);
     //   slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        elbow = hardwareMap.servo.get("elbow");

        telemetry.addLine("Arm is ready");

    }

    public void moveArm(int ArmAdjustment,int SlideAjustment, String acshon, RobotState robotState) {


        if (acshon == "sample") {

            armPos = 1600;

            if (armMotor.getCurrentPosition() >= 600) {

                slidePos = 1450;

            }

            if (armMotor.getCurrentPosition() >= 1500 && slideMotor.getCurrentPosition() >= 1090) {

                elbow.setPosition(0.25);

            }

        } else if (acshon == "home") {

            if (acshon == "sample") {
                elbow.setPosition(0.99);
                slidePos = 1;

                if (slideMotor.getCurrentPosition() <= 800) {

                    armMotor.setTargetPosition(50);

                }
            } else {

                elbow.setPosition(0.99);
                slidePos = 1;

                if (slideMotor.getCurrentPosition() <= 300) {

                    armPos = 10;

                }

            }

        } else if (acshon == "spesementRedy") {

            armPos = 1497;
            slidePos = 200;
            elbow.setPosition(0.25);

        } else if (acshon == "spesementFinish") {

            slidePos = 10;
            elbow.setPosition(0.99);

        } else if (acshon == "pickup") {

            elbow.setPosition(0.25);
            slidePos = 400;
            armPos = 200;

        }


        armMotor.setTargetPosition(armPos + ArmAdjustment);
        slideMotor.setTargetPosition(slidePos + SlideAjustment);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        armMotor.setPower(Math.abs(1));
        slideMotor.setPower(Math.abs(1));


        telemetry.addData("target", acshon);
        telemetry.addData("agustment", ArmAdjustment);
        telemetry.addData("arm target", armMotor.getTargetPosition());

        robotState.setArmPos(armMotor.getCurrentPosition());
        robotState.setSlidePos(slideMotor.getCurrentPosition());
        robotState.setArmTarget(armMotor.getTargetPosition());

    }
}
