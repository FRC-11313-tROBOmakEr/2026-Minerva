package frc.robot.subsystems.shooter;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MagnetSensorConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;


/** Add your docs here. */
public class ShooterConstants {

    public static class BigFlyWheel{

        public static final int bigFlyWheel_ID = 12;
        public static final boolean bigFlyWheel_Inverted = false;
        public static double bigFlyWheel = 48;//rps

        public static final TalonFXConfiguration bigflywheelConfiguration = new TalonFXConfiguration()
        .withMotionMagic(new MotionMagicConfigs()
        .withMotionMagicAcceleration(130))

        .withSlot0(new Slot0Configs()
        .withKP(0.35).withKI(0).withKD(0)
        .withKS(0.293).withKV(0.117).withKA(0)
        .withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign))

        .withCurrentLimits(new CurrentLimitsConfigs().withSupplyCurrentLimit(35))
        .withMotorOutput(new MotorOutputConfigs().withInverted(InvertedValue.CounterClockwise_Positive));
    }

    public static class SmallFlyWheel{
        public static final int smallFlyWheel_ID = 17;
        public static final boolean smallFlyWheel_Inverted = false;
    }

    public static class AngleFalcon{
        public static final int angleFalcon_ID = 42;
        public static final boolean angleFalcon_Inverted = false;
        
        public static double angleFalcon = 40;//rps


        public static final TalonFXConfiguration angleFalconConfiguration = new TalonFXConfiguration()
        .withMotionMagic(new MotionMagicConfigs().withMotionMagicAcceleration(130))
        .withSlot0(new Slot0Configs()
        .withKP(0.6).withKI(0).withKD(0)
        .withKS(0).withKV(0).withKA(0)
        .withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign))

        .withFeedback(new FeedbackConfigs()
        .withFeedbackSensorSource(FeedbackSensorSourceValue.RotorSensor))

        .withCurrentLimits(new CurrentLimitsConfigs()
        .withSupplyCurrentLimitEnable(true)
        .withSupplyCurrentLimit(35))
        .withMotorOutput(new MotorOutputConfigs().withInverted(InvertedValue.Clockwise_Positive)
        .withNeutralMode(NeutralModeValue.Brake))
        ;

        public static final CANcoderConfiguration angleEncoderConfig = new CANcoderConfiguration()
        .withMagnetSensor(new MagnetSensorConfigs()
        //.withMagnetOffset(OffSet)
        .withAbsoluteSensorDiscontinuityPoint(0.5)
        //.withSensorDirection(SensorDirectionValue.Clockwise_Positive)
       );
    }

    

}