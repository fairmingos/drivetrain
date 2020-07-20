package frc.robot.commands

import edu.wpi.first.wpilibj.drive.DifferentialDrive
import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robot.OI

class DriveCommand(private val differentialDrive: DifferentialDrive, private val oi: OI) : CommandBase() {
    fun processJoystickInput(x: Double, y: Double): Pair<Double, Double> {
        val fwd: Double = if (y < 0) {
            -(y * y)
        } else {
            y * y
        }

        val rot: Double = if (x < 0) {
            -(x * x)
        } else {
            x * x
        }

        return Pair(fwd, rot)
    }

    override fun execute() {
        val (fwd, rot) = processJoystickInput(oi.getX(), oi.getY())

        differentialDrive.arcadeDrive(fwd, rot)
    }
}
