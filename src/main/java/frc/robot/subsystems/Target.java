// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Target extends SubsystemBase {
  public Pose2d targetPose = new Pose2d();
  public static HashMap<Integer, List<Pose2d>> hubMap = new HashMap<>();
  private boolean isValidTarget = false;
  private final Pose2d REDPose = new Pose2d(8.27, 4.035, new Rotation2d(180));
  private final Pose2d BLUEPose = new Pose2d(4.03, 4.035, new Rotation2d(0));

  /** Creates a new Target. */

    public Rotation2d getAimingRotation(Pose2d robotPose) {
        Optional<Alliance> alliance = DriverStation.getAlliance();
        if (alliance.get() == Alliance.Red) targetPose = REDPose;
        else targetPose = BLUEPose;
        Translation2d targetToBot = targetPose.relativeTo(robotPose).getTranslation();
        return new Rotation2d(targetToBot.getX(), targetToBot.getY());
    }

    public double getDistanceToTarget(Pose2d robotPose) {
        return robotPose.getTranslation().getDistance(targetPose.getTranslation());
    }

    public boolean isTargetValid() {
        return isValidTarget;
    } 

    //   public Command getTargetStatus(double aprilTagsID) {
//         return Commands.run(() -> {

//             Optional<Alliance> alliance = DriverStation.getAlliance();
//             if (alliance.isPresent()) {
//                 if (alliance.get() == Alliance.Red
//                         && ((aprilTagsID >= 2 && aprilTagsID <= 5) || (aprilTagsID >= 8 && aprilTagsID <= 11))) {
//                     isValidTarget = true;

//                     targetPose = REDPose;

//                 } else if (alliance.get() == Alliance.Blue
//                         && ((aprilTagsID >= 18 && aprilTagsID <= 21) || (aprilTagsID >= 24 && aprilTagsID <= 27))) {
//                     isValidTarget = true;
//                     targetPose = BLUEPose;

//                 } else {
//                     isValidTarget = false;
//                 }
//             }
//         });
//     }  
}
