package io.github.kevincianfarini.alchemist.scalar

import io.github.kevincianfarini.alchemist.internal.SaturatingLong
import io.github.kevincianfarini.alchemist.internal.saturated
import io.github.kevincianfarini.alchemist.type.Acceleration

internal val Int.nmPerSecond2: Acceleration get() = toLong().nmPerSecond2
internal val Long.nmPerSecond2: Acceleration get() = Acceleration(saturated)
internal val SaturatingLong.nmPerSecond2: Acceleration get() = Acceleration(this)
