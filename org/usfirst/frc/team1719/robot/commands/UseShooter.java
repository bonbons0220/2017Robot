package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IExShooter;
import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * Simple command for managing the Experimental Shooter
 * Control Scheme: While held, sets the motor to square of the joystick
 * @author jess
 *
 */
public class UseShooter extends Command implements PIDOutput {

	public static final String SHOOTER_KP = "Shooter kP: ";
	public static final String SHOOTER_KI = "Shooter kI: ";
	public static final String SHOOTER_KD = "Shooter kD: ";
	public static final String SHOOTER_KF = "Shooter kF: ";


	private double kP;
	private double kI;
	private double kD;
	private double kF;
	
	private final IExShooter exshooter;
	private final IOI oi;
	
	private final double DEADZONE_TOLERANCE = 0.05;
	private final double MAX_SPEED = 50;
	double motorOutput;
	
	private PIDController velocityController;
	
	public UseShooter(IExShooter exshooter, IRobot robot) {
		
		SmartDashboard.putNumber(SHOOTER_KP, 0);
		SmartDashboard.putNumber(SHOOTER_KI, 0);
		SmartDashboard.putNumber(SHOOTER_KD, 0);
		SmartDashboard.putNumber(SHOOTER_KF, 0);
		
		this.exshooter = exshooter;
		oi = robot.getOI();
		try {
            requires((Subsystem) exshooter);
        } catch(ClassCastException e) {
            System.out.println("Running unit test on UseDrive command");
        }
		
		velocityController = new PIDController(kP, kI, kD, kF, exshooter, this);
		
	}
	
	@Override
	protected void initialize() {
		exshooter.setPIDSourceType(PIDSourceType.kRate);
		velocityController.setInputRange(-(MAX_SPEED * 1.5), MAX_SPEED * 1.5);
		velocityController.setOutputRange(-1, 1);
		velocityController.setContinuous(false);
		velocityController.setPercentTolerance(2);
		velocityController.setToleranceBuffer(3);
	}
	
	
	/**
	 * Squares Joystick value while preserving sign of the original value
	 */
	
	@Override
	public void execute(){
		
	}


	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void pidWrite(double output) {
		this.motorOutput = output;
		
	}

}
