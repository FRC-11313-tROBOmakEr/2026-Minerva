package frc.robot.subsystems.conveyor;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;

public class Conveyor_Constants {
     public static final int Conveyor_ID = 88;

        public static final TalonFXConfiguration conveyorConfiguration = new TalonFXConfiguration()
        .withSlot0(new Slot0Configs().withKP(0.2).withKI(0).withKD(0.5)
        .withKS(0.1).withKV(0.12).withKA(0).withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign));

        public static double Conveyor_Start = 50;
}
