package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;

@Disabled
@Autonomous
public class score1_park extends LinearOpMode {

    DcMotor frontLeftMotor;
    DcMotor backLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backRightMotor;
    DcMotor elevatorMotor;

    CRServo servoBucket;


    @Override
    public void runOpMode() throws InterruptedException {
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        backLeftMotor = hardwareMap.dcMotor.get("backLeft");
        frontRightMotor = hardwareMap.dcMotor.get("frontRight");
        backRightMotor = hardwareMap.dcMotor.get("backRight");
        elevatorMotor = hardwareMap.dcMotor.get("elevator");
        servoBucket = hardwareMap.get(CRServo.class, "servoBucket");


        DigitalChannel elevatorIn = hardwareMap.digitalChannel.get("elevatorIn");
        DigitalChannel elevatorOut = hardwareMap.digitalChannel.get("elevatorOut");


        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        elevatorMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevatorMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            double targetFL = DrivetrainFunction.calculateTicks(-45.0);
            double targetFR = DrivetrainFunction.calculateTicks(-45.0);
            double targetBL = DrivetrainFunction.calculateTicks(-45.0);
            double targetBR = DrivetrainFunction.calculateTicks(-45.0);
            encoderDrive(targetFL, targetFR, targetBL, targetBR);

            targetFL = frontLeftMotor.getCurrentPosition() + DrivetrainFunction.calculateTicks(-30.0);
            targetFR = frontRightMotor.getCurrentPosition() + DrivetrainFunction.calculateTicks(30.0);
            targetBL = backLeftMotor.getCurrentPosition() + DrivetrainFunction.calculateTicks(-30.0);
            targetBR =  backRightMotor.getCurrentPosition() + DrivetrainFunction.calculateTicks(30.0);
            encoderDrive(targetFL, targetFR, targetBL, targetBR);
            sleep(300);
            elevatorMotor.setPower(ElevatorFunction.moveElevator(0.75, elevatorIn, elevatorOut));

            targetFL = frontLeftMotor.getCurrentPosition() + DrivetrainFunction.calculateTicks(35.0);
            targetFR = frontRightMotor.getCurrentPosition() + DrivetrainFunction.calculateTicks(35.0);
            targetBL = backLeftMotor.getCurrentPosition() + DrivetrainFunction.calculateTicks(35.0);
            targetBR =  backRightMotor.getCurrentPosition() +DrivetrainFunction.calculateTicks(35.0);
            encoderDrive(targetFL, targetFR, targetBL, targetBR);
            sleep(600);
            elevatorMotor.setPower(0);
            frontLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            backLeftMotor.setPower(0);
            backRightMotor.setPower(0);
            servoBucket.setPower(-1);
            sleep(1800);

            servoBucket.setPower(1);
            sleep(500);
            elevatorMotor.setPower(ElevatorFunction.moveElevator(-0.75, elevatorIn, elevatorOut));
            sleep(500);
            servoBucket.setPower(0);
            sleep(400);
            elevatorMotor.setPower(0);
            targetFL = frontLeftMotor.getCurrentPosition() + DrivetrainFunction.calculateTicks(30.0);
            targetFR = frontRightMotor.getCurrentPosition() + DrivetrainFunction.calculateTicks(-30.0);
            targetBL = backLeftMotor.getCurrentPosition() + DrivetrainFunction.calculateTicks(-30.0);
            targetBR =  backRightMotor.getCurrentPosition() + DrivetrainFunction.calculateTicks(-30.0);
            encoderDrive(targetFL, targetFR, targetBL, targetBR);
            sleep(300);
            targetFL = frontLeftMotor.getCurrentPosition() + DrivetrainFunction.calculateTicks(-40.0);
            targetFR = frontRightMotor.getCurrentPosition() + DrivetrainFunction.calculateTicks(40.0);
            targetBL = backLeftMotor.getCurrentPosition() + DrivetrainFunction.calculateTicks(-40.0);
            targetBR =  backRightMotor.getCurrentPosition() + DrivetrainFunction.calculateTicks(40.0);
            encoderDrive(targetFL, targetFR, targetBL, targetBR);
            sleep(300);


            targetFL = frontLeftMotor.getCurrentPosition() + DrivetrainFunction.calculateTicks(-250.0);
            targetFR = frontRightMotor.getCurrentPosition() + DrivetrainFunction.calculateTicks(-250.0);
            targetBL = backLeftMotor.getCurrentPosition() + DrivetrainFunction.calculateTicks(-250.0);
            targetBR =  backRightMotor.getCurrentPosition() +DrivetrainFunction.calculateTicks(-250.0);
            encoderDrive(targetFL, targetFR, targetBL, targetBR);
            sleep(500);
            targetFL = frontLeftMotor.getCurrentPosition() + DrivetrainFunction.calculateTicks(-25.0);
            targetFR = frontRightMotor.getCurrentPosition() + DrivetrainFunction.calculateTicks(25.0);
            targetBL = backLeftMotor.getCurrentPosition() + DrivetrainFunction.calculateTicks(25.0);
            targetBR =  backRightMotor.getCurrentPosition() +DrivetrainFunction.calculateTicks(-25.0);
            encoderDrive(targetFL, targetFR, targetBL, targetBR);
            sleep(500);
            break;
        }
    }

    public void encoderDrive(double targetFL, double targetFR, double targetBL, double targetBR) {
        while (DrivetrainFunction.calculatePower(targetFL, frontLeftMotor.getCurrentPosition()) != 0
                && DrivetrainFunction.calculatePower(targetFR, frontRightMotor.getCurrentPosition()) != 0
                && DrivetrainFunction.calculatePower(targetBL, backLeftMotor.getCurrentPosition()) != 0
                && DrivetrainFunction.calculatePower(targetBR, backRightMotor.getCurrentPosition()) != 0 && opModeIsActive()) {
            frontLeftMotor.setPower(DrivetrainFunction.calculatePower(targetFL, frontLeftMotor.getCurrentPosition()));
            frontRightMotor.setPower(DrivetrainFunction.calculatePower(targetFR, frontRightMotor.getCurrentPosition()));
            backLeftMotor.setPower(DrivetrainFunction.calculatePower(targetBL, backLeftMotor.getCurrentPosition()));
            backRightMotor.setPower(DrivetrainFunction.calculatePower(targetBR, backRightMotor.getCurrentPosition()));
        }
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);


    }


}

