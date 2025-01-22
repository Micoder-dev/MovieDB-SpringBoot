package com.micoder.sbdemo.repository

import com.micoder.sbdemo.domain.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByUserName(username: String): UserEntity?
}