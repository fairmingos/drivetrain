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

    @Test fun processJoystickInput_doesSquareRot () {
        for (i in -10..10) {
            val joystickX = i.toDouble()

            val (_, rot) = driveCommand.processJoystickInput(joystickX, 0.0)

            assert(abs(rot) == joystickX * joystickX)
        }
    }

    @Test fun processJoystickInput_doesSquareFwd () {
        for (i in -10..10) {
            val joystickY = i.toDouble()

            val (fwd) = driveCommand.processJoystickInput(0.0, joystickY)

            assert(abs(fwd) == joystickY * joystickY)
        }
    }

    @Test fun processJoystickInput_preservesSignOfRot () {
        for (i in -10..10) {
            val joystickX = i.toDouble()

            val (_, rot) = driveCommand.processJoystickInput(joystickX, 0.0)

            when {
                joystickX > 0 -> {
                    assert(rot > 0)
                }
                joystickX < 0 -> {
                    assert(rot < 0)
                }
                else -> {
                    assert(rot == 0.0)
                }
            }
        }
    }

    @Test fun processJoystickInput_preservesSignOfFwd () {
        for (i in -10..10) {
            val joystickY = i.toDouble()

            val (fwd) = driveCommand.processJoystickInput(0.0, joystickY)

            when {
                joystickY > 0 -> {
                    assert(fwd > 0)
                }
                joystickY < 0 -> {
                    assert(fwd < 0)
                }
                else -> {
                    assert(fwd == 0.0)
                }
            }
        }
    }

    @Test fun execute_callsDifferentialDriveWithProcessedJoystickInput () {
        doReturn(0.5).`when`(oi).getX()
        doReturn(0.8).`when`(oi).getY()

        driveCommand.execute()

        val (expectedFwd, expectedRot) = driveCommand.processJoystickInput(0.5, 0.8)

        verify(differentialDrive).arcadeDrive(expectedFwd, expectedRot)
    }
}