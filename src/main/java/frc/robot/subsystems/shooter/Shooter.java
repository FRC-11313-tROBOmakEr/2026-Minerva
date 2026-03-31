
package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.intake.Intake_Constants;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;

import java.lang.annotation.Target;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVelocityDutyCycle;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

import com.ctre.phoenix6.signals.MotorAlignmentValue;

import frc.robot.Constants;


public class Shooter extends SubsystemBase {
  private final TalonFX bigFlyWheel = new TalonFX(ShooterConstants.BigFlyWheel.bigFlyWheel_ID,Constants.CANBus);
  private final TalonFX smallFlyWheel = new TalonFX(ShooterConstants.SmallFlyWheel.smallFlyWheel_ID,Constants.CANBus);
  private final TalonFX angleFalcon = new TalonFX(ShooterConstants.AngleFalcon.angleFalcon_ID,Constants.CANBus);
  private final CANcoder angleEncoder = new CANcoder(Intake_Constants.Intake_Kraken_Deploy.Encoder_ID, Constants.CANBus);
  
  private double degrees;

  public Shooter() {
    


  bigFlyWheel.setPosition(0);
  smallFlyWheel.setPosition(0);
  angleFalcon.setPosition(0);

  smallFlyWheel.setControl(new Follower(bigFlyWheel.getDeviceID(), MotorAlignmentValue.Opposed));

  bigFlyWheel.getConfigurator().apply(ShooterConstants.BigFlyWheel.bigflywheelConfiguration);
  angleFalcon.getConfigurator().apply(ShooterConstants.AngleFalcon.angleFalconConfiguration);
  angleEncoder.getConfigurator().apply(Intake_Constants.Intake_Kraken_Deploy.encoderConfiguration);
  
  
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Current Velocity", bigFlyWheel.getVelocity().getValueAsDouble());
  }


  public void out() {
    bigFlyWheel.setControl(new VelocityVoltage(ShooterConstants.BigFlyWheel.bigFlyWheel).withSlot(0));
  
  }

  public void stopFlyWheels() {
    bigFlyWheel.stopMotor();
  }

  public void titi() {
   bigFlyWheel.getClosedLoopError().getTimestamp();
  }

  public void angleup(){
    angleFalcon.set(0.05);
  }

  public void angledown(){
    angleFalcon.set(-0.05);
  }

  public void anglestop(){
    angleFalcon.stopMotor();
  }
  // public void angleout(){
  //   anglectrl.setSetpoint(20.0, ControlType.kPosition, ClosedLoopSlot.kSlot0);
  // }
  // public void anglein(){
  //   anglectrl.setSetpoint(0, ControlType.kPosition, ClosedLoopSlot.kSlot0);
  // }
  
  public void angleAdjust(){
    angleFalcon.setControl(new MotionMagicVoltage(0).withPosition(degrees));
  }

  public boolean isVelocityTarget() {
   return Math.abs(bigFlyWheel.getClosedLoopError().getValueAsDouble() - (ShooterConstants.BigFlyWheel.bigFlyWheel)) < 5;
  }
}