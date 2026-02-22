// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.indexer;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.Slot1Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;
import com.revrobotics.spark.config.SparkMaxConfig;


/** Add your docs here. */
public class IndexerConstants {
    public static final CANBus Can = new CANBus("888");
    

    public static class  Indexer_Kraken {
        public static final int Indexer_ID = 1;

        public static double run = 80;//rps
        public static final TalonFXConfiguration IndexerConfiguration = new TalonFXConfiguration()
        .withSlot0(new Slot0Configs() .withKP(0.2).withKI(0).withKD(0.5).withKS(0.1).withKV(0.12).withKA(0)
        .withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign))
        .withMotionMagic(new MotionMagicConfigs().withMotionMagicAcceleration(400).withMotionMagicCruiseVelocity(80)
        .withMotionMagicJerk(1000))
        .withMotorOutput(new MotorOutputConfigs().withNeutralMode(NeutralModeValue.Brake).withInverted(InvertedValue.Clockwise_Positive));
        }
        
    

    public static class  Indexer_Neo {
        public static final int Indexer_ID = 1;

        public static double Run = 0.8;//目前為dutycycle，記得改
        
        public static final double Run_P = 0.1;
        public static final double Run_I = 0;
        public static final double Run_D = 0.001;
        public static final double Run_F = 0;

        public static final double Max_Output = 1;//最大輸出
        public static final double Min_Output = -1;//最小輸出
    }


}
