package frc.robot.commands

import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robot.OI
import frc.robot.subsystems.DriveSub

class Driver(private val driveSub: DriveSub, private val oi: OI) : CommandBase() {
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
        driveSub.drive(x, y)
    }
}
