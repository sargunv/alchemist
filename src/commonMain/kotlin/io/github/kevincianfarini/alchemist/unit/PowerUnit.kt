package io.github.kevincianfarini.alchemist.unit

/**
 * A unit of power precise to the microwatt.
 */
public interface PowerUnit {

    /**
     * The amount of microwatts in this unit. Implementations of [PowerUnit] should be perfectly divisible by a
     * quantity of microwatts.
     */
    public val microwattScale: Long

    /**
     * The symbol of this unit.
     */
    public val symbol: String

    public enum class International(
        override val microwattScale: Long,
        override val symbol: String,
    ) : PowerUnit {
        Microwatt(1, "μW"),
        Milliwatt(1_000, "mW"),
        Watt(1_000_000, "W"),
        Kilowatt(1_000_000_000, "kW"),
        Megawatt(1_000_000_000_000, "MW"),
        Gigawatt(1_000_000_000_000_000, "GW"),
        Terawatt(1_000_000_000_000_000_000, "TW"),
    }
}
