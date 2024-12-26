package io.github.kevincianfarini.alchemist

import kotlin.jvm.JvmInline
import kotlin.time.Duration

@JvmInline
public value class Velocity internal constructor(private val rawNanometersPerSecond: SaturatingLong) {

    /**
     * Returns the [Acceleration] necessary to achieve this velocity when applied over the specified [duration].
     */
    public operator fun div(duration: Duration): Acceleration = TODO()

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
     * Returns the [Length] traveled at this velocity for the specified [duration].
     */
    public operator fun times(duration: Duration): Length = TODO()

    /**
     * Returns a velocity whose value is multiplied by the specified [scale].
     */
    public operator fun times(scale: Int): Velocity = times(scale.toLong())

    /**
     * Returns a velocity whose value is multiplied by the specified [scale].
     */
    public operator fun times(scale: Long): Velocity = Velocity(rawNanometersPerSecond * scale)

    public fun isFinite(): Boolean = rawNanometersPerSecond.isFinite()

    public fun isInfinite(): Boolean = rawNanometersPerSecond.isInfinite()

    public companion object {
        public val POSITIVE_INFINITY: Velocity = Velocity(SaturatingLong.POSITIVE_INFINITY)
        public val NEGATIVE_INFINITY: Velocity = Velocity(SaturatingLong.NEGATIVE_INFINITY)
    }
}

internal val Int.nmPerSecond: Velocity get() = Velocity(toLong().saturated)
internal val Long.nmPerSecond: Velocity get() = Velocity(saturated)
internal val SaturatingLong.nmPerSecond: Velocity get() = Velocity(this)
