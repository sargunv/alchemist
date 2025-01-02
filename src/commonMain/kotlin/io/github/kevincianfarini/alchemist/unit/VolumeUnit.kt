package io.github.kevincianfarini.alchemist.unit

import kotlin.text.Typography.nbsp

/**
 * A unit of volume precise to the centimeter³.
 */
public interface VolumeUnit {

    /**
     * The symbol of this unit.
     */
    public val symbol: String

    /**
     * The amount of centimeter³ in this unit. Implementations of [TemperatureUnit] should be perfectly divisible by a
     * quantity of centimeter³.
     */
    public val cubicCentimetersScale: Long

    /**
     * A non-standard representation of volume commonly used as part of the metric system.
     */
    public enum class Metric(override val symbol: String, override val cubicCentimetersScale: Long) : VolumeUnit {
        Milliliter("${nbsp}mL", 1),
        Liter("${nbsp}L", 1_000),
        Kiloliter("${nbsp}kL", 1_000_000),
        Megaliter("${nbsp}ML", 1_000_000_000),
        Gigaliter("${nbsp}GL", 1_000_000_000_000),
        Teraliter("${nbsp}TL", 1_000_000_000_000_000),
        Petaliter("${nbsp}PL", 1_000_000_000_000_000_000),
    }
}
