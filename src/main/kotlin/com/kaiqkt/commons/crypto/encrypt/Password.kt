package com.kaiqkt.commons.crypto.encrypt

data class Password(
    val hash: String,
    val salt: String
)