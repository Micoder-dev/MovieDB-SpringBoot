package com.micoder.sbdemo.config.database

import com.micoder.sbdemo.domain.Role
import com.micoder.sbdemo.domain.entities.UserEntity
import com.micoder.sbdemo.repository.AdminRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class DatabaseInitializer {

    @Bean
    fun initDatabase(
        adminRepository: AdminRepository,
        passwordEncoder: PasswordEncoder
    ): CommandLineRunner {
        return CommandLineRunner {
            // Check if there's any ADMIN in the DB
            val admins = adminRepository.findByRole(Role.ADMIN)
            if (admins.isEmpty()) {
                // Insert default admin
                adminRepository.save(
                    UserEntity(
                        id = null,
                        userName = "admin",
                        password = passwordEncoder.encode("admin"),
                        role = Role.ADMIN,
                        age = 23,
                        image = "admin.jpg"
                    )
                )
            }
        }
    }
}