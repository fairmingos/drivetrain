package frc.robot.subsystems

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import edu.wpi.first.wpilibj.drive.DifferentialDrive
import org.junit.Test

class DriveSubSpec {
    @Test fun driveCallsArcadeDriveWithXAndY () {
        val mockDifferentialDrive: DifferentialDrive = mock()
        val driveSub = DriveSub(mockDifferentialDrive)

        driveSub.drive(0.2, 0.8)

        verify(mockDifferentialDrive).arcadeDrive(0.8, 0.2)

        driveSub.drive(-0.8, -0.2)

        verify(mockDifferentialDrive).arcadeDrive(-0.2, -0.8)
    }
}