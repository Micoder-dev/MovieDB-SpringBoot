package com.micoder.sbdemo.domain.entities

import jakarta.persistence.*

/** CATEGORY ENTITY & DTO **/
@Entity
@Table(name = "tbl_category")
data class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "category_name", unique = true)
    val categoryName: String,

    @Column(name = "category_image")
    val categoryImage: String
)