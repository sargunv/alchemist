package io.github.kevincianfarini.alchemist.type

import io.github.kevincianfarini.alchemist.internal.SaturatingLong
import io.github.kevincianfarini.alchemist.internal.toDecimalString
import io.github.kevincianfarini.alchemist.unit.TemperatureUnit
import io.github.kevincianfarini.alchemist.unit.convertNanokelvinsToThis
import kotlin.jvm.JvmInline
import kotlin.math.roundToLong

/**
 * Represents a temperature and is capable of storing ±9.2 billion Kelvin (±9.2 billion °C, ±16.6 billion °F) at
 * nanokelvin precision.
 */
@JvmInline
public value class Temperature internal constructor(private val rawNanokelvin: SaturatingLong) : Comparable<Temperature> {

    // region Scalar Arithmetic

    /**
     * Returns the number that is the ratio of this and the [other] temperature value.
     */
    public operator fun div(other: Temperature): Double {
        return rawNanokelvin.toDouble() / other.rawNanokelvin.toDouble()
    }

    /**
     * Returns a temperature whose value is this temperature value divided by the specified [scale].
     */
    public operator fun div(scale: Int): Temperature = div(scale.toLong())

    /**
     * Returns a temperature whose value is this temperature value divided by the specified [scale].
     */
    public operator fun div(scale: Long): Temperature = Temperature(rawNanokelvin / scale)

    /**
     * Returns a temperature whose value is the difference between this and the [other] temperature value.
     */
    public operator fun minus(other: Temperature): Temperature = Temperature(rawNanokelvin - other.rawNanokelvin)

    /**
     * Returns a temperature whose value is the sum between this and the [other] temperature value.
     */
    public operator fun plus(other: Temperature): Temperature = Temperature(rawNanokelvin + other.rawNanokelvin)

    /**
     * Returns a temperature whose value is multiplied by the specified [scale].
     */
    public operator fun times(scale: Int): Temperature = times(scale.toLong())

    /**
     * Returns a temperature whose value is multiplied by the specified [scale].
     */
    public operator fun times(scale: Long): Temperature = Temperature(rawNanokelvin * scale)

    // endregion

    // region Temperature to Scalar Conversions

    /**
     * Returns the value of this temperature expressed as a [Long] number of the specified [unit]. Infinite values are
     * converted to either [Long.MAX_VALUE] or [Long.MIN_VALUE] depending on its sign.
     */
    public fun toLong(unit: TemperatureUnit): Long = toDouble(unit).roundToLong()

    public fun toDouble(unit: TemperatureUnit): Double = unit.convertNanokelvinsToThis(rawNanokelvin)

    public fun toString(unit: TemperatureUnit, decimals: Int = 0): String {
        return buildString {
            append(unit.convertNanokelvinsToThis(rawNanokelvin).toDecimalString(decimals))
            append(unit.symbol)
        }
    }

    public override fun toString(): String {
        return toString(toStringUnit(), decimals = 2)
    }

    private fun toStringUnit(): TemperatureUnit = when {
        TemperatureUnit.International.Gigakelvin.convertNanokelvinsToThis(rawNanokelvin) >= 1.0 -> {
            TemperatureUnit.International.Gigakelvin
        }
        TemperatureUnit.International.Megakelvin.convertNanokelvinsToThis(rawNanokelvin) >= 1.0 -> {
            TemperatureUnit.International.Megakelvin
        }
        TemperatureUnit.International.Kilokelvin.convertNanokelvinsToThis(rawNanokelvin) >= 1.0 -> {
            TemperatureUnit.International.Kilokelvin
        }
        TemperatureUnit.International.Kelvin.convertNanokelvinsToThis(rawNanokelvin) >= 1.0 -> {
            TemperatureUnit.International.Kelvin
        }
        TemperatureUnit.International.Millikelvin.convertNanokelvinsToThis(rawNanokelvin) >= 1.0 -> {
            TemperatureUnit.International.Millikelvin
        }
        TemperatureUnit.International.Microkelvin.convertNanokelvinsToThis(rawNanokelvin) >= 1.0 -> {
            TemperatureUnit.International.Microkelvin
        }
        else -> TemperatureUnit.International.Nanokelvin
    }

    // endregion

    // region Comparisons

    public fun isInfinite(): Boolean = rawNanokelvin.isInfinite()

    public fun isFinite(): Boolean = rawNanokelvin.isFinite()

    public override fun compareTo(other: Temperature): Int {
        return rawNanokelvin.compareTo(other.rawNanokelvin)
    }

    // endregion
}