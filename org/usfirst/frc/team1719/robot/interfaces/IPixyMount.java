package org.usfirst.frc.team1719.robot.interfaces;

public interface IPixyMount extends GenericSubsystem{

	void setX(double angleX);
	void setY(double angleY);
	
	double getX();
	double getY();
	double getAngleX();
	double getAngleY();
	
	VisionTarget getTarget();
	void setTarget(VisionTarget target);
}
