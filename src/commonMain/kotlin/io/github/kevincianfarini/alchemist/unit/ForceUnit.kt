package io.github.kevincianfarini.alchemist.unit

/**
 * A unit of force precise to the nanonewton.
 */
public interface ForceUnit {

    /**
     * The symbol of this unit.
     */
    public val symbol: String

    /**
     * The amount of nanonewtons in this unit. Implementations of [ForceUnit] should be perfectly divisible by a
     * quantity of nanonewtons.
     */
    public val nanonewtonScale: Long

    /**
     * An International System of Units standard representation of force.
     */
    public enum class International(override val symbol: String, override val nanonewtonScale: Long) : ForceUnit {
        Nanonewton("nN", 1),
        Micronewton("μN", 1_000),
        Millinewton("mN", 1_000_000),
        Newton("N", 1_000_000_000),
        Kilonewton("kN", 1_000_000_000_000),
        Meganewton("MN", 1_000_000_000_000_000),
        Giganewton("GN", 1_000_000_000_000_000_000)
    }
}
