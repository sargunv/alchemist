package io.github.kevincianfarini.alchemist.unit

public interface ForceUnit {

    public val symbol: String

    public val nanonewtonScale: Long

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
