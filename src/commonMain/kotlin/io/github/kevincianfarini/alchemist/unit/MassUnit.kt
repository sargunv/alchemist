package io.github.kevincianfarini.alchemist.unit

public interface MassUnit {

    public val microgramScale: Long

    public val symbol: String

    public enum class International(override val microgramScale: Long, override val symbol: String): MassUnit {
        Microgram(1, "μg"),
        Milligram(1_000, "mg"),
        Gram(1_000_000, "g"),
        Kilogram(1_000_000_000, "kg"),
        Megagram(1_000_000_000_000, "Mg"),
        Gigagram(1_000_000_000_000_000, "Gg"),
        Teragram(1_000_000_000_000_000_000, "Tg"),
    }
}
