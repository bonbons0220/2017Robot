package org.usfirst.frc.team1719.robot;

import org.usfirst.frc.team1719.robot.actuators.LEDCtrl;
import org.usfirst.frc.team1719.robot.actuators.Solenoid;
import org.usfirst.frc.team1719.robot.sensors.E4TOpticalEncoder;
import org.usfirst.frc.team1719.robot.sensors.I2C;
import org.usfirst.frc.team1719.robot.sensors.LIDAR;
import org.usfirst.frc.team1719.robot.sensors.NAVX;
import org.usfirst.frc.team1719.robot.sensors.RS7Encoder;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
    
    /* DIO */
    public static final E4TOpticalEncoder leftDriveEnc = new E4TOpticalEncoder(0, 1, true);
    public static final E4TOpticalEncoder rightDriveEnc = new E4TOpticalEncoder(2, 3, false);
    public static final RS7Encoder shooterEnc1 = new RS7Encoder(4, 5, true);
    public static final RS7Encoder shooterEnc2 = new RS7Encoder(6, 7, false);
    public static final LEDCtrl led = new LEDCtrl(8);
    
    /* PWM */
    public static final SpeedController leftDrive = new Spark(0);
    public static final SpeedController rightDrive = new Spark(1);
    public static final Servo pan = new Servo(2);
    public static final Servo tilt = new Servo(3);
    public static final SpeedController shooterController = new Spark(4);
    public static final SpeedController intakeMotor = new Spark(5);
    public static final SpeedController climberController = new Spark(6);

    /* I2C */
    public static final I2C pixyI2C = new I2C(I2C.Port.kOnboard, 0x54);
    public static final LIDAR lidar = new LIDAR(I2C.Port.kOnboard);
    
    /* MXP */
    public static final NAVX navx = new NAVX(I2C.Port.kMXP);
    
    /* Pneumatics */
    public static final Solenoid shifter = new Solenoid(0);
    public static final Solenoid elevator = new Solenoid (1);
    public static final Solenoid clawTop = new Solenoid (2);
    public static final Solenoid clawBottom = new Solenoid (3);
}
