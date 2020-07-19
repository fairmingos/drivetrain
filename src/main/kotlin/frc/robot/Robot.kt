/*----------------------------------------------------------------------------*/ /* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */ /* Open Source Software - may be modified and shared by FRC teams. The code   */ /* must be accompanied by the FIRST BSD license file in the root directory of */ /* the project.                                                               */ /*----------------------------------------------------------------------------*/
package frc.robot

import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.CommandScheduler

class Robot : TimedRobot() {
    private val robotContainer: RobotContainer = RobotContainer()
    private val autonomousCommand: Command

    init {
        autonomousCommand = robotContainer.autonomousCommand
    }

    override fun robotInit() {}

    override fun robotPeriodic() {
        CommandScheduler.getInstance().run()
    }

    override fun disabledInit() {}

    override fun disabledPeriodic() {}

    override fun autonomousInit() {
        autonomousCommand.schedule()
    }

    override fun autonomousPeriodic() {}

    override fun teleopInit() {
        autonomousCommand.cancel()
    }

    override fun teleopPeriodic() {}

    override fun testInit() {
        CommandScheduler.getInstance().cancelAll()
    }

    override fun testPeriodic() {}
}