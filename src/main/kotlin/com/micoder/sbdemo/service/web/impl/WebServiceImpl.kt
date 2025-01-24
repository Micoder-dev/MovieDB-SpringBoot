package com.micoder.sbdemo.service.web.impl

import com.micoder.sbdemo.config.user.CustomUserDetails
import com.micoder.sbdemo.repository.AuthRepository
import com.micoder.sbdemo.service.web.WebService
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class WebServiceImpl(
    private val authRepository: AuthRepository
): WebService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = authRepository.findByUserName(username) ?: throw UsernameNotFoundException("User not found: $username")

        authRepository.save(user)

        val authorities: Collection<GrantedAuthority> = listOf(
            SimpleGrantedAuthority("ROLE_${user.role.name}")
        )

        return CustomUserDetails(
            username = user.userName,
            password = user.password,
            age = user.age,
            role = user.role,
            authorities = authorities
        )
    }

}