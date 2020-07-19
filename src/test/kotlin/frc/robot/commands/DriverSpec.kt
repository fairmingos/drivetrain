package frc.robot.commands

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import frc.robot.OI
import frc.robot.subsystems.DriveSub
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.math.abs

class DriverSpec {
    private lateinit var driveSub: DriveSub
    private lateinit var driver: Driver

    @BeforeEach fun beforeEach () {
        driveSub = mock()
        driver = Driver(driveSub, mock())
    }

    @Test fun processJoystickInput_doesSquareX () {
        for (i in -10..10) {
            val joystickX = i.toDouble()

            val (x) = driver.processJoystickInput(joystickX, 0.0)

            assert(abs(x) == joystickX * joystickX)
        }
    }

    @Test fun processJoystickInput_doesSquareY () {
        for (i in -10..10) {
            val joystickY = i.toDouble()

            val (_, y) = driver.processJoystickInput(0.0, joystickY)

            assert(abs(y) == joystickY * joystickY)
        }
    }

    @Test fun processJoystickInput_preservesSignOfX () {
        for (i in -10..10) {
            val joystickX = i.toDouble()

            val (x) = driver.processJoystickInput(joystickX, 0.0)

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

            val (_, y) = driver.processJoystickInput(0.0, joystickY)

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

    @Test fun execute_callsDriveSubDriveWithProcessedJoystickInput () {
        driver.execute()

        val oi = OI(mock())

        val (expectedX, expectedY) = driver.processJoystickInput(oi.getX(), oi.getY())

        verify(driveSub).drive(expectedX, expectedY)
    }
}