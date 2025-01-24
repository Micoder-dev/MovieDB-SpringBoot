package com.micoder.sbdemo.service.api.impl

import com.micoder.sbdemo.domain.entities.CategoryEntity
import com.micoder.sbdemo.domain.entities.MovieEntity
import com.micoder.sbdemo.domain.entities.UserEntity
import com.micoder.sbdemo.repository.CategoryRepository
import com.micoder.sbdemo.repository.MovieRepository
import com.micoder.sbdemo.repository.UserRepository
import com.micoder.sbdemo.service.api.AdminService
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AdminServiceImpl(
    private val userRepository: UserRepository,
    private val categoryRepository: CategoryRepository,
    private val movieRepository: MovieRepository,
    private val passwordEncoder: PasswordEncoder
) : AdminService {

    /** USER management **/
    override fun listAllUsers(): List<UserEntity> {
        return userRepository.findAll()
    }

    override fun getUser(id: Long): UserEntity? {
        return userRepository.findById(id).orElse(null)
    }

    override fun createUser(user: UserEntity): UserEntity {
        if (userRepository.findByUserName(user.userName) != null) {
            throw IllegalArgumentException("Username already exists")
        }
        val hashedUser = user.copy(password = passwordEncoder.encode(user.password))
        return userRepository.save(hashedUser)
    }

    @Transactional
    override fun updateUser(id: Long, user: UserEntity): UserEntity {
        val existing = userRepository.findById(id).orElseThrow { IllegalArgumentException("User not found") }
        val updated = existing.copy(
            userName = user.userName,
            password = passwordEncoder.encode(user.password),
            age = user.age,
            image = user.image,
            role = user.role
        )
        return userRepository.save(updated)
    }

    override fun deleteUser(id: Long) {
        if (!userRepository.existsById(id)) {
            throw IllegalArgumentException("User not found")
        }
        userRepository.deleteById(id)
    }

    /** CATEGORY management **/
    override fun listAllCategories(): List<CategoryEntity> {
        return categoryRepository.findAll()
    }

    override fun createCategory(category: CategoryEntity): CategoryEntity {
        if (categoryRepository.findByCategoryName(category.categoryName) != null) {
            throw IllegalArgumentException("Category already exists")
        }
        return categoryRepository.save(category)
    }

    @Transactional
    override fun updateCategory(id: Long, category: CategoryEntity): CategoryEntity {
        val existing = categoryRepository.findById(id).orElseThrow { IllegalArgumentException("Category not found") }
        val updated = existing.copy(
            categoryName = category.categoryName,
            categoryImage = category.categoryImage
        )
        return categoryRepository.save(updated)
    }

    override fun deleteCategory(id: Long) {
        if (!categoryRepository.existsById(id)) {
            throw IllegalArgumentException("Category not found")
        }
        categoryRepository.deleteById(id)
    }

    /** MOVIE management **/
    override fun listAllMovies(): List<MovieEntity> {
        return movieRepository.findAll()
    }

    override fun createMovie(movie: MovieEntity): MovieEntity {
        if (movieRepository.findByMovieName(movie.movieName) != null) {
            throw IllegalArgumentException("Movie name already exists")
        }
        return movieRepository.save(movie)
    }

    @Transactional
    override fun updateMovie(id: Long, movie: MovieEntity): MovieEntity {
        val existing = movieRepository.findById(id).orElseThrow { IllegalArgumentException("Movie not found") }
        val updated = existing.copy(
            movieCategoryName = movie.movieCategoryName,
            movieName = movie.movieName,
            movieDescription = movie.movieDescription,
            moviePoster = movie.moviePoster,
            movieTarget = movie.movieTarget,
            movieUrl = movie.movieUrl
        )
        return movieRepository.save(updated)
    }

    override fun deleteMovie(id: Long) {
        if (!movieRepository.existsById(id)) {
            throw IllegalArgumentException("Movie not found")
        }
        movieRepository.deleteById(id)
    }
}