package io.github.kevincianfarini.alchemist.unit

import kotlin.text.Typography.nbsp

public interface VolumeUnit {

    public val symbol: String

    public val cubicCentimetersScale: Long

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
