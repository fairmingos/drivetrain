/*----------------------------------------------------------------------------*/ /* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */ /* Open Source Software - may be modified and shared by FRC teams. The code   */ /* must be accompanied by the FIRST BSD license file in the root directory of */ /* the project.                                                               */ /*----------------------------------------------------------------------------*/
package frc.robot

import frc.robot.commands.ExampleCommand
import frc.robot.subsystems.ExampleSubsystem

class RobotContainer {
    private val exampleSubsystem = ExampleSubsystem()

    val autonomousCommand = ExampleCommand(exampleSubsystem)

    private fun configureButtonBindings() {}

    init {
        configureButtonBindings()
    }
}