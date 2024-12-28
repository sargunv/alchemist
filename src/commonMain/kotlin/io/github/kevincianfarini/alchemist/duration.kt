package io.github.kevincianfarini.alchemist

import kotlin.time.Duration
import kotlin.time.Duration.Companion.nanoseconds
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
