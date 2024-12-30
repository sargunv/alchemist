package io.github.kevincianfarini.alchemist

import io.github.kevincianfarini.alchemist.internal.SaturatingLong
import io.github.kevincianfarini.alchemist.internal.saturated
import io.github.kevincianfarini.alchemist.internal.toDecimalString
import kotlin.jvm.JvmInline
import kotlin.math.pow
import kotlin.math.roundToLong

/**
 * Represents a measure of area and is capable of storing ±9.22 million kilometers² at millimeter² precision.
 */
@JvmInline
public value class Area internal constructor(private val rawMillimetersSquared: SaturatingLong) : Comparable<Area> {

    // region SI Arithmetic

    /**
     * Returns the resulting length after dividing this area by the specified [length].
     *
     * This operation attempts to retain precision, but for sufficiently large values of this area some precision
     * may be lost.
     *
     * @throws IllegalArgumentException if both this area and [length] are infinite.
     */
    public operator fun div(length: Length): Length {
        // Try to find the right level which we can perform this operation at without losing precision.
        // --------------------------------------------------------------------------------------------
        // 1 nanometer² / 1 nanometer is 1 nanometer
        // 1 micrometer² / 1 nanometer is 1,000,000 nanometers.
        // 1 millimeter² / 1 nanometer is 1,000,000,000,000 nanometers.
        // --------------------------------------------------------------------------------------------
        val nano2 = rawMillimetersSquared * 1_000_000_000_000
        if (nano2.isFinite()) return Length(nano2 / length.rawNanometers)
        val micro2 = rawMillimetersSquared * 1_000_000
        if (micro2.isFinite()) return Length((micro2 / length.rawNanometers) * 1_000_000)
        return Length((rawMillimetersSquared / length.rawNanometers) * 1_000_000_000_000)
    }

    /**
     * Returns the resulting [Volume] after applying this area over the specified [length].
     */
    public operator fun times(length: Length): Volume = TODO()

    // endregion

    // region Scalar Arithmetic

    /**
     * Returns an area whose value is this area value divided by the specified [scale].
     */
    public operator fun div(scale: Int): Area = div(scale.toLong())

    /**
     * Returns an area whose value is this area value divided by the specified [scale].
     */
    public operator fun div(scale: Long): Area = Area(rawMillimetersSquared / scale)

    /**
     * Returns the number that is the ratio of this and the [other] area value.
     */
    public operator fun div(other: Area): Double {
        return rawMillimetersSquared.toDouble() / other.rawMillimetersSquared.toDouble()
    }

    /**
     * Returns an area whose value is the difference between this and the [other] area value.
     */
    public operator fun minus(other: Area): Area = Area(rawMillimetersSquared - other.rawMillimetersSquared)

    /**
     * Returns an area whose value is the sum between this and the [other] area value.
     */
    public operator fun plus(other: Area): Area = Area(rawMillimetersSquared + other.rawMillimetersSquared)

    /**
     * Returns an area whose value is multiplied by the specified [scale].
     */
    public operator fun times(scale: Int): Area = times(scale.toLong())

    /**
     * Returns an area whose value is multiplied by the specified [scale].
     */
    public operator fun times(scale: Long): Area = Area(rawMillimetersSquared * scale)

    // endregion

    // region Area to Scalar Conversions

    public fun <T> toInternationalComponents(
        action: (
            megametersSquared: Long,
            kilometersSquared: Long,
            metersSquared: Long,
            centimetersSquared: Long,
            millimetersSquared: Long,
        ) -> T,
    ): T {
        val mega = rawMillimetersSquared / 1_000_000_000_000_000_000
        val megaRemainder = rawMillimetersSquared % 1_000_000_000_000_000_000
        val kilo = megaRemainder / 1_000_000_000_000
        val kiloRemainder = megaRemainder % 1_000_000_000_000
        val meters = kiloRemainder / 1_000_000
        val metersRemainder = kiloRemainder % 1_000_000
        val centi = metersRemainder / 100
        val milli = metersRemainder % 100
        return action(mega.rawValue, kilo.rawValue, meters.rawValue, centi.rawValue, milli.rawValue)
    }

    /**
     * Returns the value of this area expressed as a [Double] number of the specified [unit]. Infinite values are
     * converted to either [Double.POSITIVE_INFINITY] or [Double.NEGATIVE_INFINITY] depending on its sign.
     */
    public fun toDouble(unit: AreaUnit): Double {
        return this / unit.millimetersSquaredScale.mm2
    }

    public fun toDouble(squareUnit: LengthUnit): Double {
        val nm2 = rawMillimetersSquared.toDouble() * 1_000_000_000_000
        return nm2 / squareUnit.nanometerScale.toDouble().pow(2)
    }

    public fun toString(unit: AreaUnit, decimals: Int = 0): String = when (isInfinite()) {
        true -> rawMillimetersSquared.toString()
        false -> buildString {
            append(toDouble(unit).toDecimalString(decimals))
            append(unit.symbol)
        }
    }

    public fun toString(squareUnit: LengthUnit, decimals: Int = 0): String = when (isInfinite()) {
        true -> rawMillimetersSquared.toString()
        false -> buildString {
            append(toDouble(squareUnit).toDecimalString(decimals))
            append(squareUnit.symbol)
            append("²")
        }
    }

    public override fun toString(): String {
        val largestUnit = LengthUnit.International.entries.asReversed().firstOrNull { squareUnit ->
            toDouble(squareUnit) >= 1.0
        }
        return toString(largestUnit ?: LengthUnit.International.Millimeter, decimals = 2)
    }

    // endregion

    // region Comparisons

    public fun isInfinite(): Boolean = rawMillimetersSquared.isInfinite()

    public fun isFinite(): Boolean = rawMillimetersSquared.isFinite()

    public override fun compareTo(other: Area): Int {
        return rawMillimetersSquared.compareTo(other.rawMillimetersSquared)
    }

    // endregion
}

// region Scalar to Area Conversions

public inline val Int.decimilliares: Area get() = toArea(AreaUnit.Metric.Decimilliare)
public inline val Long.decimilliares: Area get() = toArea(AreaUnit.Metric.Decimilliare)
public inline val Double.decimilliares: Area get() = toArea(AreaUnit.Metric.Decimilliare)

public inline val Int.centiares: Area get() = toArea(AreaUnit.Metric.Centiare)
public inline val Long.centiares: Area get() = toArea(AreaUnit.Metric.Centiare)
public inline val Double.centiarea: Area get() = toArea(AreaUnit.Metric.Centiare)

public inline val Int.deciares: Area get() = toArea(AreaUnit.Metric.Deciare)
public inline val Long.deciares: Area get() = toArea(AreaUnit.Metric.Deciare)
public inline val Double.deciares: Area get() = toArea(AreaUnit.Metric.Deciare)

public inline val Int.ares: Area get() = toArea(AreaUnit.Metric.Are)
public inline val Long.ares: Area get() = toArea(AreaUnit.Metric.Are)
public inline val Double.ares: Area get() = toArea(AreaUnit.Metric.Are)

public inline val Int.decares: Area get() = toArea(AreaUnit.Metric.Decare)
public inline val Long.decares: Area get() = toArea(AreaUnit.Metric.Decare)
public inline val Double.decares: Area get() = toArea(AreaUnit.Metric.Decare)

public inline val Int.hectares: Area get() = toArea(AreaUnit.Metric.Hectare)
public inline val Long.hectares: Area get() = toArea(AreaUnit.Metric.Hectare)
public inline val Double.hectares: Area get() = toArea(AreaUnit.Metric.Hectare)

public fun Int.toArea(unit: AreaUnit): Area = toLong().toArea(unit)

public fun Long.toArea(unit: AreaUnit): Area = Area(saturated * unit.millimetersSquaredScale)

public fun Double.toArea(unit: AreaUnit): Area {
    val valueInMillimeters2 = this * unit.millimetersSquaredScale
    require(!valueInMillimeters2.isNaN()) { "Area value cannot be NaN." }
    return Area(valueInMillimeters2.roundToLong().saturated)
}

// endregion

public interface AreaUnit {

    /**
     * The amount of millimeters² in this unit. Implementations of [AreaUnit] should be perfectly divisible by a
     * quantity of millimeters².
     */
    public val millimetersSquaredScale: Long

    /**
     * The symbol of this unit.
     */
    public val symbol: String

    public enum class Metric(override val symbol: String, override val millimetersSquaredScale: Long) : AreaUnit {
        Decimilliare("dma", 100),
        Centiare("ca", 1_000_000),
        Deciare("da", 10_000_000),
        Are("a", 100_000_000),
        Decare("daa", 1_000_000_000),
        Hectare("ha", 10_000_000_000),
    }
}


internal inline val Long.mm2: Area get() = Area(saturated)
internal inline val Int.mm2: Area get() = Area(toLong().saturated)
internal inline val SaturatingLong.mm2: Area get() = Area(this)
