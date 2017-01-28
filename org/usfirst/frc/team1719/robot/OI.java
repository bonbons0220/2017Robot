package org.usfirst.frc.team1719.robot;

import org.usfirst.frc.team1719.robot.commands.MoveToPosition;
import org.usfirst.frc.team1719.robot.commands.MoveTriangleStunt;
import org.usfirst.frc.team1719.robot.commands.UseExShooter;
import org.usfirst.frc.team1719.robot.interfaces.IOI;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI implements IOI{

    Joystick driver = new Joystick(0);
    Joystick operator = new Joystick(1);
    
    @Override
    public double getLeftX() {
        return driver.getRawAxis(0);
    }

    @Override
    public double getLeftY() {
        return driver.getRawAxis(1);
    }

    @Override
    public double getRightX() {
        return driver.getRawAxis(4);
    }

    @Override
    public double getRightY() {
        return driver.getRawAxis(5);
    }
    
    public boolean getShifter() {
        return driver.getRawButton(1);

    }

	@Override
	public double getDeviceX() {
		return operator.getRawAxis(0);
	}
	
	public double getDeviceY() {
		return operator.getRawAxis(1);
	}
    
	
	
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	public void init(Robot robot){
		Button controlShooter = new JoystickButton(operator, 9);

		controlShooter.whileHeld(new UseExShooter(robot.shooter, robot));
		(new JoystickButton(driver, 3)).whenPressed(new MoveTriangleStunt(robot.tracker, robot.drive, robot));//new MoveToPosition(36, 36, robot.tracker, robot.drive, robot, false));
	}

    @Override
    public boolean getAbortAutomove() {
        return driver.getRawButton(2);
    }

    @Override
    public boolean getResetPIDConstants() {
        return driver.getRawButton(4);
    }
	
}
