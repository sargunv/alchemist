package io.github.kevincianfarini.alchemist.internal

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import platform.posix.sprintf

@OptIn(ExperimentalForeignApi::class)
internal actual fun Double.toDecimalString(decimals: Int): String = memScoped {
    val buff = allocArray<ByteVar>(Double.SIZE_BYTES * 2)
    sprintf(buff, "%.${decimals}f", this@toDecimalString)
    buff.toKString()
}