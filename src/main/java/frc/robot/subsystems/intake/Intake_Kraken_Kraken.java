// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.controls.MotionMagicVoltage;

public class Intake_Kraken_Kraken extends SubsystemBase {
  private final TalonFX roller = new TalonFX(Intake_Constants.Intake_Kraken_Roller.Roller_ID, Intake_Constants.Can);
  private final TalonFX deploy = new TalonFX(Intake_Constants.Intake_Kraken_Deploy.Deploy_ID, Intake_Constants.Can);

  public Intake_Kraken_Kraken() {


    roller.getConfigurator().apply(Intake_Constants.Intake_Kraken_Roller.rollerConfiguration);
    deploy.getConfigurator().apply(Intake_Constants.Intake_Kraken_Deploy.deployConfiguration);

  }

  public void rollerStart() {
    roller.setControl(new MotionMagicVelocityVoltage(Intake_Constants.Intake_Kraken_Roller.Roller_Start).withSlot(0));
  }

  public void rollerStop() {
    roller.stopMotor();
  }

  public void deployOut() {
    deploy.setControl(new MotionMagicVoltage(Intake_Constants.Intake_Kraken_Deploy.Deploy_Start).withSlot(0));
  }

  public void deployBack() {
    deploy.setControl(new MotionMagicVoltage(Intake_Constants.Intake_Kraken_Deploy.Deploy_Back).withSlot(1));
  }
}
