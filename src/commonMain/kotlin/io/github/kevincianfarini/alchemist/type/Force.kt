package io.github.kevincianfarini.alchemist.type

import io.github.kevincianfarini.alchemist.internal.SaturatingLong
import io.github.kevincianfarini.alchemist.internal.toDecimalString
import io.github.kevincianfarini.alchemist.unit.ForceUnit
import kotlin.jvm.JvmInline

@JvmInline
public value class Force internal constructor(private val rawNanonewtons: SaturatingLong) : Comparable<Force> {

    // region SI Arithmetic

    /**
     * Returns the resulting [Acceleration] from applying this force to the specified [mass].
     */
    public operator fun div(mass: Mass): Acceleration = TODO()

    /**
     * Returns the amount of [Energy] required to apply this force over the specified [length].
     *
     * This operation attempts to retain precision, but for sufficiently large values of either this force or the
     * specified [length], some precision may be lost.
     *
     * @throws IllegalArgumentException when [length] is [infinite][isInfinite] and this force is 0 or vice versa.
     */
    public operator fun times(length: Length): Energy {
        return length.toInternationalComponents { giga, mega, kilo, meters, centi, milli, micro, nano ->
            // Try to find the right level which we can perform this operation at without losing precision.
            // --------------------------------------------------------------------------------------------
            // 1 nN * 1 nm is 1 attojoule.
            // 1 nN * 1 μm is 1 femtojoule.
            // 1 nN * 1 mm is 1 picojoule.
            // 1 nN * 1 cm is 10 picojoules.
            // 1 nN * 1 m is 1 nanojoule.
            // 1 nN * 1 km is 1 microjoule.
            // 1 nN * 1 Mm is 1 millijoule.
            // 1 nN * 1 Gm is 1 joule.
            // --------------------------------------------------------------------------------------------
            val joules = rawNanonewtons * giga
            val millijoules = rawNanonewtons * mega
            val microjoules = rawNanonewtons * kilo
            val nanojoules = rawNanonewtons * meters
            val picojoules = (rawNanonewtons * centi * 10) + (rawNanonewtons * milli)
            val femtojoules = rawNanonewtons * micro
            val attojoules = rawNanonewtons * nano
            // ----------- Try attojoule precision. ------------------------------------------------------
            val attoJ = attojoules + (femtojoules * 1_000) + (picojoules * 1_000_000) + (nanojoules * 1_000_000_000) + (microjoules * 1_000_000_000_000) + (millijoules * 1_000_000_000_000_000) + (joules * 1_000_000_000_000_000_000)
            if (attoJ.isFinite()) return@toInternationalComponents Energy(attoJ / 1_000_000_000_000_000)
            // ----------- Try femtojoule precision. ------------------------------------------------------
            val femtoJ = (attojoules / 1_000) + femtojoules + (picojoules * 1_000) + (nanojoules * 1_000_000) + (microjoules * 1_000_000_000) + (millijoules * 1_000_000_000_000) + (joules * 1_000_000_000_000_000)
            if (femtoJ.isFinite()) return@toInternationalComponents Energy(femtoJ / 1_000_000_000_000)
            // ----------- Try picojoule precision. ------------------------------------------------------
            val picoJ = (attojoules / 1_000_000) + (femtojoules / 1_000) + picojoules + (nanojoules * 1_000) + (microjoules * 1_000_000) + (millijoules * 1_000_000_000) + (joules * 1_000_000_000_000)
            if (picoJ.isFinite()) return@toInternationalComponents Energy(picoJ / 1_000_000_000)
            // ----------- Try nanojoule precision. ------------------------------------------------------
            val nanoJ = (attojoules / 1_000_000_000) + (femtojoules / 1_000_000) + (picojoules / 1_000) + nanojoules + (microjoules * 1_000) + (millijoules * 1_000_000) + (joules * 1_000_000_000)
            if (nanoJ.isFinite()) return@toInternationalComponents Energy(nanoJ / 1_000_000)
            // ----------- Try microjoule precision. ------------------------------------------------------
            val microJ = (attojoules / 1_000_000_000_000) + (femtojoules / 1_000_000_000) + (picojoules / 1_000_000) + (nanojoules / 1_000) + microjoules + (millijoules * 1_000) + (joules * 1_000_000)
            if (microJ.isFinite()) return@toInternationalComponents Energy(microJ / 1_000)
            // ----------- Default millijoule precision. ------------------------------------------------------
            val milliJ = (attojoules / 1_000_000_000_000_000) + (femtojoules / 1_000_000_000_000) + (picojoules / 1_000_000_000) + (nanojoules / 1_000_000) + (microjoules / 1_000) + millijoules + (joules * 1_000)
            Energy(milliJ)
        }
    }

    // endregion

    // region Scalar Arithmetic

    /**
     * Returns the number that is the ratio of this and the [other] force value.
     */
    public operator fun div(other: Force): Double = rawNanonewtons.toDouble() / other.rawNanonewtons.toDouble()

    /**
     * Returns a force whose value is this force value divided by the specified [scale].
     */
    public operator fun div(scale: Int): Force = div(scale.toLong())

    /**
     * Returns a force whose value is this force value divided by the specified [scale].
     */
    public operator fun div(scale: Long): Force = Force(rawNanonewtons / scale)

    /**
     * Returns a force whose value is the difference between this and the [other] force value.
     */
    public operator fun minus(other: Force): Force = Force(rawNanonewtons - other.rawNanonewtons)

    /**
     * Returns a force whose value is the sum between this and the [other] force value.
     */
    public operator fun plus(other: Force): Force = Force(rawNanonewtons + other.rawNanonewtons)

    /**
     * Returns a force whose value is multiplied by the specified [scale].
     */
    public operator fun times(scale: Int): Force = times(scale.toLong())

    /**
     * Returns a force whose value is multiplied by the specified [scale].
     */
    public operator fun times(scale: Long): Force = Force(rawNanonewtons * scale)

    // endregion

    // region Force to Scalar Conversions

    /**
     * Returns the value of this force expressed as a [Long] number of the specified [unit]. Infinite values are
     * converted to either [Long.MAX_VALUE] or [Long.MIN_VALUE] depending on its sign.
     */
    public fun toLong(unit: ForceUnit): Long {
        return (rawNanonewtons / unit.nanonewtonScale).rawValue
    }

    public fun toDouble(unit: ForceUnit): Double {
        return rawNanonewtons.toDouble() / unit.nanonewtonScale
    }

    public fun toString(unit: ForceUnit, decimals: Int = 0): String = when {
        isInfinite() -> rawNanonewtons.toString()
        else -> buildString {
            append(toDouble(unit).toDecimalString(decimals))
            append(unit.symbol)
        }
    }

    public override fun toString(): String {
        val largestUnit = ForceUnit.International.entries.asReversed().firstOrNull { unit ->
            rawNanonewtons / unit.nanonewtonScale > 0
        }
        return toString(largestUnit ?: ForceUnit.International.Nanonewton, decimals = 2)
    }

    // endregion

    // region Comparisons

    public fun isInfinite(): Boolean = rawNanonewtons.isInfinite()

    public fun isFinite(): Boolean = rawNanonewtons.isFinite()

    override fun compareTo(other: Force): Int = rawNanonewtons.compareTo(other.rawNanonewtons)

    // endregion
}
