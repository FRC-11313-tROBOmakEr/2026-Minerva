// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.command;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import frc.robot.subsystems.conveyor.Conveyor;
import frc.robot.subsystems.indexer.Spindexer_Kraken;
import frc.robot.subsystems.indexer.Indexer_Kraken;
import frc.robot.subsystems.shooter.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Shootstop extends ParallelCommandGroup {
  /** Creates a new Shootstop. */
  public Shootstop(Shooter shooter, Indexer_Kraken indexer, Spindexer_Kraken spindexer, Conveyor conveyor) {
    addCommands(
      new InstantCommand(()-> shooter.stopFlyWheels(),shooter),
      new InstantCommand(()-> indexer.stop(), indexer),
      new InstantCommand(()-> spindexer.stop(),spindexer),
      new InstantCommand(()-> conveyor.stop(),conveyor)
      );
  }
}
