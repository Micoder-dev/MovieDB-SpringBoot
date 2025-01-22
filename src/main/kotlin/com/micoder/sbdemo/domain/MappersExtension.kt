package com.micoder.sbdemo.domain

import com.micoder.sbdemo.domain.dto.CategoryDto
import com.micoder.sbdemo.domain.dto.MovieDto
import com.micoder.sbdemo.domain.dto.UserDto
import com.micoder.sbdemo.domain.entities.CategoryEntity
import com.micoder.sbdemo.domain.entities.MovieEntity
import com.micoder.sbdemo.domain.entities.UserEntity

// Category Mappers
fun CategoryEntity.toCategoryDto(): CategoryDto = CategoryDto(
    id = this.id,
    categoryName = this.categoryName,
    categoryImage = this.categoryImage
)

fun CategoryDto.toCategoryEntity(): CategoryEntity = CategoryEntity(
    id = this.id,
    categoryName = this.categoryName,
    categoryImage = this.categoryImage
)

// Movie Mappers
fun MovieEntity.toMovieDto(): MovieDto = MovieDto(
    id = this.id,
    movieCategoryName = this.movieCategoryName,
    movieName = this.movieName,
    movieDescription = this.movieDescription,
    moviePoster = this.moviePoster,
    movieTarget = this.movieTarget,
    movieUrl = this.movieUrl
)

fun MovieDto.toMovieEntity(): MovieEntity = MovieEntity(
    id = this.id,
    movieCategoryName = this.movieCategoryName,
    movieName = this.movieName,
    movieDescription = this.movieDescription,
    moviePoster = this.moviePoster,
    movieTarget = this.movieTarget,
    movieUrl = this.movieUrl
)

// User Mappers
fun UserEntity.toUserDto(): UserDto = UserDto(
    id = this.id,
    userName = this.userName,
    password = this.password,
    age = this.age,
    image = this.image,
    role = this.role
)

fun UserDto.toUserEntity(): UserEntity = UserEntity(
    id = this.id,
    userName = this.userName,
    password = this.password,
    age = this.age,
    image = this.image,
    role = this.role
)