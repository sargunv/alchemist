package io.github.kevincianfarini.alchemist.type

import kotlin.time.Duration


/**
 * Returns the resulting [Velocity] after multiplying this duration by the specified [acceleration].
 *
 * This operation attempts to retain precision, but for sufficiently large values of this duration or the
 * specified [acceleration] some precision may be lost.
 *
 * @throws IllegalArgumentException if this duration is infinite and [acceleration] is zero, or if this duration
 * is zero and [acceleration] is infinite.
 */
public operator fun Duration.times(acceleration: Acceleration): Velocity = acceleration * this

/**
 * Returns the resulting [Energy] from applying the specified [power] over this duration.
 *
 * This operation attempts to retain precision, but for sufficiently large values of either this duration or [power],
 * some precision may be lost.
 *
 * @throws IllegalArgumentException if this duration is infinite and [power] is zero, or if this duration is zero and
 * [power] is infinite.
 */
public operator fun Duration.times(power: Power): Energy = power * this

/**
 * Returns the resulting [Length] from applying the specified [velocity] over this duration.
 *
 * This operation attempts to retain precision, but for sufficiently large values of either this duration or [velocity],
 * some precision may be lost.
 *
 * @throws IllegalArgumentException if this duration is infinite and [velocity] is zero, or if this duration is zero and
 * [velocity] is infinite.
 */
public operator fun Duration.times(velocity: Velocity): Length = velocity * this
