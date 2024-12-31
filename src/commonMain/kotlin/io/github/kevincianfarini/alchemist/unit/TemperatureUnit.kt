package io.github.kevincianfarini.alchemist.unit

import io.github.kevincianfarini.alchemist.internal.SaturatingLong
import io.github.kevincianfarini.alchemist.internal.saturated
import kotlin.math.roundToLong
import kotlin.text.Typography.nbsp

@RequiresOptIn(
    message = """
        Implementing TemperatureUnit requires detecting integer overflow detection, which normal Long values don't 
        expose. Implementors should exercise caution when converting between their custom temperature units and 
        nanokelvins. 
    """,
    level = RequiresOptIn.Level.ERROR,
)
public annotation class DelicateTemperatureUnit

/**
 * A unit of temperature precise to the nanokelvin.
 */
@SubclassOptInRequired(DelicateTemperatureUnit::class)
public interface TemperatureUnit {

    /**
     * The symbol of this unit.
     */
    public val symbol: String

    /**
     * Convert the degrees of this [TemperatureUnit] to nanokelvins.
     */
    public fun convertToNanokelvin(degrees: Long): Long

    /**
     * Convert the degrees of this [TemperatureUnit] to nanokelvins. Depending on its magnitude, some precision may be
     * lost.
     */
    public fun convertToNanokelvin(degrees: Double): Long

    /**
     * Convert the amount of [nanokelvins] of this temperature in this [TemperatureUnit].
     */
    public fun convertNanokelvinsToThis(nanokelvins: Long): Double

    public enum class International(override val symbol: String) : TemperatureUnit {
        Nanokelvin("${nbsp}nK") {
            override fun convertToNanokelvin(degrees: Long): Long = degrees
            override fun convertToNanokelvin(degrees: Double): Long {
                require(!degrees.isNaN()) { "Temperature value cannot be NaN." }
                return degrees.roundToLong()
            }
            override fun convertNanokelvinsToThis(nanokelvins: Long): Double = nanokelvins.toDouble()
        },
        Microkelvin("${nbsp}μK") {
            override fun convertToNanokelvin(degrees: Long): Long = (degrees.saturated * 1_000).rawValue
            override fun convertToNanokelvin(degrees: Double): Long {
                val ret = degrees * 1_000
                require(!ret.isNaN()) { "Temperature value cannot be NaN." }
                return ret.roundToLong()
            }
            override fun convertNanokelvinsToThis(nanokelvins: Long): Double = nanokelvins.saturated.toDouble() / 1_000
        },
        Millikelvin("${nbsp}mK") {
            override fun convertToNanokelvin(degrees: Long): Long = (degrees.saturated * 1_000_000).rawValue
            override fun convertToNanokelvin(degrees: Double): Long {
                val ret = degrees * 1_000_000
                require(!ret.isNaN()) { "Temperature value cannot be NaN." }
                return ret.roundToLong()
            }
            override fun convertNanokelvinsToThis(nanokelvins: Long): Double = nanokelvins.saturated.toDouble() / 1_000_000
        },
        Kelvin("${nbsp}K") {
            override fun convertToNanokelvin(degrees: Long): Long = (degrees.saturated * 1_000_000_000).rawValue
            override fun convertToNanokelvin(degrees: Double): Long {
                val ret = degrees * 1_000_000_000
                require(!ret.isNaN()) { "Temperature value cannot be NaN." }
                return ret.roundToLong()
            }
            override fun convertNanokelvinsToThis(nanokelvins: Long): Double = nanokelvins.saturated.toDouble() / 1_000_000_000
        },
        Kilokelvin("${nbsp}kK") {
            override fun convertToNanokelvin(degrees: Long): Long = (degrees.saturated * 1_000_000_000_000).rawValue
            override fun convertToNanokelvin(degrees: Double): Long {
                val ret = degrees * 1_000_000_000_000
                require(!ret.isNaN()) { "Temperature value cannot be NaN." }
                return ret.roundToLong()
            }
            override fun convertNanokelvinsToThis(nanokelvins: Long): Double = nanokelvins.saturated.toDouble() / 1_000_000_000_000
        },
        Megakelvin("${nbsp}MK") {
            override fun convertToNanokelvin(degrees: Long): Long = (degrees.saturated * 1_000_000_000_000_000).rawValue
            override fun convertToNanokelvin(degrees: Double): Long {
                val ret = degrees * 1_000_000_000_000_000
                require(!ret.isNaN()) { "Temperature value cannot be NaN." }
                return ret.roundToLong()
            }
            override fun convertNanokelvinsToThis(nanokelvins: Long): Double = nanokelvins.saturated.toDouble() / 1_000_000_000_000_000
        },
        Gigakelvin("${nbsp}GK") {
            override fun convertToNanokelvin(degrees: Long): Long = (degrees.saturated * 1_000_000_000_000_000_000).rawValue
            override fun convertToNanokelvin(degrees: Double): Long {
                val ret = degrees * 1_000_000_000_000_000_000
                require(!ret.isNaN()) { "Temperature value cannot be NaN." }
                return ret.roundToLong()
            }
            override fun convertNanokelvinsToThis(nanokelvins: Long): Double = nanokelvins.saturated.toDouble() / 1_000_000_000_000_000_000
        },
        Celsius("°C") {
            override fun convertToNanokelvin(degrees: Long): Long {
                return ((degrees.saturated * 1_000_000_000) + 273_150_000_000).rawValue
            }
            override fun convertToNanokelvin(degrees: Double): Long {
                val ret = (degrees * 1_000_000_000) + 273_150_000_000
                require(!ret.isNaN()) { "Temperature value cannot be NaN." }
                return ret.roundToLong()
            }
            override fun convertNanokelvinsToThis(nanokelvins: Long): Double {
                return (nanokelvins.saturated.toDouble() - 273_150_000_000) / 1_000_000_000
            }
        },
    }

    public object Fahrenheit : TemperatureUnit {
        override val symbol: String get() = "°F"
        override fun convertToNanokelvin(degrees: Long): Long {
            val accurate = (((degrees.saturated * 1_000_000_000) + 459_670_000_000) * 5) / 9
            return if (accurate.isFinite()) {
                accurate.rawValue
            } else {
                ((degrees.saturated * 555_555_556) + 255_372_222_222).rawValue
            }
        }

        override fun convertToNanokelvin(degrees: Double): Long {
            val ret = (((degrees * 1_000_000_000) + 459_670_000_000) * 5) / 9
            require(!ret.isNaN()) { "Temperature value cannot be NaN." }
            return ret.roundToLong()
        }
        override fun convertNanokelvinsToThis(nanokelvins: Long): Double {
            return (nanokelvins.saturated - 255_372_222_222).toDouble() / 555_555_556.toDouble()
        }
    }
}

internal fun TemperatureUnit.convertToNanokelvin(value: SaturatingLong): SaturatingLong {
    return convertToNanokelvin(value.rawValue).saturated
}

internal fun TemperatureUnit.convertNanokelvinsToThis(nanokelvins: SaturatingLong): Double {
    return convertNanokelvinsToThis(nanokelvins.rawValue)
}
