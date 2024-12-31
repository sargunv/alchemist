package io.github.kevincianfarini.alchemist.scalar

import io.github.kevincianfarini.alchemist.internal.saturated
import io.github.kevincianfarini.alchemist.type.Mass
import io.github.kevincianfarini.alchemist.unit.MassUnit
import kotlin.math.roundToLong


public fun Int.toMass(unit: MassUnit): Mass {
    return toLong().toMass(unit)
}

public fun Long.toMass(unit: MassUnit): Mass {
    return Mass(saturated * unit.microgramScale)
}

public fun Double.toMass(unit: MassUnit): Mass {
    val valueInMicrograms = this * unit.microgramScale
    require(!valueInMicrograms.isNaN()) { "Mass value cannot be NaN." }
    return Mass(valueInMicrograms.roundToLong().saturated)
}

public inline val Int.micrograms: Mass get() = toMass(MassUnit.International.Microgram)
public inline val Long.micrograms: Mass get() = toMass(MassUnit.International.Microgram)

public inline val Int.milligrams: Mass get() = toMass(MassUnit.International.Milligram)
public inline val Long.milligrams: Mass get() = toMass(MassUnit.International.Milligram)
public inline val Double.milligrams: Mass get() = toMass(MassUnit.International.Milligram)

public inline val Int.grams: Mass get() = toMass(MassUnit.International.Gram)
public inline val Long.grams: Mass get() = toMass(MassUnit.International.Gram)
public inline val Double.grams: Mass get() = toMass(MassUnit.International.Gram)

public inline val Int.kilograms: Mass get() = toMass(MassUnit.International.Kilogram)
public inline val Long.kilograms: Mass get() = toMass(MassUnit.International.Kilogram)
public inline val Double.kilograms: Mass get() = toMass(MassUnit.International.Kilogram)

public inline val Int.megagrams: Mass get() = toMass(MassUnit.International.Megagram)
public inline val Long.megagrams: Mass get() = toMass(MassUnit.International.Megagram)
public inline val Double.megagrams: Mass get() = toMass(MassUnit.International.Megagram)

public inline val Int.metricTonnes: Mass get() = toMass(MassUnit.International.Megagram)
public inline val Long.metricTonnes: Mass get() = toMass(MassUnit.International.Megagram)
public inline val Double.metricTonnes: Mass get() = toMass(MassUnit.International.Megagram)

public inline val Int.gigagrams: Mass get() = toMass(MassUnit.International.Gigagram)
public inline val Long.gigagrams: Mass get() = toMass(MassUnit.International.Gigagram)
public inline val Double.gigagrams: Mass get() = toMass(MassUnit.International.Gigagram)

public inline val Int.teragrams: Mass get() = toMass(MassUnit.International.Teragram)
public inline val Long.teragrams: Mass get() = toMass(MassUnit.International.Teragram)
public inline val Double.teragrams: Mass get() = toMass(MassUnit.International.Teragram)
