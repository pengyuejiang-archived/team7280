/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7280.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	
	// the number is coordianted with the numbers on the right side of the Roborio
	public Victor leftFrontMotor = new Victor(1);
	public Victor leftRearMotor = new Victor(0);
	public Victor rightFrontMotor = new Victor(3);
	public Victor rightRearMotor = new Victor(2);
	
	// parameter here is the index of joystick on driverstation
	public Joystick driverStick = new Joystick(0);
	
	
	
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);
	}

	
	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
	}


	@Override
	public void autonomousPeriodic() {
		switch (m_autoSelected) {
			case kCustomAuto:
				// Put custom auto code here
				break;
			case kDefaultAuto:
			default:
				// Put default auto code here
				break;
		}
	}

	
	
	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() { 
		// set the left front motor at a speed
		// 0-stop, 1.0-full speed forward, 0.5-half speed forward; negative means backward
		double driveSpeed = 0.3;
		
		double leftStickVal = driverStick.getRawAxis(1);
		double rightStickVal = driverStick.getRawAxis(5);
		
		
		leftFrontMotor.set(0-leftStickVal);
		leftRearMotor.set(0-leftStickVal);
		rightFrontMotor.set(rightStickVal);
		rightRearMotor.set(rightStickVal);
		
		
//		if(leftStickVal != 0.0) {
//			//leftFrontMotor.set(leftStickVal);
//			leftRearMotor.set(leftStickVal);
//		}
//		
//		if(rightStickVal != 0.0) {
//			//leftFrontMotor.set(rightStickVal);
//			leftRearMotor.set(rightStickVal);
//		}
		
		if(driverStick.getRawButton(1)){
			leftFrontMotor.set(driveSpeed);
			leftRearMotor.set(driveSpeed);
			rightFrontMotor.set(driveSpeed);
			rightRearMotor.set(driveSpeed);
		}

	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
