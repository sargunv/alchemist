package io.github.kevincianfarini.alchemist.scalar

import io.github.kevincianfarini.alchemist.internal.SaturatingLong
import io.github.kevincianfarini.alchemist.internal.saturated
import io.github.kevincianfarini.alchemist.type.Power
import io.github.kevincianfarini.alchemist.unit.PowerUnit
import kotlin.math.roundToLong

/**
 * Returns a [Power] equal to [Int] number of terawatts.
 */
public inline val Int.terawatts: Power get() = toPower(PowerUnit.International.Terawatt)

/**
 * Returns a [Power] equal to [Long] number of terawatts.
 */
public inline val Long.terawatts: Power get() = toPower(PowerUnit.International.Terawatt)

/**
 * Returns a [Power] equal to [Double] number of terawatts. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.terawatts: Power get() = toPower(PowerUnit.International.Terawatt)

/**
 * Returns a [Power] equal to [Int] number of gigawatts.
 */
public inline val Int.gigawatts: Power get() = toPower(PowerUnit.International.Gigawatt)

/**
 * Returns a [Power] equal to [Long] number of gigawatts.
 */
public inline val Long.gigawatts: Power get() = toPower(PowerUnit.International.Gigawatt)

/**
 * Returns a [Power] equal to [Double] number of gigawatts. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.gigawatts: Power get() = toPower(PowerUnit.International.Gigawatt)

/**
 * Returns a [Power] equal to [Int] number of megawatts.
 */
public inline val Int.megawatts: Power get() = toPower(PowerUnit.International.Megawatt)

/**
 * Returns a [Power] equal to [Long] number of megawatts.
 */
public inline val Long.megawatts: Power get() = toPower(PowerUnit.International.Megawatt)

/**
 * Returns a [Power] equal to [Double] number of megawatts. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.megawatts: Power get() = toPower(PowerUnit.International.Megawatt)

/**
 * Returns a [Power] equal to [Int] number of kilowatts.
 */
public inline val Int.kilowatts: Power get() = toPower(PowerUnit.International.Kilowatt)

/**
 * Returns a [Power] equal to [Long] number of kilowatts.
 */
public inline val Long.kilowatts: Power get() = toPower(PowerUnit.International.Kilowatt)

/**
 * Returns a [Power] equal to [Double] number of kilowatts. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.kilowatts: Power get() = toPower(PowerUnit.International.Kilowatt)

/**
 * Returns a [Power] equal to [Int] number of watts.
 */
public inline val Int.watts: Power get() = toPower(PowerUnit.International.Watt)

/**
 * Returns a [Power] equal to [Long] number of watts.
 */
public inline val Long.watts: Power get() = toPower(PowerUnit.International.Watt)

/**
 * Returns a [Power] equal to [Double] number of watts. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.watts: Power get() = toPower(PowerUnit.International.Watt)

/**
 * Returns a [Power] equal to [Int] number of milliwatts.
 */
public inline val Int.milliwatts: Power get() = toPower(PowerUnit.International.Milliwatt)

/**
 * Returns a [Power] equal to [Long] number of milliwatts.
 */
public inline val Long.milliwatts: Power get() = toPower(PowerUnit.International.Milliwatt)

/**
 * Returns a [Power] equal to [Double] number of milliwatts. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.milliwatts: Power get() = toPower(PowerUnit.International.Milliwatt)

/**
 * Returns a [Power] equal to [Int] number of microwatts.
 */
public inline val Int.microwatts: Power get() = toPower(PowerUnit.International.Microwatt)

/**
 * Returns a [Power] equal to [Long] number of microwatts.
 */
public inline val Long.microwatts: Power get() = toPower(PowerUnit.International.Microwatt)

internal inline val SaturatingLong.megawatts get() = rawValue.toPower(PowerUnit.International.Megawatt)
internal inline val SaturatingLong.kilowatts get() = rawValue.toPower(PowerUnit.International.Kilowatt)
internal inline val SaturatingLong.watts get() = rawValue.toPower(PowerUnit.International.Watt)
internal inline val SaturatingLong.milliwatts get() = rawValue.toPower(PowerUnit.International.Milliwatt)
internal inline val SaturatingLong.microwatts get() = rawValue.toPower(PowerUnit.International.Microwatt)

/**
 * Returns a [Power] equal to [Int] number of the specified [unit].
 */
public fun Int.toPower(unit: PowerUnit): Power {
    return toLong().toPower(unit)
}

/**
 * Returns a [Power] equal to [Long] number of the specified [unit].
 */
public fun Long.toPower(unit: PowerUnit): Power {
    return Power(saturated * unit.microwattScale)
}

/**
 * Returns a [Power] equal to [Double] number of the specified [unit]. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public fun Double.toPower(unit: PowerUnit): Power {
    val valueInMicrowatts = this * unit.microwattScale
    require(!valueInMicrowatts.isNaN()) { "Mass value cannot be NaN." }
    return Power(valueInMicrowatts.roundToLong().saturated)
}
