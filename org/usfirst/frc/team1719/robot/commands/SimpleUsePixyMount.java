package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IPixy;
import org.usfirst.frc.team1719.robot.interfaces.IPixyMount;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SimpleUsePixyMount extends Command {

	private IPixy pixy;
	private IPixyMount mount;
	private final int Y_CENTER = 200 / 2;
	private final int X_CENTER = 320 / 2;
	private final double xKP = 0.00018;
	private final double yKP = 0.00016;
	private final double xKD = 0.0006;
	private final double yKD = 0.0005;
	private double x_cur;
	double lastXDiff = 0;
	double lastYDiff = 0;
	private double y_cur;
	private int frame;

	public SimpleUsePixyMount(IPixy pixy, IPixyMount mount) {
		this.pixy = pixy;
		this.mount = mount;
		requires((Subsystem) mount);
		x_cur = 0.5;
		y_cur = 0.5;
		frame = 0;
	}

	@Override
	public void initialize() {
		frame = 0;
	}

	@Override
	public void execute() {
		if (frame % 1 == 0) {
			// Check for update
			boolean hasVal;
			int targetX = -1;
			int targetY = -1;
			synchronized (pixy) {
				if (pixy.hasBlocks()) {
					hasVal = true;
					targetX = pixy.getBlocks()[0].x;
					targetY = pixy.getBlocks()[0].y;
				} else {
					hasVal = false;
				}
			}
			// If there's an update, run w/ it
			if (hasVal) {
				int x_diff = targetX - X_CENTER;
				int y_diff = targetY - Y_CENTER;
				if (Math.abs(x_diff) < 5) {
					x_diff = 0;
				}
				if (Math.abs(y_diff) < 5) {
					y_diff = 0;
				}
				
				double ystep = y_diff * yKP + (y_diff - lastYDiff) * yKD;
				double xstep = x_diff * xKP + (x_diff - lastXDiff) * xKD;
				
				lastXDiff = x_diff;
				lastYDiff = y_diff;
				x_cur += xstep;
				y_cur -= ystep;
			}
			
			if (x_cur > 1) {
				x_cur = 1;
			}
			else if (x_cur < 0) {
				x_cur = 0;
			}
			
			if (y_cur > 1) {
				y_cur = 1;
			}
			else if (y_cur < 0) {
				y_cur = 0;
			}
			mount.setX(x_cur);
			mount.setY(y_cur);
			
		}
		frame++;

	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
