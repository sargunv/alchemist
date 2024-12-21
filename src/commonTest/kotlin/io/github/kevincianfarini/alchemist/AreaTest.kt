package io.github.kevincianfarini.alchemist

import kotlin.test.Test
import kotlin.test.assertEquals

class AreaTest {

    @Test
    fun infinite_area_div_finite_distance_is_infinite() {
        assertEquals(Distance.POSITIVE_INFINITY, Area.POSITIVE_INFINITY / 1.nanometers)
        assertEquals(Distance.NEGATIVE_INFINITY, Area.POSITIVE_INFINITY / (-1).nanometers)
        assertEquals(Distance.NEGATIVE_INFINITY, Area.NEGATIVE_INFINITY / 1.nanometers)
        assertEquals(Distance.POSITIVE_INFINITY, Area.NEGATIVE_INFINITY / (-1).nanometers)
    }

    @Test
    fun finite_area_div_infinite_distance_is_zero() {
        assertEquals(0.nanometers, (Long.MAX_VALUE - 1).mm2 / Distance.POSITIVE_INFINITY)
        assertEquals(0.nanometers, (Long.MAX_VALUE - 1).mm2 / Distance.NEGATIVE_INFINITY)
        assertEquals(0.nanometers, (Long.MIN_VALUE + 1).mm2 / Distance.POSITIVE_INFINITY)
        assertEquals(0.nanometers, (Long.MIN_VALUE + 1).mm2 / Distance.NEGATIVE_INFINITY)
    }

    @Test
    fun nano2_precision_div_distance_is_accurate() {
        assertEquals(123_000_000_000_000.nanometers, 123.mm2 / 1.nanometers)
    }

    @Test
    fun micro2_precision_div_distance_is_accurate() {
        assertEquals(9_223_373.millimeters, 9_223_373.mm2 / 1.millimeters)
    }

    @Test
    fun milli2_precision_div_distance_is_accurate() {
        assertEquals(1.kilometers, 9_223_373_000_000.mm2 / 9_223_373_000_000.nanometers)
    }
}