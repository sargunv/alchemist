package io.github.kevincianfarini.alchemist

import io.github.kevincianfarini.alchemist.internal.SaturatingLong
import io.github.kevincianfarini.alchemist.internal.isPreciseToNanosecond
import io.github.kevincianfarini.alchemist.internal.saturated
import io.github.kevincianfarini.alchemist.internal.secondScale
import io.github.kevincianfarini.alchemist.internal.shortName
import io.github.kevincianfarini.alchemist.internal.sign
import io.github.kevincianfarini.alchemist.internal.toDecimalString
import kotlin.jvm.JvmInline
import kotlin.text.Typography.nbsp
import kotlin.time.Duration
import kotlin.time.DurationUnit

@JvmInline
public value class Velocity internal constructor(
    internal val rawNanometersPerSecond: SaturatingLong
) : Comparable<Velocity> {

    // region SI Arithmetic

    /**
     * Returns the constant [Acceleration] required achieve this velocity in the specified [duration].
     *
     * This operation attempts to retain precision, but for sufficiently large values of either this velocity or the
     * other [duration], some precision may be lost.
     *
     * @throws IllegalArgumentException if both this velocity and [duration] are infinite.
     */
    public operator fun div(duration: Duration): Acceleration = when {
        isInfinite() && duration.isInfinite() -> {
            throw IllegalArgumentException("Dividing two infinite values yields an undefined result.")
        }
        isInfinite() -> Acceleration(rawNanometersPerSecond * duration.sign)
        duration.isInfinite() -> Acceleration(0L.saturated)
        else -> calculateAcceleration(duration)
    }

    private fun calculateAcceleration(duration: Duration): Acceleration {
        // Try to find the right level which we can perform this operation at without losing precision.
        if (duration.isPreciseToNanosecond()) {
            val acceleration = nanosPerNs2(rawNanometersPerSecond, duration.inWholeNanoseconds)
            if (acceleration.isFinite()) return acceleration
        }
        val ms = duration.inWholeMilliseconds
        val acceleration = nanosPerMs2(rawNanometersPerSecond, ms)
        if (acceleration.isFinite()) return acceleration
        return Acceleration((rawNanometersPerSecond / ms) * 1_000)
    }

    private fun nanosPerNs2(nanosPerSecond: SaturatingLong, ns: Long): Acceleration {
        val nanos = nanosPerSecond / ns
        val picoRemainder = (nanosPerSecond % ns) * 1_000
        return (nanos * 1_000_000_000).nmPerSecond2 + picosPerNs2(picoRemainder, ns)
    }

    private fun picosPerNs2(picosPerSecond: SaturatingLong, ns: Long): Acceleration {
        val picos = picosPerSecond / ns
        val femtoRemainder = (picosPerSecond % ns) * 1_000
        return (picos * 1_000_000).nmPerSecond2 + femtosPerNs2(femtoRemainder, ns)
    }

    private fun femtosPerNs2(femtosPerSecond: SaturatingLong, ns: Long): Acceleration {
        val femtos = femtosPerSecond / ns
        val attoRemainder = (femtosPerSecond % ns) * 1_000
        return (femtos * 1_000).nmPerSecond2 + attosPerNs2(attoRemainder, ns)
    }

    private fun attosPerNs2(attosPerSecond: SaturatingLong, ns: Long): Acceleration {
        return Acceleration(attosPerSecond / ns)
    }

    private fun nanosPerMs2(nanosPerSecond: SaturatingLong, ms: Long): Acceleration {
        val nanos = nanosPerSecond / ms
        val picoRemainder = (nanosPerSecond % ms) * 1_000
        return (nanos * 1_000).nmPerSecond2 + picosPerMs2(picoRemainder, ms)
    }

    private fun picosPerMs2(picosPerSecond: SaturatingLong, ms: Long): Acceleration {
        return Acceleration(picosPerSecond / ms)
    }

    /**
     * Returns the [Length] traveled at this velocity for the specified [duration].
     */
    public operator fun times(duration: Duration): Length = duration.toComponents { seconds, nanoseconds ->
        val secondComponent = (rawNanometersPerSecond * seconds).nanometers
        val preciseNanosecondComponent = ((rawNanometersPerSecond * nanoseconds) / 1_000_000_000).nanometers
        if (preciseNanosecondComponent.isFinite()) {
            secondComponent + preciseNanosecondComponent
        } else {
            val coarseNanosecondComponent = ((rawNanometersPerSecond / 1_000_000_000) * nanoseconds).nanometers
            secondComponent + coarseNanosecondComponent
        }
    }

    // endregion

    // region Scalar Arithmetic

    /**
     * Returns the number that is the ratio of this and the [other] velocity value.
     */
    public operator fun div(other: Velocity): Double {
        return rawNanometersPerSecond.toDouble() / other.rawNanometersPerSecond.toDouble()
    }

    /**
     * Returns a velocity whose value is this velocity value divided by the specified [scale].
     */
    public operator fun div(scale: Int): Velocity = div(scale.toLong())

    /**
     * Returns a velocity whose value is this velocity value divided by the specified [scale].
     */
    public operator fun div(scale: Long): Velocity = Velocity(rawNanometersPerSecond / scale)

    /**
     * Returns a velocity whose value is the difference between this and the [other] velocity value.
     */
    public operator fun minus(other: Velocity): Velocity {
        return Velocity(rawNanometersPerSecond - other.rawNanometersPerSecond)
    }

    /**
     * Returns a velocity whose value is the sum between this and the [other] velocity value.
     */
    public operator fun plus(other: Velocity): Velocity {
        return Velocity(rawNanometersPerSecond + other.rawNanometersPerSecond)
    }

    /**
     * Returns a velocity whose value is multiplied by the specified [scale].
     */
    public operator fun times(scale: Int): Velocity = times(scale.toLong())

    /**
     * Returns a velocity whose value is multiplied by the specified [scale].
     */
    public operator fun times(scale: Long): Velocity = Velocity(rawNanometersPerSecond * scale)

    // endregion

    // region Velocity to Scalar Conversions

    /**
     * Returns the value of this velocity expressed as a [Double] number of the specific [lengthUnit] per
     * [durationUnit]. Infinite values are converted to either [Double.POSITIVE_INFINITY] or [Double.NEGATIVE_INFINITY]
     * depending on its sign.
     */
    public fun toDouble(lengthUnit: LengthUnit, durationUnit: DurationUnit = DurationUnit.SECONDS): Double {
        return (rawNanometersPerSecond.toDouble() / lengthUnit.nanometerScale.toDouble()) * durationUnit.secondScale
    }

    /**
     * Returns a fractional string representation of this velocity expressed in the specified [lengthUnit] per
     * [durationUnit].
     */
    public fun toString(
        lengthUnit: LengthUnit,
        durationUnit: DurationUnit = DurationUnit.SECONDS,
        decimals: Int = 0,
    ): String {
        return when (isInfinite()) {
            true -> rawNanometersPerSecond.toString()
            false -> buildString {
                append(toDouble(lengthUnit, durationUnit).toDecimalString(decimals))
                append(nbsp)
                append(lengthUnit.symbol)
                append("/")
                append(durationUnit.shortName)
            }
        }
    }

    override fun toString(): String {
        val lengthUnit = LengthUnit.International.entries.asReversed().firstOrNull { unit ->
            rawNanometersPerSecond.absoluteValue / unit.nanometerScale > 0
        }
        return toString(lengthUnit ?: LengthUnit.International.Nanometer, DurationUnit.SECONDS, decimals = 2)
    }

    // endregion

    // region Comparisons

    public fun isFinite(): Boolean = rawNanometersPerSecond.isFinite()

    public fun isInfinite(): Boolean = rawNanometersPerSecond.isInfinite()

    override fun compareTo(other: Velocity): Int = rawNanometersPerSecond.compareTo(other.rawNanometersPerSecond)

    // endregion

    public companion object {
        public val POSITIVE_INFINITY: Velocity = Velocity(SaturatingLong.POSITIVE_INFINITY)
        public val NEGATIVE_INFINITY: Velocity = Velocity(SaturatingLong.NEGATIVE_INFINITY)
    }
}

internal val Int.nmPerSecond: Velocity get() = Velocity(toLong().saturated)
internal val Long.nmPerSecond: Velocity get() = Velocity(saturated)
internal val SaturatingLong.nmPerSecond: Velocity get() = Velocity(this)
