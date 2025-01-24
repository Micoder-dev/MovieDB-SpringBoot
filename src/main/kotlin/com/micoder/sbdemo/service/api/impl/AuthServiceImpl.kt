package com.micoder.sbdemo.service.api.impl

import com.micoder.sbdemo.domain.entities.UserEntity
import com.micoder.sbdemo.repository.AuthRepository
import com.micoder.sbdemo.service.api.AuthService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val authRepository: AuthRepository,
    private val passwordEncoder: PasswordEncoder
): AuthService {

    override fun login(username: String, password: String): UserEntity? {
        val user = authRepository.findByUserName(username) ?: throw UsernameNotFoundException("Username or Password wrong")
        if (!passwordEncoder.matches(password, user.password)) {
            throw UsernameNotFoundException("Username or Password wrong")
        } else {
            return user
        }
    }

}