package io.github.kevincianfarini.alchemist

import kotlin.time.Duration
import kotlin.time.Duration.Companion.nanoseconds

internal fun Duration.isPreciseToNanosecond(): Boolean {
    return this == inWholeNanoseconds.nanoseconds
}

internal val Duration.sign: Int get() = when {
    isPositive() -> 1
    isNegative() -> -1
    else -> 0
}