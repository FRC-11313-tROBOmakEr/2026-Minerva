// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.MotionMagicVoltage;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

public class Intake_Kraken_Neo extends SubsystemBase {
  private final TalonFX deploy = new TalonFX(Intake_Constants.Intake_Kraken_Deploy.Deploy_ID, Intake_Constants.Can);
  private final SparkMax roller = new SparkMax(Intake_Constants.Intake_Neo_Deploy.Deploy_ID, SparkLowLevel.MotorType.kBrushless);
  private final SparkClosedLoopController rollerctrl = roller.getClosedLoopController();
  private final SparkMaxConfig rollerConfig = new SparkMaxConfig();

  public Intake_Kraken_Neo() {
     deploy.getConfigurator().apply(Intake_Constants.Intake_Kraken_Deploy.deployConfiguration);

     rollerConfig.secondaryCurrentLimit(40).idleMode(IdleMode.kBrake);
     rollerConfig.closedLoop
        .feedbackSensor(FeedbackSensor.kAbsoluteEncoder)
        .pid(Intake_Constants.Intake_Neo_Roller.Roller_Start_P,
             Intake_Constants.Intake_Neo_Roller.Roller_Start_I, 
             Intake_Constants.Intake_Neo_Roller.Roller_Start_D, 
             ClosedLoopSlot.kSlot0)
        .maxMotion.allowedProfileError(0.05, ClosedLoopSlot.kSlot0)
        .maxAcceleration(Intake_Constants.Intake_Neo_Roller.maxAcceleration)
        .cruiseVelocity(Intake_Constants.Intake_Neo_Roller.cruiseVelocity);

    roller.configure(rollerConfig, ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);

  }

 public void deployOut() {
    deploy.setControl(new MotionMagicVoltage(Intake_Constants.Intake_Kraken_Deploy.Deploy_Start).withSlot(0));
  }

  public void deployBack() {
    deploy.setControl(new MotionMagicVoltage(Intake_Constants.Intake_Kraken_Deploy.Deploy_Back).withSlot(1));
  }

  public void rollerStart(){
    rollerctrl.setSetpoint(Intake_Constants.Intake_Neo_Roller.Roller_Start, ControlType.kMAXMotionVelocityControl, ClosedLoopSlot.kSlot0);
  }
  
  public void rollerStop(){
    roller.stopMotor();
  }
  }

