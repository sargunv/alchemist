package io.github.kevincianfarini.alchemist.scalar

import io.github.kevincianfarini.alchemist.internal.SaturatingLong
import io.github.kevincianfarini.alchemist.internal.saturated
import io.github.kevincianfarini.alchemist.type.Velocity

internal val Int.nmPerSecond: Velocity get() = Velocity(toLong().saturated)
internal val Long.nmPerSecond: Velocity get() = Velocity(saturated)
internal val SaturatingLong.nmPerSecond: Velocity get() = Velocity(this)
