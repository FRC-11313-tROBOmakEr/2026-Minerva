// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.climber;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.Slot1Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;


public class ClimberConstants {
    public static class string{
        public static final int String_ID = 23;
      

        public static double Out = 80;
        public static double Back = -80;
        public static double position = 20;

        public static final TalonFXConfiguration stringConfiguration = new TalonFXConfiguration()
        .withSlot0(new Slot0Configs()
         .withKP(3).withKI(0).withKD(0)
         .withKS(0).withKV(0).withKA(0).withKG(0))

        .withMotorOutput(new MotorOutputConfigs().withNeutralMode(NeutralModeValue.Brake)
        .withInverted(InvertedValue.Clockwise_Positive))

        .withCurrentLimits(new CurrentLimitsConfigs()
        .withSupplyCurrentLimit(35))
        
        .withMotionMagic(new MotionMagicConfigs()
        .withMotionMagicAcceleration(1500)
        .withMotionMagicCruiseVelocity(500)
        .withMotionMagicJerk(60));

        public static final TalonFXConfiguration string2Configuration = new TalonFXConfiguration()
        .withSlot1(new Slot1Configs() 
        .withKP(0.1).withKI(0).withKD(0)
        .withKS(0).withKV(0).withKA(0).withKG(0))

        .withMotorOutput(new MotorOutputConfigs().withNeutralMode(NeutralModeValue.Brake)
        .withInverted(InvertedValue.Clockwise_Positive))

        .withCurrentLimits(new CurrentLimitsConfigs()
        .withSupplyCurrentLimit(35))

        .withMotionMagic(new MotionMagicConfigs()
        .withMotionMagicAcceleration(2000)
        .withMotionMagicCruiseVelocity(1000)
        .withMotionMagicJerk(60));
    }
    
}
