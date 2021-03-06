package org.usfirst.frc.team1719.robot.mockHardware;

import org.usfirst.frc.team1719.robot.interfaces.IEncoder;

import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * Mock encoder sensor
 * Distance per pulse is 1, distance per revolution is also 1
 * @author Kyle Coblentz
 *
 */

public class MockEncoder implements IEncoder {
	
	double rate = 0;
	double dist = 0;
	
	PIDSourceType pidSourcetype = PIDSourceType.kDisplacement;
	
	double distancePerPulse = 1;
	double distPerRev = 1;
	boolean direction = true;
	
	@Override
	public void config(double distPerRev){
		this.distPerRev = distPerRev;
	}
	
	
	@Override
	public void reset() {
		dist = 0;
	}

	@Override
	public boolean getStopped() {
		return false;
	}

	@Override
	public boolean getDirection() {
		return direction;
	}

	public void setDistance(double newDist) {
		this.dist = newDist;
	}
	
	@Override
	public double getDistance() {
		return dist;
	}

	public void setRate(double newRate) {
		this.rate = newRate;
	}
	
	@Override
	public double getRate() {
		return rate;
	}

	public double getDistancePerPulse() {
		return this.distancePerPulse;
	}
	
	@Override
	public void setDistancePerPulse(double distancePerPulse) {
		this.distancePerPulse = distancePerPulse;
	}

	@Override
	public void setReverseDirection(boolean reverseDirection) {
		this.direction = reverseDirection;
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		this.pidSourcetype = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return pidSourcetype;
	}

	@Override
	public double pidGet() {
		if (pidSourcetype == PIDSourceType.kDisplacement) {
			return getDistance();
		}
		else {
			return getRate();
		}
	}


	@Override
	public void setSamplesToAverage(int sampleNum) {
		// TODO Auto-generated method stub
		
	}

}
