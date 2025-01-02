package io.github.kevincianfarini.alchemist.scalar

import io.github.kevincianfarini.alchemist.internal.saturated
import io.github.kevincianfarini.alchemist.type.Mass
import io.github.kevincianfarini.alchemist.unit.MassUnit
import kotlin.math.roundToLong

/**
 * Returns a [Mass] equal to [Int] number of micrograms.
 */
public inline val Int.micrograms: Mass get() = toMass(MassUnit.International.Microgram)

/**
 * Returns a [Mass] equal to [Long] number of micrograms.
 */
public inline val Long.micrograms: Mass get() = toMass(MassUnit.International.Microgram)

/**
 * Returns a [Mass] equal to [Int] number of milligrams.
 */
public inline val Int.milligrams: Mass get() = toMass(MassUnit.International.Milligram)

/**
 * Returns a [Mass] equal to [Long] number of milligrams.
 */
public inline val Long.milligrams: Mass get() = toMass(MassUnit.International.Milligram)

/**
 * Returns a [Mass] equal to [Double] number of milligrams. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.milligrams: Mass get() = toMass(MassUnit.International.Milligram)

/**
 * Returns a [Mass] equal to [Int] number of grams.
 */
public inline val Int.grams: Mass get() = toMass(MassUnit.International.Gram)

/**
 * Returns a [Mass] equal to [Long] number of grams.
 */
public inline val Long.grams: Mass get() = toMass(MassUnit.International.Gram)

/**
 * Returns a [Mass] equal to [Double] number of grams. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.grams: Mass get() = toMass(MassUnit.International.Gram)

/**
 * Returns a [Mass] equal to [Int] number of kilograms.
 */
public inline val Int.kilograms: Mass get() = toMass(MassUnit.International.Kilogram)

/**
 * Returns a [Mass] equal to [Long] number of kilograms.
 */
public inline val Long.kilograms: Mass get() = toMass(MassUnit.International.Kilogram)

/**
 * Returns a [Mass] equal to [Double] number of kilograms. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.kilograms: Mass get() = toMass(MassUnit.International.Kilogram)

/**
 * Returns a [Mass] equal to [Int] number of megagrams.
 */
public inline val Int.megagrams: Mass get() = toMass(MassUnit.International.Megagram)

/**
 * Returns a [Mass] equal to [Long] number of megagrams.
 */
public inline val Long.megagrams: Mass get() = toMass(MassUnit.International.Megagram)

/**
 * Returns a [Mass] equal to [Double] number of megagrams. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.megagrams: Mass get() = toMass(MassUnit.International.Megagram)

/**
 * Returns a [Mass] equal to [Int] number of metric tonnes.
 */
public inline val Int.metricTonnes: Mass get() = toMass(MassUnit.International.Megagram)

/**
 * Returns a [Mass] equal to [Long] number of metric tonnes.
 */
public inline val Long.metricTonnes: Mass get() = toMass(MassUnit.International.Megagram)

/**
 * Returns a [Mass] equal to [Double] number of metric tonnes. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.metricTonnes: Mass get() = toMass(MassUnit.International.Megagram)

/**
 * Returns a [Mass] equal to [Int] number of gigagrams.
 */
public inline val Int.gigagrams: Mass get() = toMass(MassUnit.International.Gigagram)

/**
 * Returns a [Mass] equal to [Long] number of gigagrams.
 */
public inline val Long.gigagrams: Mass get() = toMass(MassUnit.International.Gigagram)

/**
 * Returns a [Mass] equal to [Double] number of gigagrams. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.gigagrams: Mass get() = toMass(MassUnit.International.Gigagram)

/**
 * Returns a [Mass] equal to [Int] number of teragrams.
 */
public inline val Int.teragrams: Mass get() = toMass(MassUnit.International.Teragram)

/**
 * Returns a [Mass] equal to [Long] number of teragrams.
 */
public inline val Long.teragrams: Mass get() = toMass(MassUnit.International.Teragram)

/**
 * Returns a [Mass] equal to [Double] number of teragrams. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.teragrams: Mass get() = toMass(MassUnit.International.Teragram)

/**
 * Returns a [Mass] equal to [Int] number of the specified [unit].
 */
public fun Int.toMass(unit: MassUnit): Mass {
    return toLong().toMass(unit)
}

/**
 * Returns a [Mass] equal to [Long] number of the specified [unit].
 */
public fun Long.toMass(unit: MassUnit): Mass {
    return Mass(saturated * unit.microgramScale)
}

/**
 * Returns a [Mass] equal to [Double] number of the specified [unit]. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public fun Double.toMass(unit: MassUnit): Mass {
    val valueInMicrograms = this * unit.microgramScale
    require(!valueInMicrograms.isNaN()) { "Mass value cannot be NaN." }
    return Mass(valueInMicrograms.roundToLong().saturated)
}
