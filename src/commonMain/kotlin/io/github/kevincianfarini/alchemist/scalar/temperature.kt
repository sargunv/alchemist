package io.github.kevincianfarini.alchemist.scalar

import io.github.kevincianfarini.alchemist.internal.SaturatingLong
import io.github.kevincianfarini.alchemist.internal.saturated
import io.github.kevincianfarini.alchemist.type.Temperature
import io.github.kevincianfarini.alchemist.unit.TemperatureUnit
import io.github.kevincianfarini.alchemist.unit.convertToNanokelvin

/**
 * Returns a [Temperature] equal to [Int] number of nanokelvins.
 */
public val Int.nanokelvins: Temperature get() = toLong().nanokelvins

/**
 * Returns a [Temperature] equal to [Long] number of nanokelvins.
 */
public val Long.nanokelvins: Temperature get() = saturated.nanokelvins

/**
 * Returns a [Temperature] equal to [Int] number of microkelvins.
 */
public val Int.microkelvins: Temperature get() = toLong().microkelvins

/**
 * Returns a [Temperature] equal to [Long] number of microkelvins.
 */
public val Long.microkelvins: Temperature get() = saturated.microkelvins

/**
 * Returns a [Temperature] equal to [Double] number of microkelvins. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public val Double.microkelvins: Temperature get() = Temperature(
    TemperatureUnit.International.Microkelvin.convertToNanokelvin(this).saturated
)

/**
 * Returns a [Temperature] equal to [Int] number of millikelvins.
 */
public val Int.millikelvins: Temperature get() = toLong().millikelvins

/**
 * Returns a [Temperature] equal to [Long] number of millikelvins.
 */
public val Long.millikelvins: Temperature get() = saturated.millikelvins

/**
 * Returns a [Temperature] equal to [Double] number of millikelvins. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public val Double.millikelvins: Temperature get() = Temperature(
    TemperatureUnit.International.Millikelvin.convertToNanokelvin(this).saturated
)

/**
 * Returns a [Temperature] equal to [Int] number of kelvins.
 */
public val Int.kelvins: Temperature get() = toLong().kelvins

/**
 * Returns a [Temperature] equal to [Long] number of kelvins.
 */
public val Long.kelvins: Temperature get() = saturated.kelvins

/**
 * Returns a [Temperature] equal to [Double] number of kelvins. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public val Double.kelvins: Temperature get() = Temperature(
    TemperatureUnit.International.Kelvin.convertToNanokelvin(this).saturated
)

/**
 * Returns a [Temperature] equal to [Int] number of kilokelvins.
 */
public val Int.kilokelvins: Temperature get() = toLong().kilokelvins

/**
 * Returns a [Temperature] equal to [Long] number of kilokelvins.
 */
public val Long.kilokelvins: Temperature get() = saturated.kilokelvins

/**
 * Returns a [Temperature] equal to [Double] number of kilokelvins. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public val Double.kilokelvins: Temperature get() = Temperature(
    TemperatureUnit.International.Kilokelvin.convertToNanokelvin(this).saturated
)

/**
 * Returns a [Temperature] equal to [Int] number of megakelvins.
 */
public val Int.megakelvins: Temperature get() = toLong().megakelvins

/**
 * Returns a [Temperature] equal to [Long] number of megakelvins.
 */
public val Long.megakelvins: Temperature get() = saturated.megakelvins

/**
 * Returns a [Temperature] equal to [Double] number of megakelvins. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public val Double.megakelvins: Temperature get() = Temperature(
    TemperatureUnit.International.Megakelvin.convertToNanokelvin(this).saturated
)

/**
 * Returns a [Temperature] equal to [Int] number of gigakelvins.
 */
public val Int.gigakelvins: Temperature get() = toLong().gigakelvins

/**
 * Returns a [Temperature] equal to [Long] number of gigakelvins.
 */
public val Long.gigakelvins: Temperature get() = saturated.gigakelvins

/**
 * Returns a [Temperature] equal to [Double] number of gigakelvins. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public val Double.gigakelvins: Temperature get() = Temperature(
    TemperatureUnit.International.Gigakelvin.convertToNanokelvin(this).saturated
)

/**
 * Returns a [Temperature] equal to [Int] number of Celsius.
 */
public val Int.celsius: Temperature get() = toLong().celsius

/**
 * Returns a [Temperature] equal to [Long] number of Celsius.
 */
public val Long.celsius: Temperature get() = saturated.celsius

/**
 * Returns a [Temperature] equal to [Double] number of Celsius. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public val Double.celsius: Temperature get() = Temperature(
    TemperatureUnit.International.Celsius.convertToNanokelvin(this).saturated
)

/**
 * Returns a [Temperature] equal to [Int] number of Fahrenheit.
 */
public val Int.fahrenheit: Temperature get() = toLong().fahrenheit

/**
 * Returns a [Temperature] equal to [Long] number of Fahrenheit.
 */
public val Long.fahrenheit: Temperature get() = saturated.fahrenheit

/**
 * Returns a [Temperature] equal to [Double] number of Fahrenheit. Depending on its magnitude, some precision may be
 * lost.
 *
 * @throws IllegalArgumentException is this [Double] is [Double.NaN].
 */
public val Double.fahrenheit: Temperature get() = Temperature(
    TemperatureUnit.Fahrenheit.convertToNanokelvin(this).saturated
)

private inline val SaturatingLong.microkelvins get() = Temperature(
    TemperatureUnit.International.Microkelvin.convertToNanokelvin(this)
)
private inline val SaturatingLong.nanokelvins get() = Temperature(
    TemperatureUnit.International.Nanokelvin.convertToNanokelvin(this)
)
private inline val SaturatingLong.millikelvins get() = Temperature(
    TemperatureUnit.International.Millikelvin.convertToNanokelvin(this)
)
private inline val SaturatingLong.kelvins get() = Temperature(
    TemperatureUnit.International.Kelvin.convertToNanokelvin(this)
)
private inline val SaturatingLong.kilokelvins get() = Temperature(
    TemperatureUnit.International.Kilokelvin.convertToNanokelvin(this)
)
private inline val SaturatingLong.megakelvins get() = Temperature(
    TemperatureUnit.International.Megakelvin.convertToNanokelvin(this)
)
private inline val SaturatingLong.gigakelvins get() = Temperature(
    TemperatureUnit.International.Gigakelvin.convertToNanokelvin(this)
)
private inline val SaturatingLong.celsius get() = Temperature(
    TemperatureUnit.International.Celsius.convertToNanokelvin(this)
)
private inline val SaturatingLong.fahrenheit get() = Temperature(
    TemperatureUnit.Fahrenheit.convertToNanokelvin(this)
)
