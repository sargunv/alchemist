package io.github.kevincianfarini.alchemist

import kotlin.time.Duration
import kotlin.time.Duration.Companion.microseconds
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

internal fun Duration.isPreciseToNanosecond(): Boolean {
    return this == inWholeNanoseconds.nanoseconds
}

internal val Duration.sign: Int get() = when {
    isPositive() -> 1
    isNegative() -> -1
    else -> 0
}

internal val DurationUnit.shortName: String get() = when (this) {
    DurationUnit.NANOSECONDS -> "ns"
    DurationUnit.MICROSECONDS -> "us"
    DurationUnit.MILLISECONDS -> "ms"
    DurationUnit.SECONDS -> "s"
    DurationUnit.MINUTES -> "m"
    DurationUnit.HOURS -> "h"
    DurationUnit.DAYS -> "d"
    else -> error("Unknown unit: $this")
}

internal val DurationUnit.shortNameSquared: String get() = when (this) {
    DurationUnit.NANOSECONDS -> "ns²"
    DurationUnit.MICROSECONDS -> "us²"
    DurationUnit.MILLISECONDS -> "ms²"
    DurationUnit.SECONDS -> "s²"
    DurationUnit.MINUTES -> "m²"
    DurationUnit.HOURS -> "h²"
    DurationUnit.DAYS -> "d²"
    else -> error("Unknown unit: $this")
}

internal val DurationUnit.secondScale: Double get() = when (this) {
    DurationUnit.NANOSECONDS -> 0.000_000_001
    DurationUnit.MICROSECONDS -> 0.000_001
    DurationUnit.MILLISECONDS -> 0.001
    DurationUnit.SECONDS -> 1.0
    DurationUnit.MINUTES -> 60.0
    DurationUnit.HOURS -> 3_600.0
    DurationUnit.DAYS -> 86_400.0
    else -> error("Unknown unit: $this")
}

internal fun Duration.toDecimalComponents(
    action: (
        kiloseconds: Long,
        seconds: Long,
        millis: Long,
        micros: Long,
        nanos: Long
    ) -> Energy,
): Energy {
    val seconds = inWholeSeconds
    val secondsRemainder = this - seconds.seconds
    val millis = secondsRemainder.inWholeMilliseconds
    val millisRemainder = secondsRemainder - millis.milliseconds
    val micros = millisRemainder.inWholeMicroseconds
    val nanos = (millisRemainder - micros.microseconds).inWholeNanoseconds
    return action(seconds / 1_000, seconds % 1_000, millis, micros, nanos)
}
