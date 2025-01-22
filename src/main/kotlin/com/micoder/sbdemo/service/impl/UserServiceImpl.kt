package com.micoder.sbdemo.service.impl

import com.micoder.sbdemo.domain.entities.CategoryEntity
import com.micoder.sbdemo.domain.entities.MovieEntity
import com.micoder.sbdemo.domain.entities.UserEntity
import com.micoder.sbdemo.repository.CategoryRepository
import com.micoder.sbdemo.repository.MovieRepository
import com.micoder.sbdemo.repository.UserRepository
import com.micoder.sbdemo.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val categoryRepository: CategoryRepository,
    private val movieRepository: MovieRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService {

    override fun register(user: UserEntity): UserEntity {
        // Ensure the userName is not already used
        if (userRepository.findByUserName(user.userName) != null) {
            throw IllegalArgumentException("Username already exists")
        }
        val hashedUser = user.copy(password = passwordEncoder.encode(user.password))
        return userRepository.save(hashedUser)
    }

    override fun listCategories(): List<CategoryEntity> {
        return categoryRepository.findAll()
    }

    override fun listAllMovies(): List<MovieEntity> {
        return movieRepository.findAll()
    }

    override fun listMoviesByCategoryName(categoryName: String): List<MovieEntity> {
        return movieRepository.findByMovieCategoryName(categoryName)
    }

    override fun getMovieDetails(id: Long): MovieEntity? {
        return movieRepository.findById(id).orElse(null)
    }
}