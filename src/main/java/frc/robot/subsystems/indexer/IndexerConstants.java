// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.indexer;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;


/** Add your docs here. */
public class IndexerConstants {

    public static class  Indexer_Kraken {
        public static final int Indexer_ID = 33;

        public static double Run = 450;
        public static final TalonFXConfiguration IndexerConfiguration = new TalonFXConfiguration()
        .withSlot0(new Slot0Configs() 
        .withKP(1).withKI(0).withKD(0.001))
        .withMotorOutput(new MotorOutputConfigs().withNeutralMode(NeutralModeValue.Brake)
        .withInverted(InvertedValue.Clockwise_Positive))
        .withCurrentLimits(new CurrentLimitsConfigs()
        .withSupplyCurrentLimit(35));

    }


}
