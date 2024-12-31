package io.github.kevincianfarini.alchemist.scalar

import io.github.kevincianfarini.alchemist.internal.SaturatingLong
import io.github.kevincianfarini.alchemist.internal.saturated
import io.github.kevincianfarini.alchemist.type.Temperature
import io.github.kevincianfarini.alchemist.unit.TemperatureUnit
import io.github.kevincianfarini.alchemist.unit.convertToNanokelvin


public val Int.nanokelvins: Temperature get() = toLong().nanokelvins
public val Long.nanokelvins: Temperature get() = saturated.nanokelvins
private inline val SaturatingLong.nanokelvins get() = Temperature(
    TemperatureUnit.International.Nanokelvin.convertToNanokelvin(this)
)

public val Int.microkelvins: Temperature get() = toLong().microkelvins
public val Long.microkelvins: Temperature get() = saturated.microkelvins
public val Double.microkelvins: Temperature
    get() = Temperature(
        TemperatureUnit.International.Microkelvin.convertToNanokelvin(this).saturated
    )
private inline val SaturatingLong.microkelvins get() = Temperature(
    TemperatureUnit.International.Microkelvin.convertToNanokelvin(this)
)

public val Int.millikelvins: Temperature get() = toLong().millikelvins
public val Long.millikelvins: Temperature get() = saturated.millikelvins
public val Double.millikelvins: Temperature
    get() = Temperature(
        TemperatureUnit.International.Millikelvin.convertToNanokelvin(this).saturated
    )
private inline val SaturatingLong.millikelvins get() = Temperature(
    TemperatureUnit.International.Millikelvin.convertToNanokelvin(this)
)

public val Int.kelvins: Temperature get() = toLong().kelvins
public val Long.kelvins: Temperature get() = saturated.kelvins
public val Double.kelvins: Temperature
    get() = Temperature(
        TemperatureUnit.International.Kelvin.convertToNanokelvin(this).saturated
    )
private inline val SaturatingLong.kelvins get() = Temperature(
    TemperatureUnit.International.Kelvin.convertToNanokelvin(this)
)

public val Int.kilokelvins: Temperature get() = toLong().kilokelvins
public val Long.kilokelvins: Temperature get() = saturated.kilokelvins
public val Double.kilokelvins: Temperature
    get() = Temperature(
        TemperatureUnit.International.Kilokelvin.convertToNanokelvin(this).saturated
    )
private inline val SaturatingLong.kilokelvins get() = Temperature(
    TemperatureUnit.International.Kilokelvin.convertToNanokelvin(this)
)

public val Int.megakelvins: Temperature get() = toLong().megakelvins
public val Long.megakelvins: Temperature get() = saturated.megakelvins
public val Double.megakelvins: Temperature
    get() = Temperature(
        TemperatureUnit.International.Megakelvin.convertToNanokelvin(this).saturated
    )
private inline val SaturatingLong.megakelvins get() = Temperature(
    TemperatureUnit.International.Megakelvin.convertToNanokelvin(this)
)

public val Int.gigakelvins: Temperature get() = toLong().gigakelvins
public val Long.gigakelvins: Temperature get() = saturated.gigakelvins
public val Double.gigakelvins: Temperature
    get() = Temperature(
        TemperatureUnit.International.Gigakelvin.convertToNanokelvin(this).saturated
    )
private inline val SaturatingLong.gigakelvins get() = Temperature(
    TemperatureUnit.International.Gigakelvin.convertToNanokelvin(this)
)

public val Int.celsius: Temperature get() = toLong().celsius
public val Long.celsius: Temperature get() = saturated.celsius
public val Double.celsius: Temperature
    get() = Temperature(
        TemperatureUnit.International.Celsius.convertToNanokelvin(this).saturated
    )
private inline val SaturatingLong.celsius get() = Temperature(
    TemperatureUnit.International.Celsius.convertToNanokelvin(this)
)

public val Int.fahrenheit: Temperature get() = toLong().fahrenheit
public val Long.fahrenheit: Temperature get() = saturated.fahrenheit
public val Double.fahrenheit: Temperature
    get() = Temperature(
        TemperatureUnit.Fahrenheit.convertToNanokelvin(this).saturated
    )
private inline val SaturatingLong.fahrenheit get() = Temperature(
    TemperatureUnit.Fahrenheit.convertToNanokelvin(this)
)
