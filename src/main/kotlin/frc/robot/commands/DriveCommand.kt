package frc.robot.commands

import edu.wpi.first.wpilibj.drive.DifferentialDrive
import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robot.OI

class DriveCommand(private val differentialDrive: DifferentialDrive, private val oi: OI) : CommandBase() {
    fun processJoystickInput(joystickX: Double, joystickY: Double): Pair<Double, Double> {
        val x: Double = if (joystickX < 0) {
            -(joystickX * joystickX)
        } else {
            joystickX * joystickX
        }

        val y: Double = if (joystickY < 0) {
            -(joystickY * joystickY)
        } else {
            joystickY * joystickY
        }

        return Pair(x, y)
    }

    override fun execute() {
        val (x, y) = processJoystickInput(oi.getX(), oi.getY())

        differentialDrive.arcadeDrive(y, x)
    }
}
