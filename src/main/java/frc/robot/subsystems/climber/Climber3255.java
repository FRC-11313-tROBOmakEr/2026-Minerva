// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.climber;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVelocityDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber3255 extends SubsystemBase {
  private final TalonFX string = new TalonFX(ClimberConstants.string.string_ID);
  private final TalonFX string2 = new TalonFX(ClimberConstants.string.string2_ID);

  public Climber3255() {
    string.getConfigurator().apply(ClimberConstants.string.stringConfiguration);
    string2.setControl(new Follower(string.getDeviceID(), MotorAlignmentValue.Aligned));
  }

  public void out() {
    string.setControl(new MotionMagicVelocityDutyCycle(ClimberConstants.string.out).withSlot(0));
  }

  public void back() {
    string.setControl(new MotionMagicVelocityDutyCycle(ClimberConstants.string.back).withSlot(1));
  }
}
