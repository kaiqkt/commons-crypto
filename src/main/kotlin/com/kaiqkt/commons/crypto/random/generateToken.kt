package com.kaiqkt.commons.crypto.random

private const val REFRESH_TOKEN_MAX_LENGTH = 40
private const val REFRESH_TOKEN_MIN_LENGTH = 30
private val REFRESH_TOKEN_ALLOWED_CHARS = ('A'..'Z') + ('a'..'z') + ('0'..'9')

fun generateRandomString(): String {
    return (REFRESH_TOKEN_MIN_LENGTH..REFRESH_TOKEN_MAX_LENGTH)
        .map { REFRESH_TOKEN_ALLOWED_CHARS.random() }
        .joinToString("")
}
