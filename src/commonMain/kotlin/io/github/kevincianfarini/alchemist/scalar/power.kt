package io.github.kevincianfarini.alchemist.scalar

import io.github.kevincianfarini.alchemist.internal.SaturatingLong
import io.github.kevincianfarini.alchemist.internal.saturated
import io.github.kevincianfarini.alchemist.type.Power
import io.github.kevincianfarini.alchemist.unit.PowerUnit
import kotlin.math.roundToLong

public inline val Int.terawatts: Power get() = toPower(PowerUnit.International.Terawatt)
public inline val Long.terawatts: Power get() = toPower(PowerUnit.International.Terawatt)
public inline val Double.terawatts: Power get() = toPower(PowerUnit.International.Terawatt)

public inline val Int.gigawatts: Power get() = toPower(PowerUnit.International.Gigawatt)
public inline val Long.gigawatts: Power get() = toPower(PowerUnit.International.Gigawatt)
public inline val Double.gigawatts: Power get() = toPower(PowerUnit.International.Gigawatt)

public inline val Int.megawatts: Power get() = toPower(PowerUnit.International.Megawatt)
public inline val Long.megawatts: Power get() = toPower(PowerUnit.International.Megawatt)
public inline val Double.megawatts: Power get() = toPower(PowerUnit.International.Megawatt)

public inline val Int.kilowatts: Power get() = toPower(PowerUnit.International.Kilowatt)
public inline val Long.kilowatts: Power get() = toPower(PowerUnit.International.Kilowatt)
public inline val Double.kilowatts: Power get() = toPower(PowerUnit.International.Kilowatt)

public inline val Int.watts: Power get() = toPower(PowerUnit.International.Watt)
public inline val Long.watts: Power get() = toPower(PowerUnit.International.Watt)
public inline val Double.watts: Power get() = toPower(PowerUnit.International.Watt)

public inline val Int.milliwatts: Power get() = toPower(PowerUnit.International.Milliwatt)
public inline val Long.milliwatts: Power get() = toPower(PowerUnit.International.Milliwatt)
public inline val Double.milliwatts: Power get() = toPower(PowerUnit.International.Milliwatt)

public inline val Int.microwatts: Power get() = toPower(PowerUnit.International.Microwatt)
public inline val Long.microwatts: Power get() = toPower(PowerUnit.International.Microwatt)

internal inline val SaturatingLong.megawatts get() = rawValue.toPower(PowerUnit.International.Megawatt)
internal inline val SaturatingLong.kilowatts get() = rawValue.toPower(PowerUnit.International.Kilowatt)
internal inline val SaturatingLong.watts get() = rawValue.toPower(PowerUnit.International.Watt)
internal inline val SaturatingLong.milliwatts get() = rawValue.toPower(PowerUnit.International.Milliwatt)
internal inline val SaturatingLong.microwatts get() = rawValue.toPower(PowerUnit.International.Microwatt)

public fun Int.toPower(unit: PowerUnit): Power {
    return toLong().toPower(unit)
}

public fun Long.toPower(unit: PowerUnit): Power {
    return Power(saturated * unit.microwattScale)
}

public fun Double.toPower(unit: PowerUnit): Power {
    val valueInMicrowatts = this * unit.microwattScale
    require(!valueInMicrowatts.isNaN()) { "Mass value cannot be NaN." }
    return Power(valueInMicrowatts.roundToLong().saturated)
}
