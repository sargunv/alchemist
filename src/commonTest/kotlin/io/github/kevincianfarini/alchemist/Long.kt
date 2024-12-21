package io.github.kevincianfarini.alchemist

/**
 * Convert this [Long] to its binary string representation. The result is chunked into 8 bytes. This function is marked
 * as unused, but it's useful for debugging.
 *
 * The following example:
 *
 * ```kt
 * println(Long.MAX_VALUE.toBinaryString())
 * println(Long.MIN_VALUE.toBinaryString())
 * ```
 *
 * will print the following results:
 *
 * ```
 * 01111111 11111111 11111111 11111111 11111111 11111111 11111111 11111111
 * 10000000 00000000 00000000 00000000 00000000 00000000 00000000 00000000
 * ```
 */
@Suppress("unused")
fun Long.toBinaryString(): String = toULong()
    .toString(radix = 2)
    .padStart(64, '0')
    .chunked(8)
    .joinToString(" ")