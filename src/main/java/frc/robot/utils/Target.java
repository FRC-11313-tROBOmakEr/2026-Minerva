// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utils;

import java.util.Optional;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.math.interpolation.Interpolatable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;


public final class Target {
//   public static HashMap<Integer, List<Pose2d>> hubMap = new HashMap<>();
//   private boolean isValidTarget = false;
  private static final Pose2d REDPose = new Pose2d(11.91, 4.035, new Rotation2d(180));
  private static final Pose2d BLUEPose = new Pose2d(4.62, 4.035, new Rotation2d(0));
  public static Pose2d targetPose;
  public static Rotation2d target;
  public static CommandSwerveDrivetrain drivetrain;
  public double distance = 1.511;
  public double interval = 0.3011;
  /** Creates a new Target. */

    public static Rotation2d getAimingRotation(Pose2d robotPose) {
  
        Optional<Alliance> alliance = DriverStation.getAlliance();
        if (alliance.get() == Alliance.Red) targetPose = REDPose;
        else targetPose = BLUEPose;
        double deltaX = robotPose.getX() - targetPose.getX();
        double deltaY = robotPose.getY() - targetPose.getY();
        target = new Rotation2d(deltaX,deltaY);
        return target;
    }

     public double getDistanceToTarget(Pose2d robotPose) {
        return robotPose.getTranslation().getDistance(targetPose.getTranslation());
    }

    public double getDegrees(){
      return Math.abs(0.1*getDistanceToTarget(drivetrain.getState().Pose)-0);

    }
    
    // public double getVelocity(){
    //   return 
    // }
   
    

  }