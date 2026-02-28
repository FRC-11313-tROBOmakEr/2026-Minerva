
package frc.robot;

import java.util.Arrays;

import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;


public class HubNotifier {
    /** Time length of each SHIFTs. */
    private static final int[] shiftLengths = {10, 25, 25, 25, 25, 30, 100};  // 1 extra element to prevent index out of range
    /** Teleop time length. */
    private static final int teleopLength = 140;

    private final CommandGenericHID[] controllers;
    private int shift = 0;
    private double timeOffset;
    private Alliance currentAlliance, inactiveFirst = Alliance.Blue;

    public HubNotifier(CommandGenericHID... controllers) {
        this.controllers = controllers;

        SmartDashboard.putBoolean("HUB Status/Is active", true);
    }

    /** Fetches the game data and starts the timer. */
    public void start() {
        shift = 0;  // reset SHIFT counter
        currentAlliance = DriverStation.getAlliance().orElse(Alliance.Blue);
        while (DriverStation.getGameSpecificMessage().length() <= 0) {}  // keep trying to get the message
        final char gameData = DriverStation.getGameSpecificMessage().charAt(0);
        switch (gameData) {
            case 'R':
                inactiveFirst = Alliance.Red;
                break;
            case 'B':
                inactiveFirst = Alliance.Blue;
                break;
            default:
                DriverStation.reportWarning("Invalid Game Specific data: " + gameData, false);
                break;
        }
        SmartDashboard.putBoolean("HUB Status/Active first?", !currentAlliance.equals(inactiveFirst));
    }

    /** Checks if the state of HUB is about to change; if yes, vibrate the controllers and update the dashboards 
     *  to grab drivers' attention. 
     *  This should be called in every event loop (e.g. robotPeriodic, teleopPeriodic).
     */
    public void check() {
        timeOffset = DriverStation.getMatchTime() - getCurrentShiftEnd();
        SmartDashboard.putString("HUB Status/SHIFT countdown", String.format("%.1f", timeOffset));
        if (timeOffset <= 0) {
            // next SHIFT has started, increase the SHIFT counter and restart the timer
            stopAllRumble();
            shift++;
            // update dashboards
            if (shift != 0 && shift != 5 // during TRANSITION SHIFT and END GAME, both HUBs are active
                && ((inactiveFirst.equals(currentAlliance) && shift % 2 == 1)   // Your HUB is deactivated first --> HUB is inactive during SHIFT 1/3
                || (!inactiveFirst.equals(currentAlliance) && shift % 2 == 0))  // Enemies' HUB is deactivated first --> HUB is inactive during SHIFT 2/4
            ) {
                SmartDashboard.putBoolean("HUB Status/Is active", false);
            } else {
                SmartDashboard.putBoolean("HUB Status/Is active", true);
            }
            setGameStateDisplay();
        } else if (timeOffset <= 3) {
            applyRumble(timeOffset);
        }
    }

    private int getCurrentShiftEnd() {
        return teleopLength - Arrays.stream(
            Arrays.copyOfRange(shiftLengths, 0, shift + 1)
        ).sum();
    }

    private void applyRumble(double countdown) {
        if (3 >= countdown && countdown > 2) {
            // 3 secs left
            for (CommandGenericHID controller: controllers) {
                controller.setRumble(RumbleType.kLeftRumble, 0.3);  // Left only, 30%
            }
        } else if (2 >= countdown && countdown > 1) {
            // 2 secs left
            for (CommandGenericHID controller: controllers) {
                controller.setRumble(RumbleType.kRightRumble, 0.5);  // Right only, 50%
            }
        } else {
            // 1 sec left
            for (CommandGenericHID controller: controllers) {
                controller.setRumble(RumbleType.kBothRumble, 0.7);  // Both, 70%
            }
        }
    }

    private void stopAllRumble() {
        for (CommandGenericHID controller: controllers) {
            controller.setRumble(RumbleType.kBothRumble, 0);
        }
    }

    private void setGameStateDisplay() {
        String gameStateString = "UNKNOWN";
        if (shift == 0) gameStateString = "TRANSITION";
        else if (shift == 5) gameStateString = "ENDGAME";
        else if (1 <= shift && shift <= 4) gameStateString = "SHIFT " + shift;
        SmartDashboard.putString("Game State", gameStateString);
    }
}
