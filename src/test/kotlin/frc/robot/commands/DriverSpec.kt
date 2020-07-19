package frc.robot.commands

import org.junit.jupiter.api.Test
import kotlin.math.abs

class DriverSpec {
    @Test fun processJoystickInput_doesSquareX () {
        val driver = Driver()

        for (joystickX in -10..10) {
            val (x) = driver.processJoystickInput(joystickX, 0)

            assert(abs(x) == joystickX * joystickX)
        }
    }

    @Test fun processJoystickInput_doesSquareY () {
        val driver = Driver()

        for (joystickY in -10..10) {
            val (_, y) = driver.processJoystickInput(0, joystickY)

            assert(abs(y) == joystickY * joystickY)
        }
    }

    @Test fun processJoystickInput_preservesSignOfX () {
        val driver = Driver()

        for (joystickX in -10..10) {
            val (x) = driver.processJoystickInput(joystickX, 0)

            if (joystickX > 0) {
                assert(x > 0)
            } else if (joystickX < 0) {
                assert(x < 0)
            } else {
                assert(x == 0)
            }
        }
    }

    @Test fun processJoystickInput_preservesSignOfY () {
        val driver = Driver()

        for (joystickY in -10..10) {
            val (_, y) = driver.processJoystickInput(0, joystickY)

            if (joystickY > 0) {
                assert(y > 0)
            } else if (joystickY < 0) {
                assert(y < 0)
            } else {
                assert(y == 0)
            }
        }
    }
}