package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.interfaces.IPixyMount;

import edu.wpi.first.wpilibj.Servo;

public class PixyMountLogic implements IPixyMount {
	
	private Servo pan;
	private Servo tilt;
	private final double MAX = 0.81;
	private final double MIN = 0.1;
	
	public PixyMountLogic (Servo pan, Servo tilt){
		this.pan = pan;
		this.tilt = tilt;
	}

	@Override
	public void disable() {
		//Nothing to disable
	}

	@Override
	public void setX(double angleX) {
		pan.set(clip(angleX));
	}

	@Override
	public void setY(double angleY) {
		tilt.set(clip(angleY));
	}

	@Override
	public double getAngleX() {
		return pan.getAngle();
	}

	@Override
	public double getAngleY() {
		return tilt.getAngle();
	}
	
    private double clip(double d) {
        return Math.min(MAX, Math.max(MIN, d));
    }

	@Override
	public double getX() {
		return pan.get();
	}

	@Override
	public double getY() {
		return tilt.get();
	}

	public String toString() {
		return "Pixy Mount Logic";
	}
}
