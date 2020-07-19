package frc.robot

import com.nhaarman.mockitokotlin2.*
import edu.wpi.first.wpilibj.Joystick
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class OISpec {
    @Test fun getX_returnsJoystickX () {
        val joystick: Joystick = mock()
        val oi = OI(joystick)

        doReturn(0.2).`when`(joystick).x
        assert(oi.getX() == 0.2)

        doReturn(-0.7).`when`(joystick).x
        assert(oi.getX() == -0.7)
    }

    @Test fun getY_returnsJoystickY () {
        val joystick: Joystick = mock()
        val oi = OI(joystick)

        doReturn(0.8).`when`(joystick).y
        assert(oi.getY() == 0.8)

        doReturn(-0.1).`when`(joystick).y
        assert(oi.getY() == -0.1)
    }
}