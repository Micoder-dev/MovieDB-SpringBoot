package com.micoder.sbdemo.controllers.user

import com.micoder.sbdemo.domain.dto.UserDto
import com.micoder.sbdemo.domain.model.ResponseModel
import com.micoder.sbdemo.domain.toCategoryDto
import com.micoder.sbdemo.domain.toMovieDto
import com.micoder.sbdemo.domain.toUserDto
import com.micoder.sbdemo.domain.toUserEntity
import com.micoder.sbdemo.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Base Url: /v1/api/user
 *
 * Endpoints:
 * - POST   /register                -> User registration
 * - GET    /category/list          -> List categories
 * - GET    /movie/listAll          -> List all movies
 * - GET    /movie/byCategory/{cat} -> List by category name
 * - GET    /movie/{id}             -> Movie details
 */
@RestController
@RequestMapping("/v1/api/user")
class UserController(
    private val userService: UserService
) {

    /** USER REGISTER **/
    @PostMapping("/registerUser")
    fun registerUser(@RequestBody userDto: UserDto): ResponseEntity<ResponseModel> {
        return try {
            val createdUser = userService.register(userDto.toUserEntity())
            ResponseEntity(
                ResponseModel(success = true, message = "User registered successfully", data = createdUser.toUserDto()),
                HttpStatus.CREATED
            )
        } catch (ex: IllegalArgumentException) {
            ResponseEntity(
                ResponseModel(success = false, message = ex.message ?: "Failed to register user"),
                HttpStatus.BAD_REQUEST
            )
        }
    }

    /** CATEGORY LIST **/
    @GetMapping("/categoryList")
    fun listCategories(): ResponseEntity<ResponseModel> {
        val categories = userService.listCategories().map { it.toCategoryDto() }
        return ResponseEntity(
            ResponseModel(success = true, message = "Categories retrieved successfully", data = categories),
            HttpStatus.OK
        )
    }

    /** MOVIE LIST (All) **/
    @GetMapping("/movieListAll")
    fun listAllMovies(): ResponseEntity<ResponseModel> {
        val movies = userService.listAllMovies().map { it.toMovieDto() }
        return ResponseEntity(
            ResponseModel(success = true, message = "Movies retrieved successfully", data = movies),
            HttpStatus.OK
        )
    }

    /** MOVIES BY CATEGORY NAME **/
    @GetMapping("/movieByCategory/{categoryName}")
    fun listMoviesByCategoryName(@PathVariable categoryName: String): ResponseEntity<ResponseModel> {
        val movies = userService.listMoviesByCategoryName(categoryName).map { it.toMovieDto() }
        return ResponseEntity(
            ResponseModel(success = true, message = "Movies for category '$categoryName' retrieved successfully", data = movies),
            HttpStatus.OK
        )
    }

    /** MOVIE DETAILS **/
    @GetMapping("/movieDetails/{id}")
    fun movieDetails(@PathVariable id: Long): ResponseEntity<ResponseModel> {
        val movie = userService.getMovieDetails(id)
        return movie?.let {
            ResponseEntity(
                ResponseModel(success = true, message = "Movie details retrieved successfully", data = it.toMovieDto()),
                HttpStatus.OK
            )
        } ?: ResponseEntity(
            ResponseModel(success = false, message = "Movie not found"),
            HttpStatus.NOT_FOUND
        )
    }
}