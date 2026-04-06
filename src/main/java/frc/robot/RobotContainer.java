// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.FollowPathCommand;

import edu.wpi.first.math.geometry.Rotation2d;
import static edu.wpi.first.units.Units.*;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.RobotModeTriggers;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import frc.robot.command.Intakeout;
// import frc.robot.command.Intakeout;
import frc.robot.command.Shoot;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.climber.Climber1690;
import frc.robot.subsystems.indexer.Indexer_Kraken;
import frc.robot.subsystems.indexer.Spindexer_Kraken;
//import frc.robot.subsystems.intake.Intake_Constants;
import frc.robot.subsystems.intake.Intake_Kraken_Kraken;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.ShooterConstants.BigFlyWheel;
import frc.robot.subsystems.conveyor.Conveyor;
import frc.robot.command.Shootstop;
import frc.robot.utils.Target;
import frc.robot.command.auto.rollerstop;
//auto
import frc.robot.command.auto.suck;

public class RobotContainer {
    private double MaxSpeed = 1 * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed
    private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); // 3/4 of a rotation per second max angular velocity

    /* Setting up bindings for necessary control of the swerve drive platform */
    private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
            .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors
    private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
    private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();
    private final SwerveRequest.RobotCentric forwardStraight = new SwerveRequest.RobotCentric()
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage);
    private final SwerveRequest.FieldCentricFacingAngle driveAutoAlign = new SwerveRequest.FieldCentricFacingAngle()
    .withHeadingPID(0.4, 0, 0).withMaxAbsRotationalRate(MaxAngularRate*0.8);

    private final Telemetry logger = new Telemetry(MaxSpeed);
    private final Shooter shooter = new Shooter();
    private final CommandXboxController joystick = new CommandXboxController(0);
    private final CommandXboxController joystick2 = new CommandXboxController(1);

    public final CommandXboxController[] joysticks = {joystick};

    public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();
   

    private final Intake_Kraken_Kraken intake = new Intake_Kraken_Kraken();
    private final Spindexer_Kraken spindexer = new Spindexer_Kraken();
    private final Conveyor conveyor = new Conveyor();
    private final Climber1690 climber = new Climber1690();
    private final Indexer_Kraken indexer = new Indexer_Kraken();
    private final Intakeout intakeout = new Intakeout(intake);
    private final Shoot shoot = new Shoot (shooter, indexer , spindexer, conveyor);
    private final Shootstop shootstop = new Shootstop(shooter, indexer, spindexer, conveyor);
    // private final Intakeout intakeout = new Intakeout(intake);

    //auto
    private final suck suck = new suck(intake);
    private final rollerstop rollerstop = new rollerstop(intake);



    /* Path follower */
    private final SendableChooser<Command> autoChooser;

    public RobotContainer() {
        NamedCommands.registerCommand("shoot", shoot);
        NamedCommands.registerCommand("shootstop", shootstop);
        NamedCommands.registerCommand("intakeout", intakeout);
        NamedCommands.registerCommand("suck", suck);
        NamedCommands.registerCommand("rollerstop", rollerstop);
        autoChooser = AutoBuilder.buildAutoChooser();
        SmartDashboard.putData("Auto Mode", autoChooser);
        

        

        configureBindings();

        // Warmup PathPlanner to avoid Java pauses
        CommandScheduler.getInstance().schedule(FollowPathCommand.warmupCommand());
    }

    private void configureBindings() {
        // Note that X is defined as forward according to WPILib convention,
        // and Y is defined as to the left according to WPILib convention.
        drivetrain.setDefaultCommand(
            // Drivetrain will execute this command periodically
            drivetrain.applyRequest(() ->
                drive.withVelocityX(-joystick.getLeftY() * MaxSpeed) // Drive forward with negative Y (forward)
                    .withVelocityY(-joystick.getLeftX() * MaxSpeed) // Drive left with negative X (left)
                    .withRotationalRate(-joystick.getRightX() * MaxAngularRate) // Drive counterclockwise with negative X (left)
            )
        );

        // Idle while the robot is disabled. This ensures the configured
        // neutral mode is applied to the drive motors while disabled.
        final var idle = new SwerveRequest.Idle();
        RobotModeTriggers.disabled().whileTrue(
            drivetrain.applyRequest(() -> idle).ignoringDisable(true)
        );

        joystick.a().whileTrue(drivetrain.applyRequest(() -> brake));
        joystick.b().whileTrue(drivetrain.applyRequest(() ->
            point.withModuleDirection(new Rotation2d(-joystick.getLeftY(), -joystick.getLeftX()))
        ));

        joystick.x().whileTrue(drivetrain.applyRequest(
            ()->driveAutoAlign
            .withVelocityX(-joystick2.getLeftY() * MaxSpeed) 
            .withVelocityY(-joystick2.getLeftX() * MaxSpeed)
            .withTargetDirection(Target.getAimingRotation(drivetrain.getState().Pose))));

        joystick.povUp().whileTrue(drivetrain.applyRequest(() ->
            forwardStraight.withVelocityX(0.5).withVelocityY(0))
        );
        joystick.povDown().whileTrue(drivetrain.applyRequest(() ->
            forwardStraight.withVelocityX(-0.5).withVelocityY(0))
        );

        // Run SysId routines when holding back/start and X/Y.
        // Note that each routine should be run exactly once in a single log.
        joystick.back().and(joystick.y()).whileTrue(drivetrain.sysIdDynamic(Direction.kForward));
        joystick.back().and(joystick.x()).whileTrue(drivetrain.sysIdDynamic(Direction.kReverse));
        joystick.start().and(joystick.y()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kForward));
        joystick.start().and(joystick.x()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kReverse));

        // Reset the field-centric heading on left bumper press.
        joystick.leftBumper().onTrue(drivetrain.runOnce(drivetrain::seedFieldCentric));

        drivetrain.registerTelemetry(logger::telemeterize);

        //joystick2.rightBumper().onTrue(intakeout);
        joystick2.b().whileTrue(shoot).onFalse(shootstop);
        //joystick2.leftBumper().onTrue(new InstantCommand(intake::Back))
        joystick2.a().whileTrue(intakeout);
                    
        // joystick2.b().whileTrue(new InstantCommand(intake::Out));
        //joystick2.a().whileTrue(new InstantCommand(indexer::run)).onFalse(new InstantCommand(indexer::stop));

        //joystick2.x().whileTrue(new InstantCommand(spindexer::run)).whileFalse(new InstantCommand(spindexer::stop));
        //joystick2.y().whileTrue(new InstantCommand(shooter::out)).whileFalse(new InstantCommand(shooter::stopFlyWheels));
        //joystick2.x().whileTrue(new InstantCommand(intake::Out));
        //joystick2.y().whileTrue(new InstantCommand(conveyor::run)).whileFalse(new InstantCommand(conveyor::stop));
        joystick2.leftBumper().whileTrue(new InstantCommand(shooter::angleup)).onFalse(new InstantCommand(shooter::anglestop));
        joystick2.rightBumper().whileTrue(new InstantCommand(shooter::angledown)).onFalse(new InstantCommand(shooter::anglestop));
        
        joystick2.povUp().onTrue(new InstantCommand(() -> {
            Shooter.degrees -= 0.5;
            shooter.angleAdjust();
        }));
        joystick2.povDown().onTrue(new InstantCommand(() -> {
            Shooter.degrees += 0.5;
            shooter.angleAdjust();
        }));
        joystick2.povLeft().onTrue(new InstantCommand(() -> {
            BigFlyWheel.bigFlyWheel -= 1;
        }));
        joystick2.povRight().onTrue(new InstantCommand(() -> {
            BigFlyWheel.bigFlyWheel += 1;
        }));
        // joystick2.leftBumper().onTrue(new InstantCommand(intake::rollerStart, intake)).onFalse(new InstantCommand(intake::rollerStop));
        //joystick2.rightBumper().whileTrue(new InstantCommand(intake::Back));
        ///onFalse(new InstantCommand(intake::DeployStop));
        joystick2.y().onTrue(new InstantCommand(intake::Back));
        joystick2.x().whileTrue(new InstantCommand(intake::rollerStart)).onFalse(new InstantCommand(intake::rollerStop));
    }   

    public Command getAutonomousCommand() {
        /* Run the path selected from the auto chooser */
        return autoChooser.getSelected();
    }

}
