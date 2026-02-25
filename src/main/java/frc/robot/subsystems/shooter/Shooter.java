
package frc.robot.subsystems.shooter;

import edu.wpi.first.math.geometry.Pose2d;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import java.util.HashMap;
import java.util.List;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;

import com.ctre.phoenix6.signals.MotorAlignmentValue;

import com.ctre.phoenix6.hardware.TalonFX;

public class Shooter extends SubsystemBase {
  private final TalonFX bigFlyWheel = new TalonFX(ShooterConstants.BigFlyWheel.bigFlyWheel_ID,ShooterConstants.Can);
  private final TalonFX smallFlyWheel = new TalonFX(ShooterConstants.SmallFlyWheel.smallFlyWheel_ID,ShooterConstants.Can);
  
  //private SparkAbsoluteEncoder encoder;

  public WaitCommand timmer;
  public double smoothSpeed;
  public double targetSpeed = 0.8;
  //private double angle;
  public Pose2d TargetPose = new Pose2d();
  public static HashMap<Integer, List<Pose2d>> hubMap = new HashMap<>();
  

  public Shooter() {

  bigFlyWheel.setPosition(0);
  smallFlyWheel.setPosition(0);

  smallFlyWheel.setControl(new Follower(bigFlyWheel.getDeviceID(), MotorAlignmentValue.Opposed));

  bigFlyWheel.getConfigurator().apply(ShooterConstants.BigFlyWheel.bigflywheelConfiguration);
  }


  public void Shooter_Out() {
     bigFlyWheel.setControl(new MotionMagicVelocityVoltage(ShooterConstants.BigFlyWheel.bigFlyWheel).withSlot(0));
     
  }

  public void stopFlyWheels() {
    bigFlyWheel.stopMotor();
  }

}