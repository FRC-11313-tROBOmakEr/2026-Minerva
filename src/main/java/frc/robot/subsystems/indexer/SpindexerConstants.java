// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.indexer;

import com.ctre.phoenix6.CANBus;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class SpindexerConstants  {
  public static final CANBus Can = new CANBus("888");

  public static final class Spindexer_Karken {
    public static final int Spindexer_ID = 1;
  }
  public static final class  Spindexer_Neo {
    public static final int Spindexer_ID = 1;
    public static double RPM = 100;

      
    public static final double Run_P = 0.1;
    public static final double Run_I = 0;
    public static final double Run_D = 0.001;
    public static final double Run_F = 0;

    public static final double Max_Output = 1;//最大輸出
    public static final double Min_Output = -1;//最小輸出
  }


 

 
}
