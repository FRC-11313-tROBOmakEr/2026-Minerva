// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.hardware.CANcoder;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;

import com.ctre.phoenix6.configs.Slot0Configs;

public class Intake_Kraken_Kraken extends SubsystemBase {
  private final TalonFX roller = new TalonFX(Intake_Constants.Intake_Kraken_Roller.Roller_ID, Intake_Constants.Can);
  private final TalonFX deploy = new TalonFX(Intake_Constants.Intake_Kraken_Deploy.Deploy_ID, Intake_Constants.Can);
  private final CANcoder encoder = new CANcoder(Intake_Constants.Intake_Kraken_Deploy.Encoder_ID, Intake_Constants.Can);
  public double dynamic;

  public Intake_Kraken_Kraken() {
    roller.getConfigurator().apply(Intake_Constants.Intake_Kraken_Roller.rollerConfiguration);
    deploy.getConfigurator().apply(Intake_Constants.Intake_Kraken_Deploy.deployConfiguration);
    deploy.setPosition(0);

    encoder.setPosition(0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Intake Pos", getCurrentPosition());
    // double radians = ((deploy.getPosition().getValueAsDouble())-Intake_Constants.Intake_Kraken_Deploy.OffSet/Intake_Constants.Intake_Kraken_Deploy.GearRatio*2*Math.PI);
    double radians = (deploy.getPosition().getValueAsDouble()/Intake_Constants.Intake_Kraken_Deploy.GearRatio*2*Math.PI);
    double dynamicFF = Math.cos(radians)*Intake_Constants.Intake_Kraken_Deploy.Kg_Max;
    this.dynamic = dynamicFF;
  }

  public void rollerStart() {
    System.out.println("roller start");
    roller.setControl(new VelocityVoltage(Intake_Constants.Intake_Kraken_Roller.Roller_Start).withSlot(0));
  }

  public void rollerStop() {
    roller.stopMotor();
  }


  // public void deployOut() {
  //   deploy.setControl(new MotionMagicVoltage(Intake_Constants.Intake_Kraken_Deploy.Deploy_Start).withSlot(0));
  // }

  // public void deployBack() {
  //   deploy.set(-0.43);
  // }

  public void Out(){
    // System.out.println("deploy out");
    // deploy.setControl(new MotionMagicVoltage(Intake_Constants.Intake_Kraken_Deploy.OutPositionInRot).withSlot(0)
    // .withFeedForward(this.dynamic));
    System.out.println(deploy.getPosition().getValueAsDouble());
  }
  public void Back(){
    System.out.println("deploy back");
   deploy.setControl(new MotionMagicVoltage(Intake_Constants.Intake_Kraken_Deploy.BackPositionInRot).withSlot(1)
   .withFeedForward(this.dynamic));
  }
  public void DeployStop(){
    deploy.getPosition().getValueAsDouble();
    deploy.stopMotor();
  }

  public double getCurrentPosition(){
    return deploy.getPosition().getValueAsDouble();
  }



}
