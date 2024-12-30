package io.github.kevincianfarini.alchemist

import io.github.kevincianfarini.alchemist.internal.SaturatingLong
import io.github.kevincianfarini.alchemist.internal.saturated
import io.github.kevincianfarini.alchemist.internal.toDecimalString
import kotlin.jvm.JvmInline
import kotlin.math.pow
import kotlin.math.roundToLong
import kotlin.text.Typography.nbsp

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
        return toString(lengthUnit ?: LengthUnit.International.Nanometer, decimals = 2)
    }

    // endregion

    // region Comparisons

    public fun isInfinite(): Boolean = rawCubicCentimeters.isInfinite()

    public fun isFinite(): Boolean = rawCubicCentimeters.isFinite()

    override fun compareTo(other: Volume): Int = rawCubicCentimeters.compareTo(other.rawCubicCentimeters)

    // endregion
}

// region Scalar to Volume Conversions

public inline val Int.milliliters: Volume get() = toVolume(VolumeUnit.Metric.Milliliter)
public inline val Long.milliliters: Volume get() = toVolume(VolumeUnit.Metric.Milliliter)

public inline val Int.liters: Volume get() = toVolume(VolumeUnit.Metric.Liter)
public inline val Long.liters: Volume get() = toVolume(VolumeUnit.Metric.Liter)
public inline val Double.liters: Volume get() = toVolume(VolumeUnit.Metric.Liter)

public inline val Int.kiloliters: Volume get() = toVolume(VolumeUnit.Metric.Kiloliter)
public inline val Long.kiloliters: Volume get() = toVolume(VolumeUnit.Metric.Kiloliter)
public inline val Double.kiloliters: Volume get() = toVolume(VolumeUnit.Metric.Kiloliter)

public inline val Int.megaliters: Volume get() = toVolume(VolumeUnit.Metric.Megaliter)
public inline val Long.megaliters: Volume get() = toVolume(VolumeUnit.Metric.Megaliter)
public inline val Double.megaliters: Volume get() = toVolume(VolumeUnit.Metric.Megaliter)

public inline val Int.gigaliters: Volume get() = toVolume(VolumeUnit.Metric.Gigaliter)
public inline val Long.gigaliters: Volume get() = toVolume(VolumeUnit.Metric.Gigaliter)
public inline val Double.gigaliters: Volume get() = toVolume(VolumeUnit.Metric.Gigaliter)

public inline val Int.teraliters: Volume get() = toVolume(VolumeUnit.Metric.Teraliter)
public inline val Long.teraliters: Volume get() = toVolume(VolumeUnit.Metric.Teraliter)
public inline val Double.teraliters: Volume get() = toVolume(VolumeUnit.Metric.Teraliter)

public inline val Int.petaliters: Volume get() = toVolume(VolumeUnit.Metric.Petaliter)
public inline val Long.petaliters: Volume get() = toVolume(VolumeUnit.Metric.Petaliter)
public inline val Double.petaliters: Volume get() = toVolume(VolumeUnit.Metric.Petaliter)

public fun Int.toVolume(unit: VolumeUnit): Volume = toLong().toVolume(unit)

public fun Long.toVolume(unit: VolumeUnit): Volume {
    return Volume(saturated * unit.cubicCentimetersScale)
}

public fun Double.toVolume(unit: VolumeUnit): Volume {
    val valueInCubicCentis = this * unit.cubicCentimetersScale
    require(!valueInCubicCentis.isNaN()) { "Volume value cannot be NaN." }
    return Volume(valueInCubicCentis.roundToLong().saturated)
}

// endregion

public interface VolumeUnit {

    public val symbol: String

    public val cubicCentimetersScale: Long

    public enum class Metric(override val symbol: String, override val cubicCentimetersScale: Long) : VolumeUnit {
        Milliliter("${nbsp}mL", 1),
        Liter("${nbsp}L", 1_000),
        Kiloliter("${nbsp}kL", 1_000_000),
        Megaliter("${nbsp}ML", 1_000_000_000),
        Gigaliter("${nbsp}GL", 1_000_000_000_000),
        Teraliter("${nbsp}TL", 1_000_000_000_000_000),
        Petaliter("${nbsp}PL", 1_000_000_000_000_000_000),
    }
}