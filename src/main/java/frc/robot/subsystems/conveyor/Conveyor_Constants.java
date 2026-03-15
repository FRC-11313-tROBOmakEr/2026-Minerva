package frc.robot.subsystems.conveyor;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;

public class Conveyor_Constants {
        
  public static final CANBus Can = new CANBus("888");
     public static final int Conveyor_ID = 20;

        public static final TalonFXConfiguration conveyorConfiguration = new TalonFXConfiguration()
        .withSlot0(new Slot0Configs().withKP(0.05).withKI(0.02).withKD(0)
        .withKS(0.05).withKV(0.03).withKA(0).withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign));

        public static double Conveyor_Start = 30;
}
