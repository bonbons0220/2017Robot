package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.interfaces.IOI;
import org.usfirst.frc.team1719.robot.interfaces.IPixy;
import org.usfirst.frc.team1719.robot.interfaces.IPixyMount;
import org.usfirst.frc.team1719.robot.interfaces.IRobot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UsePixyMount extends Command{
	
	private final IPixyMount pixyMount;
	private final IPixy pixyCam;
	private double xServoOut;
	private double yServoOut;
	
	private class SetXServo implements PIDOutput {

		@Override
		public void pidWrite(double output) {
			xServoOut = output;
		}
		
	}
	
	private class SetYServo implements PIDOutput {

		@Override
		public void pidWrite(double output) {
			yServoOut = output;
		}
		
	}
	
	private class GetTargetX implements PIDSource {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}

		@Override
		public double pidGet() {
			if (pixyCam.hasBlocks()) {
				return pixyCam.getBlocks()[0].x;
			}
			else {
				return 0;
			}
		}
		
	}
	
	private class GetTargetY implements PIDSource {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}

		@Override
		public double pidGet() {
			if (pixyCam.hasBlocks()) {
				return pixyCam.getBlocks()[0].y;
			}
			else {
				return 0;
			}
		}
		
	}
	
	double xKP = 0;
	double xKI = 0;
	double xKD = 0;
	
	double yKP = 0;
	double yKI = 0;
	double yKD = 0;
	
	public final String X_KP_STRING = "Pixy x kP: ";
	public final String X_KI_STRING = "Pixy x kI: ";
	public final String X_KD_STRING = "Pixy x kD: ";

	public final String Y_KP_STRING = "Pixy y kP: ";
	public final String Y_KI_STRING = "Pixy y kI: ";
	public final String Y_KD_STRING = "Pixy y kD: ";

	
	PIDController xController;
	PIDController yController;
	
	public UsePixyMount(IPixyMount pixyMount, IPixy pixyCam){
		this.pixyMount = pixyMount;
		this.pixyCam = pixyCam;
		
		xController = new PIDController(xKP, xKI, xKD, new GetTargetX(), new SetXServo());
		yController = new PIDController(yKP, yKI, yKD, new GetTargetY(), new SetYServo());
		
		SmartDashboard.putNumber(X_KP_STRING, 0);
		SmartDashboard.putNumber(X_KI_STRING, 0);
		SmartDashboard.putNumber(X_KD_STRING, 0);

		SmartDashboard.putNumber(Y_KP_STRING, 0);
		SmartDashboard.putNumber(Y_KI_STRING, 0);
		SmartDashboard.putNumber(Y_KD_STRING, 0);
		
		try {
			requires( (Subsystem) pixyMount);
		}
		catch (ClassCastException e) {
			System.out.println("Running unit test on UsePixyMount");
		}

	}
	
	@Override
	public void initialize() {
		xController.setInputRange(0, 1280);
		yController.setInputRange(0, 800);
		
		xController.setOutputRange(0, 1);
		yController.setOutputRange(0, 1);
		
		xController.setToleranceBuffer(5);
		yController.setToleranceBuffer(5);
		
		xController.setAbsoluteTolerance(2);
		yController.setAbsoluteTolerance(2);
		
		xController.setSetpoint(1280 / 2);
		yController.setSetpoint(800 / 2);
		
		xController.enable();
		yController.enable();
	}
	
	public void execute(){
		xKP = SmartDashboard.getNumber(X_KP_STRING, 0);
		xKI = SmartDashboard.getNumber(X_KI_STRING, 0);
		xKD = SmartDashboard.getNumber(X_KD_STRING, 0);

		yKP = SmartDashboard.getNumber(Y_KP_STRING, 0);
		yKI = SmartDashboard.getNumber(Y_KI_STRING, 0);
		yKD = SmartDashboard.getNumber(Y_KD_STRING, 0);

		xController.setPID(xKP, xKI, xKD);
		yController.setPID(yKP, yKI, yKD);
		
		System.out.println("Target x: " + (new GetTargetX().pidGet()));
		System.out.println("Target y: " + (new GetTargetY().pidGet()));

		pixyMount.setX(xServoOut);
		pixyMount.setY(yServoOut);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
