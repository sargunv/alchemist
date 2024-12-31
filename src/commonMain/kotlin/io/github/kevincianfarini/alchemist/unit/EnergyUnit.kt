package io.github.kevincianfarini.alchemist.unit

/**
 * A unit of energy precise to the millijoule.
 */
public interface EnergyUnit {

    /**
     * The amount of millijoules in this unit. Implementations of [EnergyUnit] should be perfectly divisible by a
     * quantity of millijoules.
     */
    public val millijouleScale: Long

    /**
     * The symbol of this unit.
     */
    public val symbol: String

    public enum class International(
        override val millijouleScale: Long,
        override val symbol: String,
    ) : EnergyUnit {
        Millijoule(1, "mJ"),
        Joule(1_000, "J"),
        Kilojoule(1_000_000, "kJ"),
        Megajoule(1_000_000_000, "MJ"),
        Gigajoule(1_000_000_000_000, "GJ"),
        Tetrajoule(1_000_000_000_000_000, "TJ"),
        Petajoule(1_000_000_000_000_000_000, "PJ"),
    }

    public enum class Electricity(
        override val millijouleScale: Long,
        override val symbol: String,
    ) : EnergyUnit {
        MilliwattHour(3_600, "mWh"),
        WattHour(3_600_000, "Wh"),
        KilowattHour(3_600_000_000, "kWh"),
        MegawattHour(3_600_000_000_000, "MWh"),
        GigawattHour(3_600_000_000_000_000, "GWh"),
        TerawattHour(3_600_000_000_000_000_000, "TWh"),
    }
}