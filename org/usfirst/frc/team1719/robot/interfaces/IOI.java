package org.usfirst.frc.team1719.robot.interfaces;

public interface IOI {
    public double getLeftX();
    public double getLeftY();
    public double getRightX();
    public double getRightY();
    public boolean getShifter();
    public double getDeviceX();
    public double getDeviceY();
    public boolean getAbortAutomove();
    public boolean getResetPIDConstants();
    public double getServoX();
    public double getServoY();
    public boolean getCancelScan();
    public boolean getRevUpShooter();
}
