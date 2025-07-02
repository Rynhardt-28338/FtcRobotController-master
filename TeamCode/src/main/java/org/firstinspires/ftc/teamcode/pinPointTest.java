package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

@Autonomous
public class pinPointTest extends LinearOpMode {

    double targetX = 30;
    double targetY = -30;
    double targetDir = 0;

    double powerX;
    double powerY;
    double powerTurn;

    @Override
    public void runOpMode() throws InterruptedException {

        RobotState robotState = new RobotState();

        OdometrySubsystem odometrySubsystem =
                new OdometrySubsystem(hardwareMap,
                        new Pose2D(DistanceUnit.CM, 0, 0, AngleUnit.DEGREES , 0));

        DriveSubsestem driveSubsestem = new DriveSubsestem(hardwareMap,telemetry);

        PIDController pidController = new PIDController(0.005,0,0);





        waitForStart();

        while (opModeIsActive()){

            odometrySubsystem.periodic(telemetry,robotState);

            powerX = pidController.calculate(targetX-robotState.getCurentX());
            powerY = pidController.calculate(targetY-robotState.getCurentY());
            powerTurn = pidController.calculate(targetDir-robotState.getCurentDir());


            driveSubsestem.drive(powerX,powerY,powerTurn);

            telemetry.addData("targetX", Double.toString(targetX),"powerX", powerX);
            telemetry.addData("targetY",Double.toString(targetY),"powerY", powerY);
            telemetry.addData("targetDir",Double.toString(targetDir),"powerTurn", powerTurn);
            telemetry.addData("powerY",powerX);
            telemetry.addData("powerY",powerY);
            telemetry.addData("powerTurn",powerTurn);
            telemetry.addData("x: ", robotState.getCurentX());
            telemetry.addData("y: ", robotState.getCurentY());
            telemetry.addData("heading: ", robotState.getCurentDir());



            telemetry.update();

        }


    }
}
