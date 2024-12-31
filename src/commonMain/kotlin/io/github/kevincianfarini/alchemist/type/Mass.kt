package io.github.kevincianfarini.alchemist.type

import io.github.kevincianfarini.alchemist.internal.SaturatingLong
import io.github.kevincianfarini.alchemist.internal.toDecimalString
import io.github.kevincianfarini.alchemist.unit.MassUnit
import kotlin.jvm.JvmInline
import kotlin.text.Typography.nbsp

@JvmInline
public value class Mass internal constructor(private val rawMicrograms: SaturatingLong) : Comparable<Mass> {

    // region SI Arithmetic

    /**
     * Returns the [Force] required to apply to this mass to achieve the specified [acceleration].
     */
    public operator fun times(acceleration: Acceleration): Force = acceleration * this

    // endregion

    // region Scalar Arithmetic

    /**
     * Returns a mass whose value is this mass value divided by the specified [scale].
     */
    public operator fun div(scale: Int): Mass = div(scale.toLong())

    /**
     * Returns a mass whose value is this mass value divided by the specified [scale].
     */
    public operator fun div(scale: Long): Mass = Mass(rawMicrograms / scale)

    /**
     * Returns the number that is the ratio of this and the [other] mass value.
     */
    public operator fun div(other: Mass): Double = rawMicrograms.toDouble() / other.rawMicrograms.toDouble()

    /**
     * Returns a mass whose value is the difference between this and the [other] mass value.
     */
    public operator fun minus(other: Mass): Mass = Mass(rawMicrograms - other.rawMicrograms)

    /**
     * Returns a mass whose value is the sum between this and the [other] mass value.
     */
    public operator fun plus(other: Mass): Mass = Mass(rawMicrograms + other.rawMicrograms)

    /**
     * Returns a mass whose value is multiplied by the specified [scale].
     */
    public operator fun times(scale: Int): Mass = times(scale.toLong())

    /**
     * Returns a mass whose value is multiplied by the specified [scale].
     */
    public operator fun times(scale: Long): Mass = Mass(rawMicrograms * scale)

    // endregion

    // region Mass to Scalar Conversions

    public fun <T> toInternationalComponents(
        action: (
            teragrams: Long,
            gigagrams: Long,
            megagrams: Long,
            kilograms: Long,
            grams: Long,
            milligrams: Long,
            micrograms: Long,
        ) -> T
    ): T {
        val tera = rawMicrograms / MassUnit.International.Teragram.microgramScale
        val teraRemainder = rawMicrograms % MassUnit.International.Teragram.microgramScale
        val giga = teraRemainder / MassUnit.International.Gigagram.microgramScale
        val gigaRemainder = teraRemainder % MassUnit.International.Gigagram.microgramScale
        val mega = gigaRemainder / MassUnit.International.Megagram.microgramScale
        val megaRemainder = gigaRemainder % MassUnit.International.Megagram.microgramScale
        val kilo = megaRemainder / MassUnit.International.Kilogram.microgramScale
        val kiloRemainder = megaRemainder % MassUnit.International.Kilogram.microgramScale
        val grams = kiloRemainder / MassUnit.International.Gram.microgramScale
        val gramRemainder = kiloRemainder % MassUnit.International.Gram.microgramScale
        val milli = gramRemainder / MassUnit.International.Milligram.microgramScale
        val micro = gramRemainder % MassUnit.International.Megagram.microgramScale
        return action(
            tera.rawValue,
            giga.rawValue,
            mega.rawValue,
            kilo.rawValue,
            grams.rawValue,
            milli.rawValue,
            micro.rawValue
        )
    }

    public fun toDouble(unit: MassUnit): Double {
        return rawMicrograms.toDouble() / unit.microgramScale.toDouble()
    }

    public fun toString(unit: MassUnit, decimals: Int = 0): String = when (isInfinite()) {
        true -> rawMicrograms.toString()
        false -> buildString {
            append(toDouble(unit).toDecimalString(decimals))
            append(nbsp)
            append(unit.symbol)
        }
    }

    override fun toString(): String {
        val largestUnit = MassUnit.International.entries.asReversed().firstOrNull { unit ->
            rawMicrograms.absoluteValue / unit.microgramScale > 0
        }
        return toString(largestUnit ?: MassUnit.International.Microgram, decimals = 2)
    }

    // endregion

    // region Comparisons

    public fun isInfinite(): Boolean = rawMicrograms.isInfinite()

    public fun isFinite(): Boolean = rawMicrograms.isFinite()

    override fun compareTo(other: Mass): Int = rawMicrograms.compareTo(other.rawMicrograms)

    // endregion
}

