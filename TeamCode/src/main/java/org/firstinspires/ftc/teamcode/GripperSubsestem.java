package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class GripperSubsestem {
    double gripperWaitTimeSeconds = 0.3;
    boolean isGripperBusy = false;
    double waitTime = 1.0;
    boolean isBusyGrabbing;
    ElapsedTime runtime;
    DigitalChannel sensor;
    double open = 1.0;
    double close = 0.25;
    int stage = 1;
    private final Telemetry telemetry;
    private final Servo gripper;
    private final Servo wrist;

    public GripperSubsestem(HardwareMap hardwareMap, Telemetry telemetry) {

        this.telemetry = telemetry;

        gripper = hardwareMap.servo.get("gripper");
        wrist = hardwareMap.servo.get("wrist");
        sensor = hardwareMap.get(DigitalChannel.class, "gripper_sensor");
        sensor.setMode(DigitalChannel.Mode.INPUT);

        telemetry.addLine("gripper ready");

    }

    public void moveGripper(Gamepad gamepad1, double wrist_pos, RobotState robotState) {

        if (gamepad1.dpad_up && !isBusyGrabbing) {
            if (runtime == null) {
                runtime = new ElapsedTime();
            }
            runtime.reset();
            isGripperBusy = true;
        }

        if (isGripperBusy) {
            if (robotState.getGripperPos() == "closed") {
                // Open the gripper
                gripper.setPosition(open);
                if(runtime.seconds() >= gripperWaitTimeSeconds){
                    isGripperBusy = false;
                    robotState.setGripperPos("open") ;
                    runtime = null;
                }
            } else {
                // Close the gripper
                gripper.setPosition(close);
                if(runtime.seconds() >= gripperWaitTimeSeconds){
                    isGripperBusy = false;
                    robotState.setGripperPos("closed");
                    runtime = null;
                }
            }
        }

        if (gamepad1.dpad_down) {
            isBusyGrabbing = true;
            robotState.setGripperPos("closed");
        }

        if (isBusyGrabbing) {
            grabTheBlock(robotState);
        }

        wrist.setPosition(wrist_pos);

        robotState.setWristPos(wrist_pos);
    }

    public void grabTheBlock(RobotState robotState) {

        if (runtime == null) {
            runtime = new ElapsedTime();
        }

        if (stage == 1) {

            gripper.setPosition(close);
            robotState.setGripperPos("closed");

            if (runtime.seconds() >= waitTime) {
                stage = 2;
                runtime.reset();
            }
        }


        if (stage == 2) {
            boolean detectObject = !sensor.getState();
            robotState.setGripperDetectedObject(detectObject);
            if (detectObject) {
                telemetry.addLine("Detected object");
                telemetry.update();
                resetGripper();
            } else {
                telemetry.addLine("Not detected");
                telemetry.update();
                runtime.reset();
                stage = 3;
            }
        }

        if (stage == 3) {
            telemetry.update();
            gripper.setPosition(open);
            robotState.setGripperPos("open");

            if (runtime.seconds() >= waitTime) {
                telemetry.update();
                resetGripper();
            }


        }


    }

    public void resetGripper() {
        stage = 1;
        runtime = null;
        isBusyGrabbing = false;
    }

    public void dropTheBlock(RobotState robotState) {
        gripper.setPosition(open);
        robotState.setGripperPos("open");

    }
}
