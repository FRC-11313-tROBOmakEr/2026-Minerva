// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.indexer.Indexer_Neo;
import frc.robot.subsystems.indexer.Spindexer_Kraken;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Shootstop extends ParallelCommandGroup {
  /** Creates a new Shootstop. */
  public Shootstop(Shooter shooter, Indexer_Neo indexer, Spindexer_Kraken spindexer) {
    addCommands(
      new InstantCommand(()-> shooter.stopFlyWheels(),shooter),
      new InstantCommand(()-> indexer.stop(),indexer),
      new InstantCommand(()-> spindexer.stop(),spindexer));
  }
}
