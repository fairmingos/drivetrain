package frc.robot

import edu.wpi.first.wpilibj.Joystick

open class OI(private val joystick: Joystick) {
    fun getX(): Double {
        return joystick.x
    }

    fun getY(): Double {
        return joystick.y
    }
}
