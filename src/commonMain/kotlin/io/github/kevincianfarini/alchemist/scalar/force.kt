package io.github.kevincianfarini.alchemist.scalar

import io.github.kevincianfarini.alchemist.internal.saturated
import io.github.kevincianfarini.alchemist.type.Force
import io.github.kevincianfarini.alchemist.unit.ForceUnit
import kotlin.math.roundToLong

/**
 * Returns an [Force] equal to [Int] number of nanonewtons.
 */
public inline val Int.nanonewtons: Force get() = toForce(ForceUnit.International.Nanonewton)

/**
 * Returns an [Force] equal to [Long] number of nanonewtons.
 */
public inline val Long.nanonewtons: Force get() = toForce(ForceUnit.International.Nanonewton)

/**
 * Returns an [Force] equal to [Int] number of micronewtons.
 */
public inline val Int.micronewtons: Force get() = toForce(ForceUnit.International.Micronewton)

/**
 * Returns an [Force] equal to [Long] number of micronewtons.
 */
public inline val Long.micronewtons: Force get() = toForce(ForceUnit.International.Micronewton)

/**
 * Returns an [Force] equal to [Double] number of micronewtons. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.micronewtons: Force get() = toForce(ForceUnit.International.Micronewton)

/**
 * Returns an [Force] equal to [Int] number of millinewtons.
 */
public inline val Int.millinewtons: Force get() = toForce(ForceUnit.International.Millinewton)

/**
 * Returns an [Force] equal to [Long] number of millinewtons.
 */
public inline val Long.millinewtons: Force get() = toForce(ForceUnit.International.Millinewton)

/**
 * Returns an [Force] equal to [Double] number of millinewtons. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.millinewtons: Force get() = toForce(ForceUnit.International.Millinewton)

/**
 * Returns an [Force] equal to [Int] number of newtons.
 */
public inline val Int.newtons: Force get() = toForce(ForceUnit.International.Newton)

/**
 * Returns an [Force] equal to [Long] number of newtons.
 */
public inline val Long.newtons: Force get() = toForce(ForceUnit.International.Newton)

/**
 * Returns an [Force] equal to [Double] number of newtons. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.newtons: Force get() = toForce(ForceUnit.International.Newton)

/**
 * Returns an [Force] equal to [Int] number of kilonewtons.
 */
public inline val Int.kilonewtons: Force get() = toForce(ForceUnit.International.Kilonewton)

/**
 * Returns an [Force] equal to [Long] number of kilonewtons.
 */
public inline val Long.kilonewtons: Force get() = toForce(ForceUnit.International.Kilonewton)

/**
 * Returns an [Force] equal to [Double] number of kilonewtons. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.kilonewtons: Force get() = toForce(ForceUnit.International.Kilonewton)

/**
 * Returns an [Force] equal to [Int] number of meganewtons.
 */
public inline val Int.meganewtons: Force get() = toForce(ForceUnit.International.Meganewton)

/**
 * Returns an [Force] equal to [Long] number of meganewtons.
 */
public inline val Long.meganewtons: Force get() = toForce(ForceUnit.International.Meganewton)

/**
 * Returns an [Force] equal to [Double] number of meganewtons. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.meganewtons: Force get() = toForce(ForceUnit.International.Meganewton)

/**
 * Returns an [Force] equal to [Int] number of giganewtons.
 */
public inline val Int.giganewtons: Force get() = toForce(ForceUnit.International.Giganewton)

/**
 * Returns an [Force] equal to [Long] number of giganewtons.
 */
public inline val Long.giganewtons: Force get() = toForce(ForceUnit.International.Giganewton)

/**
 * Returns an [Force] equal to [Double] number of giganewtons. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.giganewtons: Force get() = toForce(ForceUnit.International.Giganewton)

/**
 * Returns an [Force] equal to [Int] number of the specified [unit].
 */
public fun Int.toForce(unit: ForceUnit): Force = toLong().toForce(unit)

/**
 * Returns an [Force] equal to [Long] number of the specified [unit].
 */
public fun Long.toForce(unit: ForceUnit): Force = Force(saturated * unit.nanonewtonScale)

/**
 * Returns an [Force] equal to [Double] number of the specified [unit]. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public fun Double.toForce(unit: ForceUnit): Force {
    val valueInNanonewtons = this * unit.nanonewtonScale
    require(!valueInNanonewtons.isNaN()) { "Force value cannot be NaN." }
    return Force(valueInNanonewtons.roundToLong().saturated)
}
