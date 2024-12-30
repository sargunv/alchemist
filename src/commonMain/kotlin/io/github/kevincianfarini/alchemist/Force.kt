package io.github.kevincianfarini.alchemist

import io.github.kevincianfarini.alchemist.internal.SaturatingLong
import io.github.kevincianfarini.alchemist.internal.saturated
import io.github.kevincianfarini.alchemist.internal.toDecimalString
import kotlin.jvm.JvmInline
import kotlin.math.roundToLong

@JvmInline
public value class Force internal constructor(private val rawNanonewtons: SaturatingLong) : Comparable<Force> {

    // region SI Arithmetic

    /**
     * Returns the resulting [Acceleration] from applying this force to the specified [mass].
     */
    public operator fun div(mass: Mass): Acceleration = TODO()

    /**
     * Returns the amount of [Energy] required to apply this force over the specified [length].
     */
    public operator fun times(length: Length): Energy = TODO()

    // endregion

    // region Scalar Arithmetic

    /**
     * Returns the number that is the ratio of this and the [other] force value.
     */
    public operator fun div(other: Force): Double = rawNanonewtons.toDouble() / other.rawNanonewtons.toDouble()

    /**
     * Returns a force whose value is this force value divided by the specified [scale].
     */
    public operator fun div(scale: Int): Force = div(scale.toLong())

    /**
     * Returns a force whose value is this force value divided by the specified [scale].
     */
    public operator fun div(scale: Long): Force = Force(rawNanonewtons / scale)

    /**
     * Returns a force whose value is the difference between this and the [other] force value.
     */
    public operator fun minus(other: Force): Force = Force(rawNanonewtons - other.rawNanonewtons)

    /**
     * Returns a force whose value is the sum between this and the [other] force value.
     */
    public operator fun plus(other: Force): Force = Force(rawNanonewtons + other.rawNanonewtons)

    /**
     * Returns a force whose value is multiplied by the specified [scale].
     */
    public operator fun times(scale: Int): Force = times(scale.toLong())

    /**
     * Returns a force whose value is multiplied by the specified [scale].
     */
    public operator fun times(scale: Long): Force = Force(rawNanonewtons * scale)

    // endregion

    // region Force to Scalar Conversions

    public fun toDouble(unit: ForceUnit): Double {
        return rawNanonewtons.toDouble() / unit.nanonewtonScale
    }

    public fun toString(unit: ForceUnit, decimals: Int = 0): String = when {
        isInfinite() -> rawNanonewtons.toString()
        else -> buildString {
            append(toDouble(unit).toDecimalString(decimals))
            append(unit.symbol)
        }
    }

    public override fun toString(): String {
        val largestUnit = ForceUnit.International.entries.asReversed().firstOrNull { unit ->
            rawNanonewtons / unit.nanonewtonScale > 0
        }
        return toString(largestUnit ?: ForceUnit.International.Nanonewton, decimals = 2)
    }

    // endregion

    // region Comparisons

    public fun isInfinite(): Boolean = rawNanonewtons.isInfinite()

    public fun isFinite(): Boolean = rawNanonewtons.isFinite()

    override fun compareTo(other: Force): Int = rawNanonewtons.compareTo(other.rawNanonewtons)

    // endregion
}

// region Scalar to Force Conversions

public inline val Int.nanonewtons: Force get() = toForce(ForceUnit.International.Nanonewton)
public inline val Long.nanonewtons: Force get() = toForce(ForceUnit.International.Nanonewton)

public inline val Int.micronewtons: Force get() = toForce(ForceUnit.International.Micronewton)
public inline val Long.micronewtons: Force get() = toForce(ForceUnit.International.Micronewton)
public inline val Double.micronewtons: Force get() = toForce(ForceUnit.International.Micronewton)

public inline val Int.millinewtons: Force get() = toForce(ForceUnit.International.Millinewton)
public inline val Long.millinewtons: Force get() = toForce(ForceUnit.International.Millinewton)
public inline val Double.millinewtons: Force get() = toForce(ForceUnit.International.Millinewton)

public inline val Int.newtons: Force get() = toForce(ForceUnit.International.Newton)
public inline val Long.newtons: Force get() = toForce(ForceUnit.International.Newton)
public inline val Double.newtons: Force get() = toForce(ForceUnit.International.Newton)

public inline val Int.kilonewtons: Force get() = toForce(ForceUnit.International.Kilonewton)
public inline val Long.kilonewtons: Force get() = toForce(ForceUnit.International.Kilonewton)
public inline val Double.kilonewtons: Force get() = toForce(ForceUnit.International.Kilonewton)

public inline val Int.meganewtons: Force get() = toForce(ForceUnit.International.Meganewton)
public inline val Long.meganewtons: Force get() = toForce(ForceUnit.International.Meganewton)
public inline val Double.meganewtons: Force get() = toForce(ForceUnit.International.Meganewton)

public inline val Int.giganewtons: Force get() = toForce(ForceUnit.International.Giganewton)
public inline val Long.giganewtons: Force get() = toForce(ForceUnit.International.Giganewton)
public inline val Double.giganewtons: Force get() = toForce(ForceUnit.International.Giganewton)

public fun Int.toForce(unit: ForceUnit): Force = toLong().toForce(unit)

public fun Long.toForce(unit: ForceUnit): Force = Force(saturated * unit.nanonewtonScale)

public fun Double.toForce(unit: ForceUnit): Force {
    val valueInNanonewtons = this * unit.nanonewtonScale
    require(!valueInNanonewtons.isNaN()) { "Force value cannot be NaN." }
    return Force(valueInNanonewtons.roundToLong().saturated)
}

// endregion

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