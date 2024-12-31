package io.github.kevincianfarini.alchemist.scalar

import io.github.kevincianfarini.alchemist.internal.SaturatingLong
import io.github.kevincianfarini.alchemist.internal.saturated
import io.github.kevincianfarini.alchemist.type.Energy
import io.github.kevincianfarini.alchemist.unit.EnergyUnit
import kotlin.math.roundToLong

/**
 * Returns an [Energy] equal to [Int] number of millijoules.
 */
public inline val Int.millijoules: Energy get() = toEnergy(EnergyUnit.International.Millijoule)

/**
 * Returns an [Energy] equal to [Long] number of millijoules.
 */
public inline val Long.millijoules: Energy get() = toEnergy(EnergyUnit.International.Millijoule)
internal inline val SaturatingLong.millijoules get() = Energy(this)

/**
 * Returns an [Energy] equal to [Int] number of joules.
 */
public inline val Int.joules: Energy get() = toEnergy(EnergyUnit.International.Joule)

/**
 * Returns an [Energy] equal to [Long] number of joules.
 */
public inline val Long.joules: Energy get() = toEnergy(EnergyUnit.International.Joule)

/**
 * Returns an [Energy] equal to [Double] number of joules. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.joules: Energy get() = toEnergy(EnergyUnit.International.Joule)

/**
 * Returns an [Energy] equal to [Int] number of kilojoules.
 */
public inline val Int.kilojoules: Energy get() = toEnergy(EnergyUnit.International.Kilojoule)

/**
 * Returns an [Energy] equal to [Long] number of kilojoules.
 */
public inline val Long.kilojoules: Energy get() = toEnergy(EnergyUnit.International.Kilojoule)

/**
 * Returns an [Energy] equal to [Double] number of kilojoules. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.kilojoules: Energy get() = toEnergy(EnergyUnit.International.Kilojoule)

/**
 * Returns an [Energy] equal to [Int] number of megajoules.
 */
public inline val Int.megajoules: Energy get() = toEnergy(EnergyUnit.International.Megajoule)

/**
 * Returns an [Energy] equal to [Long] number of megajoules.
 */
public inline val Long.megajoules: Energy get() = toEnergy(EnergyUnit.International.Megajoule)

/**
 * Returns an [Energy] equal to [Double] number of megajoules. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.megajoules: Energy get() = toEnergy(EnergyUnit.International.Megajoule)

/**
 * Returns an [Energy] equal to [Int] number of gigajoules.
 */
public inline val Int.gigajoules: Energy get() = toEnergy(EnergyUnit.International.Gigajoule)

/**
 * Returns an [Energy] equal to [Long] number of gigajoules.
 */
public inline val Long.gigajoules: Energy get() = toEnergy(EnergyUnit.International.Gigajoule)

/**
 * Returns an [Energy] equal to [Double] number of gigajoules. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.gigajoules: Energy get() = toEnergy(EnergyUnit.International.Gigajoule)

/**
 * Returns an [Energy] equal to [Int] number of tetrajoules.
 */
public inline val Int.tetrajoules: Energy get() = toEnergy(EnergyUnit.International.Tetrajoule)

/**
 * Returns an [Energy] equal to [Long] number of tetrajoules.
 */
public inline val Long.tetrajoules: Energy get() = toEnergy(EnergyUnit.International.Tetrajoule)

/**
 * Returns an [Energy] equal to [Double] number of tetrajoules. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.tetrajoules: Energy get() = toEnergy(EnergyUnit.International.Tetrajoule)

/**
 * Returns an [Energy] equal to [Int] number of petajoules.
 */
public inline val Int.petajoules: Energy get() = toEnergy(EnergyUnit.International.Petajoule)

/**
 * Returns an [Energy] equal to [Long] number of petajoules.
 */
public inline val Long.petajoules: Energy get() = toEnergy(EnergyUnit.International.Petajoule)

/**
 * Returns an [Energy] equal to [Double] number of petajoules. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.petajoules: Energy get() = toEnergy(EnergyUnit.International.Petajoule)

/**
 * Returns an [Energy] equal to [Int] number of milliwatt-hours.
 */
public inline val Int.milliwattHours: Energy get() = toEnergy(EnergyUnit.Electricity.MilliwattHour)

/**
 * Returns an [Energy] equal to [Long] number of milliwatt-hours.
 */
public inline val Long.milliwattHours: Energy get() = toEnergy(EnergyUnit.Electricity.MilliwattHour)

/**
 * Returns an [Energy] equal to [Double] number of milliwatt-hours. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.milliwattHours: Energy get() = toEnergy(EnergyUnit.Electricity.MilliwattHour)

/**
 * Returns an [Energy] equal to [Int] number of watt-hours.
 */
public inline val Int.wattHours: Energy get() = toEnergy(EnergyUnit.Electricity.WattHour)

/**
 * Returns an [Energy] equal to [Long] number of watt-hours.
 */
public inline val Long.wattHours: Energy get() = toEnergy(EnergyUnit.Electricity.WattHour)

/**
 * Returns an [Energy] equal to [Double] number of watt-hours. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.wattHours: Energy get() = toEnergy(EnergyUnit.Electricity.WattHour)

/**
 * Returns an [Energy] equal to [Int] number of kilowatt-hours.
 */
public inline val Int.kilowattHours: Energy get() = toEnergy(EnergyUnit.Electricity.KilowattHour)

/**
 * Returns an [Energy] equal to [Long] number of kilowatt-hours.
 */
public inline val Long.kilowattHours: Energy get() = toEnergy(EnergyUnit.Electricity.KilowattHour)

/**
 * Returns an [Energy] equal to [Double] number of kilowatt-hours. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.kilowattHours: Energy get() = toEnergy(EnergyUnit.Electricity.KilowattHour)

/**
 * Returns an [Energy] equal to [Int] number of megawatt-hours.
 */
public inline val Int.megawattHours: Energy get() = toEnergy(EnergyUnit.Electricity.MegawattHour)

/**
 * Returns an [Energy] equal to [Long] number of megawatt-hours.
 */
public inline val Long.megawattHours: Energy get() = toEnergy(EnergyUnit.Electricity.MegawattHour)

/**
 * Returns an [Energy] equal to [Double] number of megawatt-hours. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.megawattHours: Energy get() = toEnergy(EnergyUnit.Electricity.MegawattHour)

/**
 * Returns an [Energy] equal to [Int] number of gigawatt-hours.
 */
public inline val Int.gigawattHours: Energy get() = toEnergy(EnergyUnit.Electricity.GigawattHour)

/**
 * Returns an [Energy] equal to [Long] number of gigawatt-hours.
 */
public inline val Long.gigawattHours: Energy get() = toEnergy(EnergyUnit.Electricity.GigawattHour)

/**
 * Returns an [Energy] equal to [Double] number of gigawatt-hours. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.gigawattHours: Energy get() = toEnergy(EnergyUnit.Electricity.GigawattHour)

/**
 * Returns an [Energy] equal to [Int] number of terawatt-hours.
 */
public inline val Int.terawattHours: Energy get() = toEnergy(EnergyUnit.Electricity.TerawattHour)

/**
 * Returns an [Energy] equal to [Int] number of terawatt-hours.
 */
public inline val Long.terawattHours: Energy get() = toEnergy(EnergyUnit.Electricity.TerawattHour)

/**
 * Returns an [Energy] equal to [Double] number of terawatt-hours. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.terawattHours: Energy get() = toEnergy(EnergyUnit.Electricity.TerawattHour)

/**
 * Returns an [Energy] equal to [Int] number of the specified [unit].
 */
public fun Int.toEnergy(unit: EnergyUnit): Energy {
    return toLong().toEnergy(unit)
}

/**
 * Returns an [Energy] equal to [Long] number of the specified [unit].
 */
public fun Long.toEnergy(unit: EnergyUnit): Energy {
    return Energy(this.saturated * unit.millijouleScale)
}

/**
 * Returns an [Energy] equal to [Double] number of the specified [unit]. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public fun Double.toEnergy(unit: EnergyUnit): Energy {
    val valueInMillijoules = this * unit.millijouleScale
    require(!valueInMillijoules.isNaN()) { "Energy value cannot be NaN." }
    return Energy(valueInMillijoules.roundToLong().saturated)
}
