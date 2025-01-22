package com.micoder.sbdemo.security

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtUtil(
    @Value("\${jwt.secret}") private val secret: String,
    @Value("\${jwt.expirationMs}") private val expirationMs: Long
) {

    private val key: Key = Keys.hmacShaKeyFor(secret.toByteArray())

    fun generateToken(username: String, roles: List<String>): String {
        val claims: Claims = Jwts.claims().setSubject(username)
        claims["roles"] = roles

        val now = Date()
        val expiryDate = Date(now.time + expirationMs)

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun getUsernameFromToken(token: String): String {
        return parseClaims(token).subject
    }

    @Suppress("UNCHECKED_CAST")
    fun getRolesFromToken(token: String): List<String> {
        val claims = parseClaims(token)
        return claims["roles"] as List<String>
    }

    fun validateToken(token: String): Boolean {
        return try {
            parseClaims(token)
            true
        } catch (ex: JwtException) {
            false
        } catch (ex: IllegalArgumentException) {
            false
        }
    }

    private fun parseClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
    }
}
