package com.notableFactory.marvelapp.utils

import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

object ApiUtils {

    fun String.md5(): ByteArray = MessageDigest.getInstance("MD5").digest(this.toByteArray(UTF_8))

    fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }
}