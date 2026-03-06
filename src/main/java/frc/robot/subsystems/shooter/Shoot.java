package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.subsystems.indexer.Indexer_Kraken;
import frc.robot.subsystems.indexer.Indexer_Neo;
import frc.robot.subsystems.indexer.Spindexer_Kraken;

public class Shoot extends SequentialCommandGroup {
  /** Creates a new shoot. */
  public Shoot(Shooter shooter, Indexer_Kraken indexer, Spindexer_Kraken spindexer) {
    addCommands(
      new InstantCommand(()-> shooter.out(),shooter),
      new WaitUntilCommand(() -> shooter.isVelocityTarget()),
      new InstantCommand(()-> indexer.run(),indexer),
      new InstantCommand(()-> spindexer.run(),spindexer)     
    );
  }
}
