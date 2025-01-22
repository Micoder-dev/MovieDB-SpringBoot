package com.micoder.sbdemo.security

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component


data class ErrorResponse(
    val status: Int,
    val message: String
)

@Component
class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        val error = ErrorResponse(
            status = HttpStatus.UNAUTHORIZED.value(),
            message = "Unauthorized: ${authException.message}"
        )
        ObjectMapper().writeValue(response.outputStream, error)
    }
}

@Component
class CustomAccessDeniedHandler : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        response.status = HttpStatus.FORBIDDEN.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        val error = ErrorResponse(
            status = HttpStatus.FORBIDDEN.value(),
            message = "Forbidden: ${accessDeniedException.message}"
        )
        ObjectMapper().writeValue(response.outputStream, error)
    }
}
