package com.micoder.sbdemo.service.api

import com.micoder.sbdemo.domain.entities.CategoryEntity
import com.micoder.sbdemo.domain.entities.MovieEntity
import com.micoder.sbdemo.domain.entities.UserEntity

/** -------------------------------------------
 *  ADMIN SERVICE
 *  -------------------------------------------
 */
interface AdminService {
    fun listAllUsers(): List<UserEntity>
    fun getUser(id: Long): UserEntity?
    fun createUser(user: UserEntity): UserEntity
    fun updateUser(id: Long, user: UserEntity): UserEntity
    fun deleteUser(id: Long)

    // Category management
    fun listAllCategories(): List<CategoryEntity>
    fun createCategory(category: CategoryEntity): CategoryEntity
    fun updateCategory(id: Long, category: CategoryEntity): CategoryEntity
    fun deleteCategory(id: Long)

    // Movie management
    fun listAllMovies(): List<MovieEntity>
    fun createMovie(movie: MovieEntity): MovieEntity
    fun updateMovie(id: Long, movie: MovieEntity): MovieEntity
    fun deleteMovie(id: Long)
}