package com.kaiqkt.commons.crypto.jwt

import io.jsonwebtoken.Claims
import java.util.Date

data class TokenDecrypted(
    val id: String,
    val sessionId: String,
    val authorities: List<String>,
    val expireAt: Date
) {

    @Suppress("UNCHECKED_CAST")
    constructor(claims: Claims) : this(
        id = claims.subject,
        sessionId = claims[SESSION_ID].toString(),
        authorities = claims[AUTHORITIES] as List<String>,
        expireAt = claims.expiration
    )
}
