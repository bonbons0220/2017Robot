package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightNoPID extends Command {

    IDrive drive;
    IRobot robot;
    IOI oi;
    double spd;
    
    public DriveStraightNoPID(IDrive _drive, IRobot _robot, double _spd) {
        drive = _drive;
        robot = _robot;
        spd = _spd;
        oi = robot.getOI();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drive.moveTank(spd, spd);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return oi.getAbortAutomove();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
