package com.micoder.sbdemo.service.api

import com.micoder.sbdemo.domain.entities.UserEntity

interface AuthService {

    fun login(username: String, password: String): UserEntity?

}