// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;

import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;




public class Intake_Neo_Kraken extends SubsystemBase {
  private final TalonFX roller = new TalonFX(Intake_Constants.Intake_Kraken_Roller.Roller_ID, Intake_Constants.Can);
  private final SparkMax deploy = new SparkMax(Intake_Constants.Intake_Neo_Deploy.Deploy_ID, SparkLowLevel.MotorType.kBrushless);
  private final TalonFXConfiguration rollerConfiguration = new TalonFXConfiguration();
  private final SparkClosedLoopController deployctrl = deploy.getClosedLoopController();
  
  public Intake_Neo_Kraken() {

    roller.getConfigurator().apply(rollerConfiguration);
  
    
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
