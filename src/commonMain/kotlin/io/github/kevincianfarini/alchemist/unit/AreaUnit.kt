package io.github.kevincianfarini.alchemist.unit


/**
 * A unit of area precise to the millimeter².
 */
public interface AreaUnit {

    /**
     * The amount of millimeters² in this unit. Implementations of [AreaUnit] should be perfectly divisible by a
     * quantity of millimeters².
     */
    public val millimetersSquaredScale: Long

    /**
     * The symbol of this unit.
     */
    public val symbol: String

    public enum class Metric(override val symbol: String, override val millimetersSquaredScale: Long) : AreaUnit {
        Decimilliare("dma", 100),
        Centiare("ca", 1_000_000),
        Deciare("da", 10_000_000),
        Are("a", 100_000_000),
        Decare("daa", 1_000_000_000),
        Hectare("ha", 10_000_000_000),
    }
}
