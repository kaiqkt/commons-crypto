package com.kaiqkt.commons.crypto.jwt

import io.jsonwebtoken.Claims
import java.util.Date

data class TokenDecrypted(
    val id: String,
    val sessionId: String,
    val authorities: List<String>,
    val expireAt: Date,
    val expired: Boolean
) {

    @Suppress("UNCHECKED_CAST")
    constructor(claims: Claims, expired: Boolean) : this(
        id = claims.subject,
        sessionId = claims[SESSION_ID].toString(),
        authorities = claims[AUTHORITIES] as List<String>,
        expireAt = claims.expiration,
        expired = expired
    )
}
