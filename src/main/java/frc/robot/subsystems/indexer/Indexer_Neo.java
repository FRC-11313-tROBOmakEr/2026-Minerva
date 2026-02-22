// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.indexer;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.intake.Intake_Constants;

public class Indexer_Neo extends SubsystemBase {
  private final SparkMax indexerNeo = new SparkMax(IndexerConstants.Indexer_Neo.Indexer_ID, MotorType.kBrushless);

  private final SparkClosedLoopController indexerCtrl = indexerNeo.getClosedLoopController();

  private final SparkMaxConfig indexerConfig = new SparkMaxConfig();
  /** Creates a new Indexer_Neo. */
  public Indexer_Neo () {
    
  }
  public void Run(){

  }
  public void Stop(){

  }

  
}
