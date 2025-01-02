package io.github.kevincianfarini.alchemist.type

import io.github.kevincianfarini.alchemist.internal.SaturatingLong
import io.github.kevincianfarini.alchemist.internal.toDecimalString
import io.github.kevincianfarini.alchemist.unit.LengthUnit
import io.github.kevincianfarini.alchemist.unit.VolumeUnit
import kotlin.jvm.JvmInline
import kotlin.math.pow
import kotlin.math.roundToLong

@JvmInline
public value class Volume internal constructor(private val rawCubicCentimeters: SaturatingLong) : Comparable<Volume> {

    // region SI Arithmetic

    /**
     * Returns the resulting [Length] after dividing this volume over the specified [area].
     */
    public operator fun div(area: Area): Length = TODO()

    /**
     * Returns the resulting [Area] after dividing this volume over the specified [length].
     */
    public operator fun div(length: Length): Area = TODO()

    // endregion

    // region Scalar Arithmetic

    /**
     * Returns the number that is the ratio of this and the [other] volume value.
     */
    public operator fun div(other: Volume): Double {
        return rawCubicCentimeters.toDouble() / other.rawCubicCentimeters.toDouble()
    }

    /**
     * Returns a volume whose value is this volume value divided by the specified [scale].
     */
    public operator fun div(scale: Int): Volume = div(scale.toLong())

    /**
     * Returns a volume whose value is this volume value divided by the specified [scale].
     */
    public operator fun div(scale: Long): Volume = Volume(rawCubicCentimeters / scale)

    /**
     * Returns a volume whose value is the difference between this and the [other] volume value.
     */
    public operator fun minus(other: Volume): Volume = Volume(rawCubicCentimeters - other.rawCubicCentimeters)

    /**
     * Returns a volume whose value is the sum between this and the [other] volume value.
     */
    public operator fun plus(other: Volume): Volume = Volume(rawCubicCentimeters + other.rawCubicCentimeters)

    /**
     * Returns a volume whose value is multiplied by the specified [scale].
     */
    public operator fun times(scale: Int): Volume = times(scale.toLong())

    /**
     * Returns a volume whose value is multiplied by the specified [scale].
     */
    public operator fun times(scale: Long): Volume = Volume(rawCubicCentimeters * scale)

    // endregion

    // region Volume to Scalar Conversions

    /**
     * Returns the value of this velocity expressed as a [Long] number of the specified [unit]. Infinite values
     * are converted to either [Long.MAX_VALUE] or [Long.MIN_VALUE] depending on its sign.
     */
    public fun toLong(unit: VolumeUnit): Long {
        return (rawCubicCentimeters / unit.cubicCentimetersScale).rawValue
    }

    /**
     * Returns the value of this velocity expressed as a [Long] number of the specified [LengthUnit]³. Infinite values
     * are converted to either [Long.MAX_VALUE] or [Long.MIN_VALUE] depending on its sign.
     */
    public fun toLong(cubicUnit: LengthUnit): Long {
        return toDouble(cubicUnit).roundToLong()
    }

    /**
     * Returns the value of this volume expressed as a [Double] number of the specified [unit]. Infinite values
     * are converted to either [Double.POSITIVE_INFINITY] or [Double.NEGATIVE_INFINITY] dependeing on its sign.
     */
    public fun toDouble(unit: VolumeUnit): Double {
        return rawCubicCentimeters.toDouble() / unit.cubicCentimetersScale.toDouble()
    }

    /**
     * Returns the value of this volume expressed as a [Double] number of the specified [LengthUnit]³. Infinite values
     * are converted to either [Double.POSITIVE_INFINITY] or [Double.NEGATIVE_INFINITY] dependeing on its sign.
     */
    public fun toDouble(cubicUnit: LengthUnit): Double {
        val cubicNanos = rawCubicCentimeters.toDouble() * 1e21
        return cubicNanos / cubicUnit.nanometerScale.toDouble().pow(3)
    }

    public fun toString(unit: VolumeUnit, decimals: Int = 0): String = when {
        isInfinite() -> rawCubicCentimeters.toString()
        else -> buildString {
            append(toDouble(unit).toDecimalString(decimals))
            append(unit.symbol)
        }
    }

    public fun toString(cubicUnit: LengthUnit, decimals: Int = 0): String = when {
        isInfinite() -> rawCubicCentimeters.toString()
        else -> buildString {
            append(toDouble(cubicUnit).toDecimalString(decimals))
            append(cubicUnit.symbol)
            append("³")
        }
    }

    public override fun toString(): String {
        val lengthUnit = LengthUnit.International.entries.asReversed().firstOrNull { cubicUnit ->
            toDouble(cubicUnit) >= 1.0
        }
        return toString(lengthUnit ?: LengthUnit.International.Centimeter, decimals = 2)
    }

    // endregion

    // region Comparisons

    public fun isInfinite(): Boolean = rawCubicCentimeters.isInfinite()

    public fun isFinite(): Boolean = rawCubicCentimeters.isFinite()

    override fun compareTo(other: Volume): Int = rawCubicCentimeters.compareTo(other.rawCubicCentimeters)

    // endregion
}
