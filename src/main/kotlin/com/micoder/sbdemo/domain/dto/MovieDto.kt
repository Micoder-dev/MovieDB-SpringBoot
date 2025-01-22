package com.micoder.sbdemo.domain.dto

data class MovieDto(
    val id: Long? = null,
    val movieCategoryName: String,
    val movieName: String,
    val movieDescription: String,
    val moviePoster: String,
    val movieTarget: String,
    val movieUrl: String
)