package io.github.kevincianfarini.alchemist.unit

/**
 * A unit of length precise to the nanometer.
 */
public interface LengthUnit {

    /**
     * The amount of nanometers in this unit. Implementations of [LengthUnit] should be perfectly divisible by a
     * quantity of nanometers.
     */
    public val nanometerScale: Long

    /**
     * The symbol of this unit.
     */
    public val symbol: String

    /**
     * An International System of Units standard representation of length.
     */
    public enum class International(
        override val nanometerScale: Long,
        override val symbol: String,
    ) : LengthUnit {
        Nanometer(1, "nm"),
        Micrometer(1_000, "μm"),
        Millimeter(1_000_000, "mm"),
        Centimeter(10_000_000, "cm"),
        Meter(1_000_000_000, "m"),
        Kilometer(1_000_000_000_000, "km"),
        Megameter(1_000_000_000_000_000, "Mm"),
        Gigameter(1_000_000_000_000_000_000, "Gm"),
    }

    /**
     * A non-standard representation of length commonly used in the United States.
     */
    public enum class UnitedStatesCustomary(
        override val nanometerScale: Long,
        override val symbol: String,
    ) : LengthUnit {
        Inch(25_400_000, "in"),
        Foot(304_800_000, "ft"),
        Yard(914_400_000, "yd"),
        Mile(1_609_344_000_000, "mi"),
    }
}
