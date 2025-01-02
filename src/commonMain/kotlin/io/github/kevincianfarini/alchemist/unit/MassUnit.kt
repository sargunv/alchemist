package io.github.kevincianfarini.alchemist.unit

/**
 * A unit of mass precise to the microgram.
 */
public interface MassUnit {

    /**
     * The amount of micrograms in this unit. Implementations of [MassUnit] should be perfectly divisible by a
     * quantity of micrograms.
     */
    public val microgramScale: Long

    /**
     * The symbol of this unit.
     */
    public val symbol: String

    /**
     * An International System of Units standard representation of mass.
     */
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
