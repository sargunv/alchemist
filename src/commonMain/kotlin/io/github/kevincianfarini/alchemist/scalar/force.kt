package io.github.kevincianfarini.alchemist.scalar

import io.github.kevincianfarini.alchemist.internal.saturated
import io.github.kevincianfarini.alchemist.type.Force
import io.github.kevincianfarini.alchemist.unit.ForceUnit
import kotlin.math.roundToLong

public inline val Int.nanonewtons: Force get() = toForce(ForceUnit.International.Nanonewton)
public inline val Long.nanonewtons: Force get() = toForce(ForceUnit.International.Nanonewton)

public inline val Int.micronewtons: Force get() = toForce(ForceUnit.International.Micronewton)
public inline val Long.micronewtons: Force get() = toForce(ForceUnit.International.Micronewton)
public inline val Double.micronewtons: Force get() = toForce(ForceUnit.International.Micronewton)

public inline val Int.millinewtons: Force get() = toForce(ForceUnit.International.Millinewton)
public inline val Long.millinewtons: Force get() = toForce(ForceUnit.International.Millinewton)
public inline val Double.millinewtons: Force get() = toForce(ForceUnit.International.Millinewton)

public inline val Int.newtons: Force get() = toForce(ForceUnit.International.Newton)
public inline val Long.newtons: Force get() = toForce(ForceUnit.International.Newton)
public inline val Double.newtons: Force get() = toForce(ForceUnit.International.Newton)

public inline val Int.kilonewtons: Force get() = toForce(ForceUnit.International.Kilonewton)
public inline val Long.kilonewtons: Force get() = toForce(ForceUnit.International.Kilonewton)
public inline val Double.kilonewtons: Force get() = toForce(ForceUnit.International.Kilonewton)

public inline val Int.meganewtons: Force get() = toForce(ForceUnit.International.Meganewton)
public inline val Long.meganewtons: Force get() = toForce(ForceUnit.International.Meganewton)
public inline val Double.meganewtons: Force get() = toForce(ForceUnit.International.Meganewton)

public inline val Int.giganewtons: Force get() = toForce(ForceUnit.International.Giganewton)
public inline val Long.giganewtons: Force get() = toForce(ForceUnit.International.Giganewton)
public inline val Double.giganewtons: Force get() = toForce(ForceUnit.International.Giganewton)

public fun Int.toForce(unit: ForceUnit): Force = toLong().toForce(unit)

public fun Long.toForce(unit: ForceUnit): Force = Force(saturated * unit.nanonewtonScale)

public fun Double.toForce(unit: ForceUnit): Force {
    val valueInNanonewtons = this * unit.nanonewtonScale
    require(!valueInNanonewtons.isNaN()) { "Force value cannot be NaN." }
    return Force(valueInNanonewtons.roundToLong().saturated)
}
