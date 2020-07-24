package com.proyectofinal.donarify.security

import com.proyectofinal.donarify.context.HeaderType
import com.proyectofinal.donarify.logger
import com.proyectofinal.donarify.service.UserService
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.annotation.Priority
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Priority(1)
@Component
class JwtFilter(
    private val jwtTokenUtil: JwtTokenUtil
) : OncePerRequestFilter() {

    private val log by logger()

    @Autowired
    private lateinit var userService: UserService

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        var token = request.getHeader(HeaderType.AUTHORIZATION.label)

        var username: String? = null

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7)
            try {
                username = jwtTokenUtil.getUsernameFromToken(token)
            } catch (e: IllegalArgumentException) {
                log.error("Unable to get JWT Token")
            } catch (e: ExpiredJwtException) {
                log.error("JWT Token has expired")
            }
        } else {
            log.warn("JWT Token does not begin with Bearer String")
        }

        if (username != null && SecurityContextHolder.getContext().authentication == null) {

            val userDetails = this.userService.loadUserByUsername(username)

            if (jwtTokenUtil.validateToken(token, userDetails)) {
                val usernamePasswordAuthenticationToken = jwtTokenUtil.getAuthentication(token, userDetails)

                usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }
        }
        filterChain.doFilter(request, response)
    }
}
