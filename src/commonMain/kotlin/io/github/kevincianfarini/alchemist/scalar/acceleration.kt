package io.github.kevincianfarini.alchemist.scalar

import io.github.kevincianfarini.alchemist.internal.SaturatingLong
import io.github.kevincianfarini.alchemist.internal.saturated
import io.github.kevincianfarini.alchemist.type.Acceleration
import io.github.kevincianfarini.alchemist.type.Velocity
import kotlin.time.Duration

/**
 * Returns the resulting [Velocity] after multiplying the specified [acceleration] by this duration.
 *
 * This operation attempts to retain precision, but for sufficiently large values of this duration or the
 * specified [acceleration] some precision may be lost.
 *
 * @throws IllegalArgumentException if this duration is infinite and [acceleration] is zero, or if this duration
 * is zero and [acceleration] is infinite.
 */
public fun Duration.times(acceleration: Acceleration): Velocity = acceleration * this

internal val Int.nmPerSecond2: Acceleration get() = toLong().nmPerSecond2
internal val Long.nmPerSecond2: Acceleration get() = Acceleration(saturated)
internal val SaturatingLong.nmPerSecond2: Acceleration get() = Acceleration(this)
