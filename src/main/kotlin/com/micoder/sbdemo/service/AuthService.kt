package com.micoder.sbdemo.service

import com.micoder.sbdemo.domain.entities.UserEntity

interface AuthService {

    fun login(username: String, password: String): UserEntity?

}