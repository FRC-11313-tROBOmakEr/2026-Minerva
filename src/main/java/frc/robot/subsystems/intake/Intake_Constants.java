// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import com.ctre.phoenix6.CANBus;

import com.ctre.phoenix6.configs.ClosedLoopGeneralConfigs;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.Slot1Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;

import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;


/** Add your docs here. */
public class Intake_Constants {

    public static final CANBus Can = new CANBus("888");

    public static class  Intake_Neo_Roller {
        public static final int Roller_ID = 11;
        public static double Roller_Start = 10;//目前為dutycycle，記得改

        public static final double Roller_Start_P = 0.1;
        public static final double Roller_Start_I = 0;
        public static final double Roller_Start_D = 0.001;
        public static final double Roller_Start_F = 0;

        public static final double maxAcceleration = 5;
        public static final double cruiseVelocity = 2 ;

    }

    public static class  Intake_Kraken_Roller {
        public static final int Roller_ID = 11;

        public static final TalonFXConfiguration rollerConfiguration = new TalonFXConfiguration()
        .withSlot0(new Slot0Configs().withKP(0.12).withKI(0).withKD(0)
        .withKS(0.05).withKV(0.1).withKA(0).withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign));

        public static double Roller_Start = -40;//rps


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
        public static final int Deploy_ID = 13;
        public static final int Encoder_ID = 30;

        public static double Deploy_Start = 10;
        public static double Deploy_Back = -10;

        public static double OutPositionInRot = 0.38;
        public static double BackPositionInRot = 0;


        

        
        public static final TalonFXConfiguration deployConfiguration = new TalonFXConfiguration()
        .withSlot0(new Slot0Configs().withKP(0.255).withKI(0).withKD(0.01).withKS(0.1).withKV(0.02).withKA(0)
        .withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign))
        .withSlot1(new Slot1Configs().withKP(0.5).withKI(0.02).withKD(0.01).withKS(0.5).withKV(0.8).withKA(0.2)
        .withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign))

        .withMotionMagic(new MotionMagicConfigs()
        .withMotionMagicCruiseVelocity(2)
        .withMotionMagicAcceleration(1))

        .withFeedback(new FeedbackConfigs()
        .withFeedbackSensorSource(FeedbackSensorSourceValue.RemoteCANcoder)
        .withFeedbackRemoteSensorID(Encoder_ID)
        .withSensorToMechanismRatio(44.0/16.0))
        
        .withMotorOutput(new MotorOutputConfigs().withNeutralMode(NeutralModeValue.Brake))
        .withCurrentLimits(new CurrentLimitsConfigs()
        .withStatorCurrentLimit(60)
        .withStatorCurrentLimitEnable(true))
        
        .withClosedLoopGeneral(new ClosedLoopGeneralConfigs()
        .withContinuousWrap(false));
        
        // .withSoftwareLimitSwitch(new SoftwareLimitSwitchConfigs()
        // .withForwardSoftLimitThreshold(0.9)
        // .withForwardSoftLimitEnable(true)
        // .withReverseSoftLimitThreshold(-0.2)
        // .withReverseSoftLimitEnable(true))
        
        // .withHardwareLimitSwitch(new HardwareLimitSwitchConfigs()
        // .withForwardLimitEnable(true)
        // .withForwardLimitRemoteSensorID(30)
        // .withForwardLimitAutosetPositionEnable(true)
        // .withForwardLimitAutosetPositionValue(0.8)
        // .withReverseLimitEnable(true)
        // .withReverseLimitRemoteSensorID(30)
        // .withReverseLimitAutosetPositionEnable(true)
        // .withReverseLimitAutosetPositionValue(0.8)
        
        
    }
}

//     public static class  Intake_Kraken_Deploy {
//         public static final int Deploy_ID = 13;
//         public static final int Encoder_ID = 30;

//         public static double Deploy_Start = 10;
//         public static double Deploy_Back = -10;

//         public static double OutPositionInRot = 0.4;
//         public static double BackPositionInRot = 0;

//         public static double Kg_Max = 0.4;
//         public static double GearRatio = (44.0/16.0)*3;
//         // public static double OffSet = 0;



        
//         public static final TalonFXConfiguration deployConfiguration = new TalonFXConfiguration()
//         .withSlot0(new Slot0Configs().withKP(2).withKI(0).withKD(0.01).withKS(0.1).withKV(0.02).withKA(0)
//         .withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign))
//         .withSlot1(new Slot1Configs().withKP(3).withKI(0.02).withKD(0.01).withKS(0.5).withKV(0.8).withKA(0.2)
//         .withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign))

//         .withMotionMagic(new MotionMagicConfigs()
//         .withMotionMagicCruiseVelocity(2)
//         .withMotionMagicAcceleration(1))

//         .withFeedback(new FeedbackConfigs()
//         .withFeedbackSensorSource(FeedbackSensorSourceValue.RemoteCANcoder)
//         .withFeedbackRemoteSensorID(Encoder_ID)
//         .withSensorToMechanismRatio(44.0/16.0))
        
//         .withMotorOutput(new MotorOutputConfigs().withNeutralMode(NeutralModeValue.Brake))
//         .withCurrentLimits(new CurrentLimitsConfigs()
//         .withStatorCurrentLimit(60)
//         .withStatorCurrentLimitEnable(true))
        
//         .withClosedLoopGeneral(new ClosedLoopGeneralConfigs()
//         .withContinuousWrap(false));
        
//         // .withSoftwareLimitSwitch(new SoftwareLimitSwitchConfigs()
//         // .withForwardSoftLimitThreshold(0.9)
//         // .withForwardSoftLimitEnable(true)
//         // .withReverseSoftLimitThreshold(-0.2)
//         // .withReverseSoftLimitEnable(true))
        
//         // .withHardwareLimitSwitch(new HardwareLimitSwitchConfigs()
//         // .withForwardLimitEnable(true)
//         // .withForwardLimitRemoteSensorID(30)
//         // .withForwardLimitAutosetPositionEnable(true)
//         // .withForwardLimitAutosetPositionValue(0.8)
//         // .withReverseLimitEnable(true)
//         // .withReverseLimitRemoteSensorID(30)
//         // .withReverseLimitAutosetPositionEnable(true)
//         // .withReverseLimitAutosetPositionValue(0.8)
        
        
//     }
 
// }
