// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.climber;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.Slot1Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;


public class ClimberConstants {
    public static final CANBus Can = new CANBus("888");
    public static class string{
        public static final int string_ID = 1;
        public static final int string2_ID = 2;

        public static double out = 80;
        public static double back = -80;

    public static final TalonFXConfiguration stringConfiguration = new TalonFXConfiguration()
        .withSlot0(new Slot0Configs() .withKP(0).withKI(0).withKD(0).withKG(0).withKV(0.2).withKA(0.3))
        .withMotorOutput(new MotorOutputConfigs().withNeutralMode(NeutralModeValue.Brake)
        .withInverted(InvertedValue.Clockwise_Positive))
        .withMotionMagic(new MotionMagicConfigs().withMotionMagicAcceleration(2000).withMotionMagicCruiseVelocity(1000)
        .withMotionMagicJerk(60));
    public static final TalonFXConfiguration string2figuration = new TalonFXConfiguration()
        .withSlot1(new Slot1Configs() .withKP(0).withKI(0).withKD(0.5).withKG(0).withKV(0.2).withKA(0.3))
        .withMotorOutput(new MotorOutputConfigs().withNeutralMode(NeutralModeValue.Brake)
        .withInverted(InvertedValue.Clockwise_Positive))
        .withMotionMagic(new MotionMagicConfigs().withMotionMagicAcceleration(2000).withMotionMagicCruiseVelocity(1000)
        .withMotionMagicJerk(60));
    }
     public static class  angle {
        public static final int Indexer_ID = 1;
        public static double RPM = 90;

      
        public static final double Run_P = 0.1;
        public static final double Run_I = 0;
        public static final double Run_D = 0.001;
        public static final double Run_F = 0;

        public static final double Max_Output = 1;//最大輸出
        public static final double Min_Output = -1;//最小輸出

         public static final double maxAcceleration = 2000;
         public static final double cruiseVelocity = 1000;
         public static final double allowedProfileError = 5;
    }
}
