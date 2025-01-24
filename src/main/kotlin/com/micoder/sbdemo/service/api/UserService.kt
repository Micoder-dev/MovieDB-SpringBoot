package com.micoder.sbdemo.service.api

import com.micoder.sbdemo.domain.entities.CategoryEntity
import com.micoder.sbdemo.domain.entities.MovieEntity
import com.micoder.sbdemo.domain.entities.UserEntity

/** -------------------------------------------
 *  USER SERVICE
 *  -------------------------------------------
 */
interface UserService {
    fun register(user: UserEntity): UserEntity
    fun listCategories(): List<CategoryEntity>
    fun listAllMovies(): List<MovieEntity>
    fun listMoviesByCategoryName(categoryName: String): List<MovieEntity>
    fun getMovieDetails(id: Long): MovieEntity?
}