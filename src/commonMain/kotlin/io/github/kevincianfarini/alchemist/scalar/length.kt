package io.github.kevincianfarini.alchemist.scalar

import io.github.kevincianfarini.alchemist.internal.SaturatingLong
import io.github.kevincianfarini.alchemist.internal.saturated
import io.github.kevincianfarini.alchemist.type.Length
import io.github.kevincianfarini.alchemist.unit.LengthUnit
import kotlin.math.roundToLong


/**
 * Returns a [Length] equal to [Int] number of nanometers.
 */
public inline val Int.nanometers: Length get() = toLength(LengthUnit.International.Nanometer)

/**
 * Returns a [Length] equal to [Long] number of nanometers.
 */
public inline val Long.nanometers: Length get() = toLength(LengthUnit.International.Nanometer)

internal inline val SaturatingLong.nanometers: Length get() = Length(this)

/**
 * Returns a [Length] equal to [Int] number of micrometers.
 */
public inline val Int.micrometers: Length get() = toLength(LengthUnit.International.Micrometer)

/**
 * Returns a [Length] equal to [Long] number of micrometers.
 */
public inline val Long.micrometers: Length get() = toLength(LengthUnit.International.Micrometer)

/**
 * Returns a [Length] equal to [Double] number of micrometers. Depending on its magnitude, some precision may be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.micrometers: Length get() = toLength(LengthUnit.International.Micrometer)

/**
 * Returns a [Length] equal to [Int] number of millimeters.
 */
public inline val Int.millimeters: Length get() = toLength(LengthUnit.International.Millimeter)

/**
 * Returns a [Length] equal to [Long] number of millimeters.
 */
public inline val Long.millimeters: Length get() = toLength(LengthUnit.International.Millimeter)

/**
 * Returns a [Length] equal to [Double] number of millimeters. Depending on its magnitude, some precision may be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.millimeters: Length get() = toLength(LengthUnit.International.Millimeter)

/**
 * Returns a [Length] equal to [Int] number of centimeters.
 */
public inline val Int.centimeters: Length get() = toLength(LengthUnit.International.Centimeter)

/**
 * Returns a [Length] equal to [Long] number of centimeters.
 */
public inline val Long.centimeters: Length get() = toLength(LengthUnit.International.Centimeter)

/**
 * Returns a [Length] equal to [Double] number of centimeters. Depending on its magnitude, some precision may be lost.
 */
public inline val Double.centimeters: Length get() = toLength(LengthUnit.International.Centimeter)

/**
 * Returns a [Length] equal to [Int] number of meters.
 */
public inline val Int.meters: Length get() = toLength(LengthUnit.International.Meter)

/**
 * Returns a [Length] equal to [Long] number of meters.
 */
public inline val Long.meters: Length get() = toLength(LengthUnit.International.Meter)

/**
 * Returns a [Length] equal to [Double] number of meters. Depending on its magnitude, some precision may be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.meters: Length get() = toLength(LengthUnit.International.Meter)

/**
 * Returns a [Length] equal to [Int] number of kilometers.
 */
public inline val Int.kilometers: Length get() = toLength(LengthUnit.International.Kilometer)

/**
 * Returns a [Length] equal to [Long] number of kilometers.
 */
public inline val Long.kilometers: Length get() = toLength(LengthUnit.International.Kilometer)

/**
 * Returns a [Length] equal to [Double] number of kilometers. Depending on its magnitude, some precision may be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.kilometers: Length get() = toLength(LengthUnit.International.Kilometer)

/**
 * Returns a [Length] equal to [Int] number of megameters.
 */
public inline val Int.megameters: Length get() = toLength(LengthUnit.International.Megameter)

/**
 * Returns a [Length] equal to [Long] number of megameters.
 */
public inline val Long.megameters: Length get() = toLength(LengthUnit.International.Megameter)

/**
 * Returns a [Length] equal to [Double] number of megameters. Depending on its magnitude, some precision may be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.megameters: Length get() = toLength(LengthUnit.International.Megameter)

/**
 * Returns a [Length] equal to [Int] number of gigameters.
 */
public inline val Int.gigameters: Length get() = toLength(LengthUnit.International.Gigameter)

/**
 * Returns a [Length] equal to [Long] number of gigameters.
 */
public inline val Long.gigameters: Length get() = toLength(LengthUnit.International.Gigameter)

/**
 * Returns a [Length] equal to [Double] number of gigameters. Depending on its magnitude, some precision may be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.gigameters: Length get() = toLength(LengthUnit.International.Gigameter)

/**
 * Returns a [Length] equal to [Int] number of inches.
 */
public inline val Int.inches: Length get() = toLength(LengthUnit.UnitedStatesCustomary.Inch)

/**
 * Returns a [Length] equal to [Long] number of inches.
 */
public inline val Long.inches: Length get() = toLength(LengthUnit.UnitedStatesCustomary.Inch)

/**
 * Returns a [Length] equal to [Double] number of inches. Depending on its magnitude, some precision may be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.inches: Length get() = toLength(LengthUnit.UnitedStatesCustomary.Inch)

/**
 * Returns a [Length] equal to [Int] number of feet.
 */
public inline val Int.feet: Length get() = toLength(LengthUnit.UnitedStatesCustomary.Foot)

/**
 * Returns a [Length] equal to [Long] number of feet.
 */
public inline val Long.feet: Length get() = toLength(LengthUnit.UnitedStatesCustomary.Foot)

/**
 * Returns a [Length] equal to [Double] number of feet. Depending on its magnitude, some precision may be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.feet: Length get() = toLength(LengthUnit.UnitedStatesCustomary.Foot)

/**
 * Returns a [Length] equal to [Int] number of yards.
 */
public inline val Int.yards: Length get() = toLength(LengthUnit.UnitedStatesCustomary.Yard)

/**
 * Returns a [Length] equal to [Long] number of yards.
 */
public inline val Long.yards: Length get() = toLength(LengthUnit.UnitedStatesCustomary.Yard)

/**
 * Returns a [Length] equal to [Double] number of yards. Depending on its magnitude, some precision may be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.yards: Length get() = toLength(LengthUnit.UnitedStatesCustomary.Yard)

/**
 * Returns a [Length] equal to [Int] number of miles.
 */
public inline val Int.miles: Length get() = toLength(LengthUnit.UnitedStatesCustomary.Mile)

/**
 * Returns a [Length] equal to [Long] number of miles.
 */
public inline val Long.miles: Length get() = toLength(LengthUnit.UnitedStatesCustomary.Mile)

/**
 * Returns a [Length] equal to [Double] number of miles. Depending on its magnitude, some precision may be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.miles: Length get() = toLength(LengthUnit.UnitedStatesCustomary.Mile)

/**
 * Returns a [Length] equal to [Int] number of the specified [unit].
 */
public fun Int.toLength(unit: LengthUnit): Length {
    return toLong().toLength(unit)
}

/**
 * Returns a [Length] equal to [Long] number of the specified [unit].
 */
public fun Long.toLength(unit: LengthUnit): Length {
    return Length(this.saturated * unit.nanometerScale)
}

/**
 * Returns a [Length] equal to [Double] number of the specified [unit]. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public fun Double.toLength(unit: LengthUnit): Length {
    val valueInNanometers = this * unit.nanometerScale
    require(!valueInNanometers.isNaN()) { "Length value cannot be NaN." }
    return Length(valueInNanometers.roundToLong().saturated)
}
