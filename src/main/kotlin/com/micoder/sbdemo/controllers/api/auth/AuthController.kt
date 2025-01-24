package com.micoder.sbdemo.controllers.api.auth

import com.micoder.sbdemo.domain.model.LoginRequest
import com.micoder.sbdemo.domain.model.ResponseModel
import com.micoder.sbdemo.security.JwtUtil
import com.micoder.sbdemo.service.api.AuthService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Base Url: /v1/api
 *
 * Endpoints:
 * - POST   /login                  -> login
 */
@RestController
@RequestMapping("/v1/api/auth")
class AuthController(
    private val authService: AuthService,
    private val jwtUtil: JwtUtil
) {

    /** LOGIN (JWT-based) **/
    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<ResponseModel> {
        val login = authService.login(loginRequest.username, loginRequest.password)
        return if (login != null) {
            // Generate JWT token
            val roles = listOf(login.role.name)
            val token = jwtUtil.generateToken(loginRequest.username, roles)

            // Create HTTP-only cookie with the JWT token
            val responseCookie = ResponseCookie.from("JWT_TOKEN", token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(86400)
                .sameSite("Strict")
                .build()

            ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(ResponseModel(success = true, message = "Login successful"))
        } else {
            ResponseEntity(ResponseModel(success = false, message = "Login failed"), HttpStatus.UNAUTHORIZED)
        }
    }

}