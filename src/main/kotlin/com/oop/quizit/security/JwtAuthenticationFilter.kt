package com.oop.quizit.security

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.SignatureException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.annotation.Resource
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter : OncePerRequestFilter() {
    @Value("\${jwt.header.string}")
    var HEADER_STRING: String? = null

    @Value("\${jwt.token.prefix}")
    var TOKEN_PREFIX: String? = null

    @Resource(name = "userAuthService")
    private val userDetailsService: UserDetailsService? = null

    @Autowired
    private val jwtTokenUtil: TokenProvider? = null
    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        val header = req.getHeader(HEADER_STRING)
        var username: String? = null
        var authToken: String? = null
        if (header != null && header.startsWith(TOKEN_PREFIX!!)) {
            authToken = header.replace(TOKEN_PREFIX!!, "")
            try {
                username = jwtTokenUtil!!.getUsernameFromToken(authToken)
            } catch (e: IllegalArgumentException) {
                logger.error("An error occurred while fetching Username from Token", e)
            } catch (e: ExpiredJwtException) {
                logger.warn("The token has expired", e)
            } catch (e: SignatureException) {
                logger.error("Authentication Failed. Username or Password not valid.")
            }
        } else {
            logger.warn("Couldn't find bearer string, header will be ignored")
        }
        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = userDetailsService!!.loadUserByUsername(username)
            if (jwtTokenUtil!!.validateToken(authToken, userDetails)) {
                val authentication = jwtTokenUtil.getAuthenticationToken(
                    authToken,
                    SecurityContextHolder.getContext().authentication,
                    userDetails
                )
                authentication.details = WebAuthenticationDetailsSource().buildDetails(req)
                logger.info("authenticated user $username, setting security context")
                SecurityContextHolder.getContext().authentication = authentication
            }
        }
        chain.doFilter(req, res)
    }
}