package frc.robot.command;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.subsystems.conveyor.Conveyor;
import frc.robot.subsystems.indexer.Indexer_Kraken;
import frc.robot.subsystems.indexer.Indexer_Neo;
import frc.robot.subsystems.indexer.Spindexer_Kraken;
import frc.robot.subsystems.shooter.Shooter;

public class Shoot extends SequentialCommandGroup {
  /** Creates a new shoot. */
  public Shoot(Shooter shooter, Indexer_Neo indexer, Spindexer_Kraken spindexer,Conveyor conveyor) {
    addCommands(
      new InstantCommand(()-> shooter.out(),shooter),
      new WaitCommand(0.4),
      new InstantCommand(()->conveyor.run(),conveyor),
      new InstantCommand(()-> indexer.run(),indexer),
      new InstantCommand(()-> spindexer.run(),spindexer));
  }
}
