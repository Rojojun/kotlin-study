package com.rojojun.kopring.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {
    @Value("\${jwt.secret}")
    private val secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512)
    private val jwtExpirationInMinutes: Long = 1000 * 60 * 60 * 24 // 24H

    fun generateToken(username: String): String {
        val claims = Jwts.claims().setSubject(username)
        val now = Date();
        val expiryDate = Date(now.time + jwtExpirationInMinutes)

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(secretKey)
            .compact()
    }

    fun getUsernameFromToken(token: String): String? {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJwt(token)
            .body
            .subject
    }

    fun validateToken(token: String?): Boolean {
        val claims = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body

        return !claims.expiration.before(Date())
    }
}