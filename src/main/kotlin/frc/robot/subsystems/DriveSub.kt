package frc.robot.subsystems

import edu.wpi.first.wpilibj.drive.DifferentialDrive

open class DriveSub (private val differentialDrive: DifferentialDrive) {
    open fun drive(x: Double, y: Double) {
        differentialDrive.arcadeDrive(y, x)
    }
}
