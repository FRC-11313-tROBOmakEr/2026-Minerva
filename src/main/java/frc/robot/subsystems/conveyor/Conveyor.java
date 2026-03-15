
package frc.robot.subsystems.conveyor;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.hardware.TalonFX;

import com.ctre.phoenix6.controls.VelocityVoltage;

public class Conveyor extends SubsystemBase {
  private final TalonFX conveyor = new TalonFX(Conveyor_Constants.Conveyor_ID,Conveyor_Constants.Can);
  public Conveyor() {
    conveyor.getConfigurator().apply(Conveyor_Constants.conveyorConfiguration);
  }
  public void run(){
    conveyor.setControl(new VelocityVoltage(Conveyor_Constants.Conveyor_Start).withSlot(0));
  }
  public void stop(){
    conveyor.stopMotor();
  }

}
