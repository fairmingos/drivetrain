package frc.robot.commands

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import edu.wpi.first.wpilibj.drive.DifferentialDrive
import frc.robot.OI
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.math.abs

class DriveCommandSpec {
    private lateinit var differentialDrive: DifferentialDrive
    private lateinit var oi: OI
    private lateinit var driveCommand: DriveCommand

    @BeforeEach fun beforeEach () {
        differentialDrive = mock()
        oi = mock()
        driveCommand = DriveCommand(differentialDrive, oi)
    }

    @Test fun processJoystickInput_doesSquareX () {
        for (i in -10..10) {
            val joystickX = i.toDouble()

            val (x) = driveCommand.processJoystickInput(joystickX, 0.0)

            assert(abs(x) == joystickX * joystickX)
        }
    }

    @Test fun processJoystickInput_doesSquareY () {
        for (i in -10..10) {
            val joystickY = i.toDouble()

            val (_, y) = driveCommand.processJoystickInput(0.0, joystickY)

            assert(abs(y) == joystickY * joystickY)
        }
    }

    @Test fun processJoystickInput_preservesSignOfX () {
        for (i in -10..10) {
            val joystickX = i.toDouble()

            val (x) = driveCommand.processJoystickInput(joystickX, 0.0)

            if (joystickX > 0) {
                assert(x > 0)
            } else if (joystickX < 0) {
                assert(x < 0)
            } else {
                assert(x == 0.0)
            }
        }
    }

    @Test fun processJoystickInput_preservesSignOfY () {
        for (i in -10..10) {
            val joystickY = i.toDouble()

            val (_, y) = driveCommand.processJoystickInput(0.0, joystickY)

            when {
                joystickY > 0 -> {
                    assert(y > 0)
                }
                joystickY < 0 -> {
                    assert(y < 0)
                }
                else -> {
                    assert(y == 0.0)
                }
            }
        }
    }

    @Test fun execute_callsDifferentialDriveWithProcessedJoystickInput () {
        doReturn(0.5).`when`(oi).getX()
        doReturn(0.8).`when`(oi).getY()

        driveCommand.execute()

        val (expectedX, expectedY) = driveCommand.processJoystickInput(0.5, 0.8)

        verify(differentialDrive).arcadeDrive(expectedY, expectedX)
    }
}