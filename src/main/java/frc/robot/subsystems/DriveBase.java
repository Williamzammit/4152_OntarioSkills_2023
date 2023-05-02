// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DriveBaseConstants;

public class DriveBase extends SubsystemBase {
  
  CANSparkMax leftDrive = new CANSparkMax(DriveBaseConstants.leftDriveMotor, MotorType.kBrushless);
  CANSparkMax rightDrive = new CANSparkMax(DriveBaseConstants.rightDriveMotor, MotorType.kBrushless);

  DifferentialDrive drive = new DifferentialDrive(leftDrive, rightDrive);

  public static double acceleration = 0.0;

  /** Creates a new DriveBase. */
  public DriveBase() {

    leftDrive.setIdleMode(IdleMode.kCoast);
    rightDrive.setIdleMode(IdleMode.kCoast);
    
    rightDrive.setInverted(true);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    

  }

  public void arcadeDrive(double throttle, double rotation){
    rotation*=-1;

    SmartDashboard.putNumber("Throttle", throttle);
    SmartDashboard.putNumber("Rotation", rotation);
    drive.arcadeDrive(throttle, 
                      rotation);
  }

  public void brake(boolean brakeState){
    if(brakeState){
      leftDrive.setIdleMode(IdleMode.kBrake);
      rightDrive.setIdleMode(IdleMode.kBrake);
    } else {
      rightDrive.setIdleMode(IdleMode.kCoast);
      leftDrive.setIdleMode(IdleMode.kCoast);
    }

  }

  public double applyDriveDeadband(double value){

    if (value < 0.01){
      if(value < -0.01){
        return value;
      }
      else {
        acceleration = 0.0;
        return 0.0;

      }
    } else{
      return value;
    }
  }

  /*public double applyAcceleration(double value){
    if (!(acceleration >= value)){
      return value;
    }
      acceleration += 0.01;
      return value*acceleration;
  }*/

}
