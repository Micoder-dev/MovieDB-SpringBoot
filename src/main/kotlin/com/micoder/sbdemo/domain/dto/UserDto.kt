package com.micoder.sbdemo.domain.dto

import com.micoder.sbdemo.domain.Role

data class UserDto(
    val id: Long? = null,
    val userName: String,
    val password: String,
    val age: Int,
    val image: String? = null,
    val role: Role = Role.USER
)