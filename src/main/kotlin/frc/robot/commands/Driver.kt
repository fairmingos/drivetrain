package frc.robot.commands

class Driver {
    fun processJoystickInput(joystickX: Int, joystickY: Int): Pair<Int, Int> {
        val x: Int = if (joystickX < 0) {
            -(joystickX * joystickX)
        } else {
            joystickX * joystickX
        }

        val y: Int = if (joystickY < 0) {
            -(joystickY * joystickY)
        } else {
            joystickY * joystickY
        }

        return Pair(x, y)
    }
}
