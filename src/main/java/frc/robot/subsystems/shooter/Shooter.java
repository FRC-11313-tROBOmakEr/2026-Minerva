
package frc.robot.subsystems.shooter;

import edu.wpi.first.math.geometry.Pose2d;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.indexer.IndexerConstants;

import java.util.HashMap;
import java.util.List;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import com.ctre.phoenix6.signals.MotorAlignmentValue;



public class Shooter extends SubsystemBase {
  private final TalonFX bigFlyWheel = new TalonFX(ShooterConstants.BigFlyWheel.bigFlyWheel_ID,ShooterConstants.Can);
  private final TalonFX smallFlyWheel = new TalonFX(ShooterConstants.SmallFlyWheel.smallFlyWheel_ID,ShooterConstants.Can);
  private final SparkMax angle = new SparkMax(IndexerConstants.Indexer_Neo.Indexer_ID, MotorType.kBrushless);
  private final SparkMaxConfig angleConfig = new SparkMaxConfig();
  private final SparkClosedLoopController angleCloseloop = angle.getClosedLoopController(); 
  
  public double targetSpeed = 0.8;
  public Pose2d TargetPose = new Pose2d();
  public static HashMap<Integer, List<Pose2d>> hubMap = new HashMap<>();

    


  public Shooter() {
    angleConfig.closedLoop
        .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
        .pid(IndexerConstants.Indexer_Neo.Run_P,
             IndexerConstants.Indexer_Neo.Run_I, 
             IndexerConstants.Indexer_Neo.Run_D, 
             ClosedLoopSlot.kSlot0)
        .maxMotion.allowedProfileError(0.05, ClosedLoopSlot.kSlot0);

    angleConfig.closedLoop.feedForward
        .kV(IndexerConstants.Indexer_Neo.Run_F, ClosedLoopSlot.kSlot0);

    
    angleConfig.closedLoop.maxMotion.maxAcceleration(IndexerConstants.Indexer_Neo.maxAcceleration)
                                      .cruiseVelocity(IndexerConstants.Indexer_Neo.cruiseVelocity)
                                      .allowedProfileError(IndexerConstants.Indexer_Neo.allowedProfileError, ClosedLoopSlot.kSlot0);

    angle.configure(angleConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);


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