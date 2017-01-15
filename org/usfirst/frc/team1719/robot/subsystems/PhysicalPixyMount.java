package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IPixyMount;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PhysicalPixyMount extends Subsystem implements IPixyMount{
	
	LogicalPixyMount logic;
	
	public PhysicalPixyMount (Servo pan, Servo tilt){
		logic = new LogicalPixyMount(pan, tilt);
	}

	@Override
	public void disable() {
		//Nothing to disable
	}

	@Override
	public void setX(double angleX) {
		logic.setX(angleX);
		
	}

	@Override
	public void setY(double angleY) {
		logic.setY(angleY);
		
	}

	@Override
	public double getAngleX() {
		return logic.getAngleX();
	}

	@Override
	public double getAngleY() {
		return logic.getAngleY();
	}

	@Override
	protected void initDefaultCommand() {
		//No default command
		
	}

}
