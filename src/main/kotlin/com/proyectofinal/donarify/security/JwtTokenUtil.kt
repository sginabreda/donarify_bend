package com.proyectofinal.donarify.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.Date
import java.util.function.Function
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class JwtTokenUtil(
    @Value("\${jwt.secret}")
    private val secret: String
) {

    private val jwtTokenValidity = 5 * 60 * 60
    private val rolesClaim = "role"

    fun getUsernameFromToken(token: String?): String {
        return getClaimFromToken(
            token,
            Function { obj: Claims -> obj.subject }
        )
    }

    fun getExpirationDateFromToken(token: String?): Date {
        return getClaimFromToken(
            token,
            Function { obj: Claims -> obj.expiration }
        )
    }

    fun <T> getClaimFromToken(token: String?, claimsResolver: Function<Claims, T>): T {
        val claims = getAllClaimsFromToken(token)
        return claimsResolver.apply(claims)
    }

    private fun getAllClaimsFromToken(token: String?): Claims {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    fun generateToken(userDetails: UserDetails): String {
        val claims: Map<String, Any> = HashMap()
        return doGenerateToken(claims, userDetails)
    }

    private fun doGenerateToken(
        claims: Map<String, Any>,
        userDetails: UserDetails
    ): String {
        return Jwts.builder().setClaims(claims).setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + jwtTokenValidity * 1000))
            .claim("role", listOf(userDetails.authorities.first().authority))
            .signWith(SignatureAlgorithm.HS512, secret).compact()
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = getUsernameFromToken(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    fun getAuthentication(token: String, userDetails: UserDetails): UsernamePasswordAuthenticationToken {
        val claims = getAllClaimsFromToken(token)

        val authorities = (claims[rolesClaim] as List<String>)
            .map { SimpleGrantedAuthority(it) }

        return UsernamePasswordAuthenticationToken(userDetails, "", authorities)
    }
}
