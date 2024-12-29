package io.github.kevincianfarini.alchemist

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.text.Typography.nbsp
import kotlin.time.DurationUnit

class AccelerationTest {

    @Test
    fun simple() {
        assertEquals(
            expected = "1.0${nbsp}m/s²",
            actual = 1_000_000_000.nmPerSecond2.toString()
        )
    }

    @Test
    fun less_simple() {
        assertEquals(
            expected = "8052.970651395847${nbsp}mi/h²",
            actual = 1_000_000_000.nmPerSecond2.toString(
                lengthUnit = LengthUnit.UnitedStatesCustomary.Mile,
                durationUnit = DurationUnit.HOURS,
            )
        )
    }
}