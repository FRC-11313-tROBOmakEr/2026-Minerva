// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shooter;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;

import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkParameters;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkFlexConfig;

/** Add your docs here. */
public class ShooterConstants {
    public static final int BigFlyWheel_ID = 1;
    public static final int SmallFlyWheel_ID = 2;
    public static final int angleMotor_ID = 4;

    public static final boolean BigFlyWheel_Inverted = false;
    public static final boolean SmallFlyWheel_Inverted = false;
    public static final boolean superneo_Inverted = false;

    public static double BigFlyWheel = 80;//rps
    public static final Slot0Configs BigFlyWheel_SLOT0_CONFIGS = new Slot0Configs()
    .withKP(0.2).withKI(0).withKD(0.5)
    .withKS(0.1).withKV(0.12).withKA(0)
    .withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign);

    public static double SmallFlyWheel = 80;//rps
    public static final Slot0Configs SmallFlyWheel_SLOT0_CONFIGS = new Slot0Configs()
    .withKP(0.2).withKI(0).withKD(0.5)
    .withKS(0.1).withKV(0.12).withKA(0)
    .withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign);

    public static double AngleMotor = 0.5;
        
        // Shooter ID
        // public static final double Climb_Angle = -30;
        public static final double ShooterB_Out = 0.65;
        public static final double ShooterS_Out = 0.65;
        public static final double ShooterB_Back = 0;
        public static final double ShooterS_Back = 0;

        // Shooter BigFlyWheel PIDF
        public static final double ShooterB_Out_P = 0.1;
        public static final double ShooterB_Out_I = 0;
        public static final double ShooterB_Out_D = 0.001;
        public static final double ShooterB_Out_F = 0;

        public static final double ShooterB_MAX_ACCEL = 100;
        public static final double ShooterB_MAX_VELOCITY = 50;


        public static final double superneo_Zero = 0;
        public static final double superneo_Out = 3;
        public static final double superneo_Back = 0;

        public static final double superneo_Out_P = 0.1;
        public static final double superneo_Out_I = 0.0;
        public static final double superneo_Out_D = 0.001;
        public static final double superneo_Out_F = 0;

        public static final double superneo_Back_P = 0.1;
        public static final double superneo_Back_I = 0;
        public static final double superneo_Back_D = 0.001;
        public static final double superneo_Back_F = 0;
        public static final double superneo_MAX_ACCEL = 100;
        public static final double superneo_MAX_VELOCITY = 50;

    }