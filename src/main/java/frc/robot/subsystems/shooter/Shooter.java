
package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.generated.TunerConstants;

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
import com.ctre.phoenix6.hardware.TalonFX;

import com.ctre.phoenix6.signals.MotorAlignmentValue;

import frc.robot.Constants;


public class Shooter extends SubsystemBase {
  private final TalonFX bigFlyWheel = new TalonFX(ShooterConstants.BigFlyWheel.bigFlyWheel_ID,Constants.CANBusName);
  private final TalonFX smallFlyWheel = new TalonFX(ShooterConstants.SmallFlyWheel.smallFlyWheel_ID,Constants.CANBusName);
  private final TalonFX angleFalcon = new TalonFX(ShooterConstants.AngleFalcon.angleFalcon_ID,Constants.CANBusName);
  private final SparkMax angle = new SparkMax(ShooterConstants.Angle.Angle_ID, MotorType.kBrushless);
  private final SparkMaxConfig angleConfig = new SparkMaxConfig();
  private final SparkClosedLoopController anglectrl = angle.getClosedLoopController();
  private double degrees;

  public Shooter() {
    // angleConfig.closedLoop
    //     .feedbackSensor(FeedbackSensor.kAbsoluteEncoder)
    //     .pid(ShooterConstants.Angle.Run_P,
    //          ShooterConstants.Angle.Run_I, 
    //          ShooterConstants.Angle.Run_D, 
    //          ClosedLoopSlot.kSlot0)
    //     .maxMotion.allowedProfileError(0.05, ClosedLoopSlot.kSlot0);

    // angleConfig.closedLoop.feedForward
    //     .kV(ShooterConstants.Angle.Run_F, ClosedLoopSlot.kSlot0);

    
    // angleConfig.closedLoop.maxMotion.maxAcceleration(ShooterConstants.Angle.maxAcceleration)
    //                                   .cruiseVelocity(ShooterConstants.Angle.cruiseVelocity)
    //                                   .allowedProfileError(ShooterConstants.Angle.allowedProfileError, ClosedLoopSlot.kSlot0);
    // angleConfig.idleMode(IdleMode.kBrake);
    // angleConfig.absoluteEncoder.zeroOffset(0.05);
    // angleConfig.inverted(true);                              

    // angle.configure(angleConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    


  bigFlyWheel.setPosition(0);
  smallFlyWheel.setPosition(0);
  angleFalcon.setPosition(0);

  smallFlyWheel.setControl(new Follower(bigFlyWheel.getDeviceID(), MotorAlignmentValue.Opposed));

  bigFlyWheel.getConfigurator().apply(ShooterConstants.BigFlyWheel.bigflywheelConfiguration);
  angleFalcon.getConfigurator().apply(ShooterConstants.AngleFalcon.angleFalconConfiguration);
  
  
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Current Velocity", bigFlyWheel.getVelocity().getValueAsDouble());
  }


  public void out() {
    bigFlyWheel.setControl(new VelocityVoltage(ShooterConstants.BigFlyWheel.bigFlyWheel).withSlot(0));
  
  }
  // public void stopFlyWheels1() {
  //   bigFlyWheel.setControl(new MotionMagicVelocityVoltage())
  // }

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