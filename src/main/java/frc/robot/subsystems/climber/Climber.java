// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.climber;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import frc.robot.Constants;


public class Climber extends SubsystemBase {
  /** Creates a new Climber. */
  private final TalonFX string= new TalonFX(ClimberConstants.string.String_ID,Constants.CANBus);
  private final TalonFX string2 = new TalonFX(ClimberConstants.string.String2_ID,Constants.CANBus);
  private final SparkMax angle = new SparkMax(ClimberConstants.Angle.Angle_ID, MotorType.kBrushless);
  private final SparkMaxConfig angleConfig = new SparkMaxConfig();
  

  public Climber(){
    string.getConfigurator().apply(ClimberConstants.string.stringConfiguration);
    string2.setControl(new Follower(string.getDeviceID(),MotorAlignmentValue.Aligned));

    angleConfig.closedLoop
        .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
        .pid(ClimberConstants.Angle.Run_P,
             ClimberConstants.Angle.Run_I, 
             ClimberConstants.Angle.Run_D, 
             ClosedLoopSlot.kSlot0)
        .maxMotion.allowedProfileError(0.05, ClosedLoopSlot.kSlot0);

    angleConfig.closedLoop.feedForward
        .kV(ClimberConstants.Angle.Run_F, ClosedLoopSlot.kSlot0);

    
    angleConfig.closedLoop.maxMotion.maxAcceleration(ClimberConstants.Angle.MaxAcceleration)
                                      .cruiseVelocity(ClimberConstants.Angle.CruiseVelocity)
                                      .allowedProfileError(ClimberConstants.Angle.AllowedProfileError, ClosedLoopSlot.kSlot0);
    angleConfig.idleMode(IdleMode.kBrake);                              

    angle.configure(angleConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  public void out(){
    string.setControl(new VelocityDutyCycle(ClimberConstants.string.Out).withSlot(0));
  }

  public void back(){
    string.setControl(new VelocityDutyCycle(ClimberConstants.string.Back).withSlot(1));
  }

  public void up(){
    angle.set(0.5);
  }

  public void down(){
    angle.set(-0.5);
  }

  public void stop(){
    angle.stopMotor();
  }

}
