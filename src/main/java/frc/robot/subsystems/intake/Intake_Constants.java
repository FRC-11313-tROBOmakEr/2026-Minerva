// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import java.lang.module.Configuration;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.ClosedLoopGeneralConfigs;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.Slot1Configs;
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import frc.robot.generated.TunerConstants;


/** Add your docs here. */
public class Intake_Constants {

    public static final CANBus Can = new CANBus("888");

    public static class  Intake_Neo_Roller {
        public static final int Roller_ID = 1;
        public static double Roller_Start = 10;//目前為dutycycle，記得改

        public static final double Roller_Start_P = 0.1;
        public static final double Roller_Start_I = 0;
        public static final double Roller_Start_D = 0.001;
        public static final double Roller_Start_F = 0;

        public static final double maxAcceleration = 5;
        public static final double cruiseVelocity = 2 ;

    }

    public static class  Intake_Kraken_Roller {
        public static final int Roller_ID = 1;

        public static final TalonFXConfiguration rollerConfiguration = new TalonFXConfiguration()
        .withSlot0(new Slot0Configs().withKP(0.2).withKI(0).withKD(0.5)
        .withKS(0.1).withKV(0.12).withKA(0).withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign));

        public static double Roller_Start = 50;//rps


    }

    public static class  Intake_Neo_Deploy {
        public static final int Deploy_ID = 2;

        public static double Deploy_Forward = 10;//目前為dutycycle，記得改
        public static double Deploy_Back = -10;
        
        public static final double Deploy_Forward_P = 0.1;
        public static final double Deploy_Forward_I = 0;
        public static final double Deploy_Forward_D = 0.001;
        public static final double Deploy_Forward_F = 0;
        
        public static final double Deploy_Back_P = 0.1;
        public static final double Deploy_Back_I = 0;
        public static final double Deploy_Back_D = 0.001;
        public static final double Deploy_Back_F = 0;

        public static final double maxAcceleration = 5;
        public static final double cruiseVelocity = 2 ;
    }


    public static class  Intake_Kraken_Deploy {
        public static final int Deploy_ID = 2;

        public static double Deploy_Start = 10;
        public static double Deploy_Back = -10;

        public static double OutRotaintion = 0.25;
        public static double BackRotaintion = 0;

        
        public static final TalonFXConfiguration deployConfiguration = new TalonFXConfiguration()
        .withSlot0(new Slot0Configs() .withKP(0).withKI(0).withKD(0).withKS(0).withKV(0).withKA(0)
        .withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign))
        .withSlot1(new Slot1Configs().withKP(0).withKI(0).withKD(0).withKS(0).withKV(0).withKA(0)
        .withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign))

        .withMotionMagic(new MotionMagicConfigs()
        .withMotionMagicCruiseVelocity(50)
        .withMotionMagicAcceleration(5))

        .withFeedback(new FeedbackConfigs()
        .withFeedbackSensorSource(FeedbackSensorSourceValue.RemoteCANcoder)
        .withFeedbackRemoteSensorID(TunerConstants.FrontLeft.EncoderId)
        .withSensorToMechanismRatio(16.0/44.0))
        
        .withMotorOutput(new MotorOutputConfigs().withNeutralMode(NeutralModeValue.Brake))
        .withCurrentLimits(new CurrentLimitsConfigs()
        .withStatorCurrentLimit(40)
        .withStatorCurrentLimitEnable(true))
        
        .withClosedLoopGeneral(new ClosedLoopGeneralConfigs()
        .withContinuousWrap(false))
        
        .withSoftwareLimitSwitch(new SoftwareLimitSwitchConfigs()
        .withForwardSoftLimitThreshold(0.28)
        .withForwardSoftLimitEnable(true)
        .withReverseSoftLimitThreshold(-0.2)
        .withReverseSoftLimitEnable(true));
        
    }
 
}
