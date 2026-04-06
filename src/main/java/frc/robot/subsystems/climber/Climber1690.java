// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.climber;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import edu.wpi.first.wpilibj.DutyCycle;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber1690 extends SubsystemBase {
  private final TalonFX string = new TalonFX(ClimberConstants.string.String_ID);
 
  public Climber1690() {
    string.getConfigurator().apply(ClimberConstants.string.stringConfiguration);
    string.setPosition(0);
  }

  public void out() {
    //string.set(-0.2);
    //string.setControl(new PositionVoltage(-38));
    string.setControl(new MotionMagicVoltage(0));
  }

  public void back() {
    //string.set(0.2);
    //string.setControl(new MotionMagicVoltage(0).withPosition(ClimberConstants.string.position).withSlot(0));
    //string.setControl(new PositionVoltage(38));
    string.setControl(new MotionMagicVoltage(-49));
  }

  public void stop(){
    string.stopMotor();
  }
}
