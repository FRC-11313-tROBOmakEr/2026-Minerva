// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;

public class Intake_Kraken_Kraken extends SubsystemBase {
  private final TalonFX roller = new TalonFX(Intake_Constants.Intake_Kraken_Roller.Roller_ID, Intake_Constants.Can);
  private final TalonFX deploy = new TalonFX(Intake_Constants.Intake_Kraken_Deploy.Deploy_ID, Intake_Constants.Can);

  private final TalonFXConfiguration rolleConfiguration = new TalonFXConfiguration();
  private final TalonFXConfiguration deployConfiguration = new TalonFXConfiguration();

  public Intake_Kraken_Kraken() {

    rolleConfiguration.withSlot0(Intake_Constants.Intake_Kraken_Roller.ROLLER_SLOT0_CONFIGS);
    deployConfiguration.withSlot0(Intake_Constants.Intake_Kraken_Deploy.Deploy_Slot0_Configs_F);
    deployConfiguration.withSlot1(Intake_Constants.Intake_Kraken_Deploy.Deploy_Slot1_Configs_B);
    deployConfiguration.withMotionMagic(new MotionMagicConfigs()
        .withMotionMagicAcceleration(Intake_Constants.Intake_Kraken_Deploy.MotionMagicAcceleration)
        .withMotionMagicCruiseVelocity(Intake_Constants.Intake_Kraken_Deploy.MotionMagicCruiseVelocity)
        .withMotionMagicJerk(Intake_Constants.Intake_Kraken_Deploy.MotionMagicJerk));

    roller.getConfigurator().apply(rolleConfiguration);
    deploy.getConfigurator().apply(deployConfiguration);

  }

  public void rollerStart() {
    roller.setControl(new VelocityVoltage(Intake_Constants.Intake_Kraken_Roller.Roller_Start).withSlot(0));
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
