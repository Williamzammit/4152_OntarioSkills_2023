// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class DriveWithJoystick extends CommandBase {
  DriveBase drivebase;
  DoubleSupplier throttle;
  DoubleSupplier rotation;
  /** Creates a new DriveWithJoystick. */
  public DriveWithJoystick(DriveBase drivebase, DoubleSupplier throttle, DoubleSupplier rotation) {
    // Use addRequirements() here to declare subsystem dependencies.

    this.drivebase = drivebase;
    this.throttle = throttle;
    this.rotation = rotation;

    addRequirements(drivebase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivebase.arcadeDrive(
    -applyDriveDeadband(throttle.getAsDouble()), 
    applyDriveDeadband(rotation.getAsDouble()));
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public double applyDriveDeadband(double value){

    if (value < 0.05){
      if(value < -0.05){
        return value;
      }
      else {
        return 0.0;
      }
    } else{
      return value;
    }
  }
}
