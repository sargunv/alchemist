package io.github.kevincianfarini.alchemist.internal

/**
 * Ensures that we don't inline the exception throwing instructions and instead make the jump
 * to this function. This helps avoid busting the instruction cache and localizes exception throwing
 * instructions to one place.
 *
 * TODO: Supply a ProGuard rule to keep this method so it's not inlined
 */
internal fun throwIllegalArgumentException(message: String): Nothing {
    throw IllegalArgumentException(message)
}
