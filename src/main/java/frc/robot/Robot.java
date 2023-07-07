// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private XboxController m_leftStick;

  private final CANSparkMax m_leftMotor = new CANSparkMax(1, MotorType.kBrushless);
  private final CANSparkMax m_leftFollower = new CANSparkMax(2, MotorType.kBrushless);
  private final CANSparkMax m_rightMotor = new CANSparkMax(3, MotorType.kBrushless);
  private final CANSparkMax m_rightFollower = new CANSparkMax(4, MotorType.kBrushless);

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightMotor.setInverted(true);

    m_rightFollower.follow(m_rightMotor);
    m_leftFollower.follow(m_leftMotor);

    m_myRobot = new DifferentialDrive(m_leftMotor, m_rightMotor);
    m_leftStick = new XboxController(0);
  }

  @Override
  public void teleopPeriodic() {
    m_myRobot.arcadeDrive(m_leftStick.getLeftY() * 0.75, m_leftStick.getRightX() * 0.75);
  }
}
