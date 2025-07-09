package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class GripperSubsestem {


    private final Telemetry telemetry;
    private final Servo gripper;
    private final Servo wrist;

    public GripperSubsestem(HardwareMap hardwareMap, Telemetry telemetry){

        this.telemetry = telemetry;

        gripper = hardwareMap.servo.get("gripper");
        wrist = hardwareMap.servo.get("wrist");

        telemetry.addLine("gripper ready");

    }

    public void moveGripper(String gripper_pos,double wrist_pos, RobotState robotState) {

        if (gripper_pos == "open") {

            gripper.setPosition(0.35);

        } else if (gripper_pos == "closed") {

            gripper.setPosition(0.95);

        }

        wrist.setPosition(wrist_pos);

        robotState.setGripperPos(gripper_pos);
        robotState.setWristPos(wrist_pos);

    }

}
