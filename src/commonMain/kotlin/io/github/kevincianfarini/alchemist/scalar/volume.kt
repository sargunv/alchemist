package io.github.kevincianfarini.alchemist.scalar

import io.github.kevincianfarini.alchemist.internal.saturated
import io.github.kevincianfarini.alchemist.type.Volume
import io.github.kevincianfarini.alchemist.unit.VolumeUnit
import kotlin.math.roundToLong

public inline val Int.milliliters: Volume get() = toVolume(VolumeUnit.Metric.Milliliter)
public inline val Long.milliliters: Volume get() = toVolume(VolumeUnit.Metric.Milliliter)

public inline val Int.liters: Volume get() = toVolume(VolumeUnit.Metric.Liter)
public inline val Long.liters: Volume get() = toVolume(VolumeUnit.Metric.Liter)
public inline val Double.liters: Volume get() = toVolume(VolumeUnit.Metric.Liter)

public inline val Int.kiloliters: Volume get() = toVolume(VolumeUnit.Metric.Kiloliter)
public inline val Long.kiloliters: Volume get() = toVolume(VolumeUnit.Metric.Kiloliter)
public inline val Double.kiloliters: Volume get() = toVolume(VolumeUnit.Metric.Kiloliter)

public inline val Int.megaliters: Volume get() = toVolume(VolumeUnit.Metric.Megaliter)
public inline val Long.megaliters: Volume get() = toVolume(VolumeUnit.Metric.Megaliter)
public inline val Double.megaliters: Volume get() = toVolume(VolumeUnit.Metric.Megaliter)

public inline val Int.gigaliters: Volume get() = toVolume(VolumeUnit.Metric.Gigaliter)
public inline val Long.gigaliters: Volume get() = toVolume(VolumeUnit.Metric.Gigaliter)
public inline val Double.gigaliters: Volume get() = toVolume(VolumeUnit.Metric.Gigaliter)

public inline val Int.teraliters: Volume get() = toVolume(VolumeUnit.Metric.Teraliter)
public inline val Long.teraliters: Volume get() = toVolume(VolumeUnit.Metric.Teraliter)
public inline val Double.teraliters: Volume get() = toVolume(VolumeUnit.Metric.Teraliter)

public inline val Int.petaliters: Volume get() = toVolume(VolumeUnit.Metric.Petaliter)
public inline val Long.petaliters: Volume get() = toVolume(VolumeUnit.Metric.Petaliter)
public inline val Double.petaliters: Volume get() = toVolume(VolumeUnit.Metric.Petaliter)

public fun Int.toVolume(unit: VolumeUnit): Volume = toLong().toVolume(unit)

public fun Long.toVolume(unit: VolumeUnit): Volume {
    return Volume(saturated * unit.cubicCentimetersScale)
}

public fun Double.toVolume(unit: VolumeUnit): Volume {
    val valueInCubicCentis = this * unit.cubicCentimetersScale
    require(!valueInCubicCentis.isNaN()) { "Volume value cannot be NaN." }
    return Volume(valueInCubicCentis.roundToLong().saturated)
}
