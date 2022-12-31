package com.kaiqkt.commons.crypto.jwt

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

const val SESSION_ID = "session_id"
const val AUTHORITIES = "authorities"

object JWTUtils {

    fun generateToken(
        id: String,
        secret: String,
        authorities: List<String>,
        sessionId: String,
        expirationHours: Long
    ): String {
        val zoneDateTime = LocalDateTime.now().plusHours(expirationHours).atZone(ZoneId.systemDefault())
        val expiration = Date.from(zoneDateTime.toInstant())

        return Jwts.builder()
            .setSubject(id)
            .setIssuer("Kaiqkt")
            .claim(SESSION_ID, sessionId)
            .claim(AUTHORITIES, authorities)
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
            .compact()
    }

    fun getClaims(token: String, secret: String): TokenDecrypted {
        return try {
            val claims = Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).body
            TokenDecrypted(claims, false)
        } catch (e: ExpiredJwtException) {
            TokenDecrypted(e.claims, true)
        }
    }
}