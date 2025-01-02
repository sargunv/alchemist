package io.github.kevincianfarini.alchemist.scalar

import io.github.kevincianfarini.alchemist.internal.saturated
import io.github.kevincianfarini.alchemist.type.Volume
import io.github.kevincianfarini.alchemist.unit.VolumeUnit
import kotlin.math.roundToLong

/**
 * Returns a [Volume] equal to [Int] number of milliliters.
 */
public inline val Int.milliliters: Volume get() = toVolume(VolumeUnit.Metric.Milliliter)

/**
 * Returns a [Volume] equal to [Long] number of milliliters.
 */
public inline val Long.milliliters: Volume get() = toVolume(VolumeUnit.Metric.Milliliter)

/**
 * Returns a [Volume] equal to [Int] number of liters.
 */
public inline val Int.liters: Volume get() = toVolume(VolumeUnit.Metric.Liter)

/**
 * Returns a [Volume] equal to [Long] number of liters.
 */
public inline val Long.liters: Volume get() = toVolume(VolumeUnit.Metric.Liter)

/**
 * Returns a [Volume] equal to [Double] number of liters. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.liters: Volume get() = toVolume(VolumeUnit.Metric.Liter)

/**
 * Returns a [Volume] equal to [Int] number of kiloliters.
 */
public inline val Int.kiloliters: Volume get() = toVolume(VolumeUnit.Metric.Kiloliter)

/**
 * Returns a [Volume] equal to [Long] number of kiloliters.
 */
public inline val Long.kiloliters: Volume get() = toVolume(VolumeUnit.Metric.Kiloliter)

/**
 * Returns a [Volume] equal to [Double] number of kiloliters. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.kiloliters: Volume get() = toVolume(VolumeUnit.Metric.Kiloliter)

/**
 * Returns a [Volume] equal to [Int] number of megaliters.
 */
public inline val Int.megaliters: Volume get() = toVolume(VolumeUnit.Metric.Megaliter)

/**
 * Returns a [Volume] equal to [Long] number of megaliters.
 */
public inline val Long.megaliters: Volume get() = toVolume(VolumeUnit.Metric.Megaliter)

/**
 * Returns a [Volume] equal to [Double] number of megaliters. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.megaliters: Volume get() = toVolume(VolumeUnit.Metric.Megaliter)

/**
 * Returns a [Volume] equal to [Int] number of gigaliters.
 */
public inline val Int.gigaliters: Volume get() = toVolume(VolumeUnit.Metric.Gigaliter)

/**
 * Returns a [Volume] equal to [Long] number of gigaliters.
 */
public inline val Long.gigaliters: Volume get() = toVolume(VolumeUnit.Metric.Gigaliter)

/**
 * Returns a [Volume] equal to [Double] number of gigaliters. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.gigaliters: Volume get() = toVolume(VolumeUnit.Metric.Gigaliter)

/**
 * Returns a [Volume] equal to [Int] number of teraliters.
 */
public inline val Int.teraliters: Volume get() = toVolume(VolumeUnit.Metric.Teraliter)

/**
 * Returns a [Volume] equal to [Long] number of teraliters.
 */
public inline val Long.teraliters: Volume get() = toVolume(VolumeUnit.Metric.Teraliter)

/**
 * Returns a [Volume] equal to [Double] number of teraliters. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.teraliters: Volume get() = toVolume(VolumeUnit.Metric.Teraliter)

/**
 * Returns a [Volume] equal to [Int] number of petaliters.
 */
public inline val Int.petaliters: Volume get() = toVolume(VolumeUnit.Metric.Petaliter)

/**
 * Returns a [Volume] equal to [Long] number of petaliters.
 */
public inline val Long.petaliters: Volume get() = toVolume(VolumeUnit.Metric.Petaliter)

/**
 * Returns a [Volume] equal to [Double] number of petaliters. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public inline val Double.petaliters: Volume get() = toVolume(VolumeUnit.Metric.Petaliter)

/**
 * Returns a [Volume] equal to [Int] number of the specified [unit].
 */
public fun Int.toVolume(unit: VolumeUnit): Volume = toLong().toVolume(unit)

/**
 * Returns a [Volume] equal to [Long] number of the specified [unit].
 */
public fun Long.toVolume(unit: VolumeUnit): Volume {
    return Volume(saturated * unit.cubicCentimetersScale)
}

/**
 * Returns a [Volume] equal to [Double] number of the specified [unit]. Depending on its magnitude, some precision may
 * be lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public fun Double.toVolume(unit: VolumeUnit): Volume {
    val valueInCubicCentis = this * unit.cubicCentimetersScale
    require(!valueInCubicCentis.isNaN()) { "Volume value cannot be NaN." }
    return Volume(valueInCubicCentis.roundToLong().saturated)
}
