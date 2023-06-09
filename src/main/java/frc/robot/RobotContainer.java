// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.DriveFast;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.Jostle;
import frc.robot.commands.LoadAndLaunch;
import frc.robot.commands.RunIntake;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Loader;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController xboxC =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  DriveBase drivebase = new DriveBase();
  Intake intake = new Intake();
  Shooter shooter = new Shooter();
  Loader loader = new Loader();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {


    drivebase.setDefaultCommand(new DriveWithJoystick(drivebase, () -> xboxC.getLeftY(), ()-> xboxC.getRightX()));

    
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    Trigger intakeButton = xboxC.rightTrigger();
    Trigger shootButton = xboxC.x();

    intakeButton.whileTrue(new RunIntake(intake, 0.50));
    shootButton.whileTrue(new LoadAndLaunch(loader, shooter, 0.27, 0.30));
    xboxC.rightBumper().whileTrue(new Shoot(shooter, 0.30));
    xboxC.leftTrigger().whileTrue(new DriveFast());
    xboxC.y().whileTrue(new RepeatCommand(new Jostle(drivebase)));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return new WaitCommand(1);
  }
}
