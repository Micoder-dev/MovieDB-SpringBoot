package com.micoder.sbdemo.repository

import com.micoder.sbdemo.domain.Role
import com.micoder.sbdemo.domain.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdminRepository : JpaRepository<UserEntity, Long> {
    fun findByRole(role: Role): List<UserEntity>
}