package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.hardware.TalonFX;

import com.ctre.phoenix6.controls.VelocityVoltage;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.AbsoluteEncoderConfig;





public class Intake_Neo_Kraken extends SubsystemBase {
  private final TalonFX roller = new TalonFX(Intake_Constants.Intake_Kraken_Roller.Roller_ID, Intake_Constants.Can);
  private final SparkMax deploy = new SparkMax(Intake_Constants.Intake_Neo_Deploy.Deploy_ID, SparkLowLevel.MotorType.kBrushless);
  private final SparkClosedLoopController deployctrl = deploy.getClosedLoopController();
  private final SparkMaxConfig deployConfig = new SparkMaxConfig();
  private final AbsoluteEncoderConfig encoderConfig = new AbsoluteEncoderConfig();

  
  public Intake_Neo_Kraken() {

    roller.getConfigurator().apply(Intake_Constants.Intake_Kraken_Roller.rollerConfiguration);

    deployConfig.secondaryCurrentLimit(40).idleMode(IdleMode.kBrake);

    encoderConfig.inverted(false).positionConversionFactor(360);

    deployConfig.absoluteEncoder.apply(encoderConfig);

    deployConfig.closedLoop
        .feedbackSensor(FeedbackSensor.kAbsoluteEncoder)
        .pid(Intake_Constants.Intake_Neo_Deploy.Deploy_Forward_P,
             Intake_Constants.Intake_Neo_Deploy.Deploy_Forward_I, 
             Intake_Constants.Intake_Neo_Deploy.Deploy_Forward_D, 
             ClosedLoopSlot.kSlot0)
        .maxMotion.allowedProfileError(0.05, ClosedLoopSlot.kSlot0)
        .maxAcceleration(Intake_Constants.Intake_Neo_Deploy.maxAcceleration)
        .cruiseVelocity(Intake_Constants.Intake_Neo_Deploy.cruiseVelocity);

    deployConfig.closedLoop.feedForward
        .kV(Intake_Constants.Intake_Neo_Deploy.Deploy_Forward_F, ClosedLoopSlot.kSlot0);

    deployConfig.closedLoop
        .feedbackSensor(FeedbackSensor.kAbsoluteEncoder)
        .pid(Intake_Constants.Intake_Neo_Deploy.Deploy_Back_P,
             Intake_Constants.Intake_Neo_Deploy.Deploy_Back_I, 
             Intake_Constants.Intake_Neo_Deploy.Deploy_Back_D, 
             ClosedLoopSlot.kSlot1)
        .maxMotion.allowedProfileError(0.05, ClosedLoopSlot.kSlot1)
        .maxAcceleration(Intake_Constants.Intake_Neo_Deploy.maxAcceleration)
        .cruiseVelocity(Intake_Constants.Intake_Neo_Deploy.cruiseVelocity);

    deployConfig.closedLoop.feedForward
        .kV(Intake_Constants.Intake_Neo_Deploy.Deploy_Back_F, ClosedLoopSlot.kSlot1);
    deploy.configure(deployConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  


   

    
  }

  public void rollerStart(){
    roller.setControl(new VelocityVoltage(Intake_Constants.Intake_Kraken_Roller.Roller_Start).withSlot(0));
  }
  
  public void rollerStop(){
    roller.stopMotor();
  }

    public void deployFoward(){
    deployctrl.setSetpoint(Intake_Constants.Intake_Neo_Deploy.Deploy_Forward, ControlType.kMAXMotionPositionControl, ClosedLoopSlot.kSlot0);
  }
  
  public void deployBack(){
   deployctrl.setSetpoint(Intake_Constants.Intake_Neo_Deploy.Deploy_Back, ControlType.kMAXMotionPositionControl, ClosedLoopSlot.kSlot1);
  }


}
