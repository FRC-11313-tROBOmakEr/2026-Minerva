
package frc.robot.subsystems.conveyor;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;

public class Conveyor extends SubsystemBase {
  private final TalonFX conveyor = new TalonFX(Conveyor_Constants.Conveyor_ID);
  public Conveyor() {
    conveyor.getConfigurator().apply(Conveyor_Constants.conveyorConfiguration);
  }
  public void run(){
    conveyor.setControl(new MotionMagicVelocityVoltage(Conveyor_Constants.Conveyor_Start));
  }
  public void stop(){
    conveyor.stopMotor();
  }

}
