package com.micoder.sbdemo.domain.entities

import com.micoder.sbdemo.domain.Role
import jakarta.persistence.*

/** USER ENTITY & DTO **/
@Entity
@Table(name = "tbl_user")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true, name = "user_name")
    val userName: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "age")
    val age: Int,

    @Column(name = "image")
    val image: String? = null,

    @Enumerated(EnumType.STRING)
    val role: Role = Role.USER
) {
    constructor() : this(
        id = null,
        userName = "",
        password = "",
        age = 0,
        image = null,
        role = Role.USER
    )
}