// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.Slot1Configs;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;

/** Add your docs here. */
public class Intake_Constants {

    public static final CANBus Can = new CANBus("888");

    public static class  Intake_Neo_Roller {
        public static final int Roller_ID = 1;

        public static double Roller_Start = 0.8;//目前為dutycycle，記得改
        
        public static final double Roller_Start_P = 0.1;
        public static final double Roller_Start_I = 0;
        public static final double Roller_Start_D = 0.001;
        public static final double Roller_Start_F = 0;

        public static final double Max_Output = 1;//最大輸出
        public static final double Min_Output = -1;//最小輸出
    }


    public static class  Intake_Kraken_Roller {
        public static final int Roller_ID = 1;

        public static double Roller_Start = 80;//rps
        public static final Slot0Configs ROLLER_SLOT0_CONFIGS = new Slot0Configs()
        .withKP(0.2).withKI(0).withKD(0.5)
        .withKS(0.1).withKV(0.12).withKA(0)
        .withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign);

    }


    public static class  Intake_Neo_Deploy {
        public static final int Deploy_ID = 2;

        public static double Deploy_Forward = 0.8;//目前為dutycycle，記得改
        public static double Deploy_Back = -0.8;
        
        public static final double Deploy_Forward_P = 0.1;
        public static final double Deploy_Forward_I = 0;
        public static final double Deploy_Forward_D = 0.001;
        public static final double Deploy_Forward_F = 0;
 
        
        public static final double Deploy_Back_P = 0.1;
        public static final double Deploy_Back_I = 0;
        public static final double Deploy_Back_D = 0.001;
        public static final double Deploy_Back_F = 0;

        public static final double Max_Output = 1;//最大輸出
        public static final double Min_Output = -1;//最小輸出
    }


    public static class  Intake_Kraken_Deploy {
        public static final int Deploy_ID = 2;

        public static double Deploy_Start = 10;//
        public static double Deploy_Back = -10;
        public static final Slot0Configs Deploy_Slot0_Configs_F = new Slot0Configs()
        .withKP(0.2).withKI(0).withKD(0.5)
        .withKS(0.1).withKV(0.12).withKA(0)
        .withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign);

        public static double MotionMagicAcceleration = 400;
        public static double MotionMagicCruiseVelocity = 80;
        public static double MotionMagicJerk = 1000;

        public static final Slot1Configs Deploy_Slot1_Configs_B = new Slot1Configs()
        .withKP(0.2).withKI(0).withKD(0.5)
        .withKS(0.1).withKV(0.12).withKA(0)
        .withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign);
    }
 
}
