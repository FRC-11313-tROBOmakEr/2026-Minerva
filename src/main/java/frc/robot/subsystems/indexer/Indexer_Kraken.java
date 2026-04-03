// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.indexer;

import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Indexer_Kraken extends SubsystemBase {
  private final TalonFX indexerKraken = new TalonFX(IndexerConstants.Indexer_Kraken.Indexer_ID);

  /** Creates a new Indexer_Kraken. */
  public Indexer_Kraken() {
    indexerKraken.getConfigurator().apply(IndexerConstants.Indexer_Kraken.IndexerConfiguration);
  }

   public void run() {
    indexerKraken.setControl(new VelocityVoltage(IndexerConstants.Indexer_Kraken.Run).withSlot(0));
  }

  public void stop() {
    indexerKraken.stopMotor();
  }

  
}
