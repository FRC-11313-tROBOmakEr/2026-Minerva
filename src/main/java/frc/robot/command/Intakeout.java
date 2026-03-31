// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.command;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.subsystems.intake.Intake_Kraken_Kraken;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Intakeout extends ParallelCommandGroup {
  /** Creates a new Intakeout. */
  public Intakeout(Intake_Kraken_Kraken intake) {
    addCommands(
      new InstantCommand(()->intake.Out()),
      new WaitUntilCommand(()->intake.isIntakeDeployed()),
      new InstantCommand(()->intake.DeployStop())
      .alongWith(new InstantCommand(()->intake.rollerStart()))
    );
  }
}
