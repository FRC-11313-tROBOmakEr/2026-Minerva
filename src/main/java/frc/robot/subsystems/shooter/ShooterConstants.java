package frc.robot.subsystems.shooter;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;


/** Add your docs here. */
public class ShooterConstants {

    public static final int angleMotor_ID = 4;

    public static final boolean superneo_Inverted = false;

    public static class BigFlyWheel{

        public static final int bigFlyWheel_ID = 12;
        public static final boolean bigFlyWheel_Inverted = false;
        public static double bigFlyWheel = 48;//rps

        public static final TalonFXConfiguration bigflywheelConfiguration = new TalonFXConfiguration()
        .withMotionMagic(new MotionMagicConfigs().withMotionMagicAcceleration(130))
        .withSlot0(new Slot0Configs().withKP(0.3).withKI(0).withKD(0)
        .withKS(0.225).withKV(0.12).withKA(0.3).withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign))
        .withCurrentLimits(new CurrentLimitsConfigs().withSupplyCurrentLimit(35))
        .withMotorOutput(new MotorOutputConfigs().withInverted(InvertedValue.Clockwise_Positive))
        ;
    }

    public static class SmallFlyWheel{
        public static final int smallFlyWheel_ID = 17;
        public static final boolean smallFlyWheel_Inverted = false;
    }
    public static class Angle  {
        public static final int Angle_ID = 8;
        public static double RPM = 20;

      
        public static final double Run_P = 0.05;
        public static final double Run_I = 0;
        public static final double Run_D = 0;
        public static final double Run_F = 0;

        public static final double Max_Output = 1;//最大輸出
        public static final double Min_Output = -1;//最小輸出

         public static final double maxAcceleration = 80;
         public static final double cruiseVelocity = 10;
         public static final double allowedProfileError = 50;
    }

    public static class AngleFalcon{
        public static final int angleFalcon_ID = 90;
        public static final boolean bigFlyWheel_Inverted = false;
        public static double bigFlyWheel = 40;//rps


        public static final TalonFXConfiguration angleFalconConfiguration = new TalonFXConfiguration()
        .withMotionMagic(new MotionMagicConfigs().withMotionMagicAcceleration(130))
        .withSlot0(new Slot0Configs().withKP(0.3).withKI(0).withKD(0)
        .withKS(0).withKV(0).withKA(0).withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign))
        .withCurrentLimits(new CurrentLimitsConfigs().withSupplyCurrentLimit(35))
        .withMotorOutput(new MotorOutputConfigs().withInverted(InvertedValue.Clockwise_Positive))
        ;
    }

    

}