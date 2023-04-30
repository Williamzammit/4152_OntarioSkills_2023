// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

  VictorSP shooterMotor = new VictorSP(0);

  /** Creates a new Shooter. */
  public Shooter() {
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void shoot(double shooterSpeed){
    shooterMotor.set(-shooterSpeed); // Control mode?
  }

  public void stopShooter(){
    shooterMotor.set(0.0);
  }

  
}
