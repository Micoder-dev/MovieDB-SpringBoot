package com.micoder.sbdemo.controllers.api.admin

import com.micoder.sbdemo.domain.*
import com.micoder.sbdemo.domain.dto.CategoryDto
import com.micoder.sbdemo.domain.dto.MovieDto
import com.micoder.sbdemo.domain.dto.UserDto
import com.micoder.sbdemo.domain.model.ResponseModel
import com.micoder.sbdemo.service.api.AdminService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Base Url: /v1/api/admin
 *
 * Endpoints:
 * - GET    /user                 -> List all users
 * - GET    /user/{id}            -> Get single user
 * - POST   /user                 -> Create user (by admin)
 * - PUT    /user/{id}            -> Update user
 * - DELETE /user/{id}            -> Delete user
 *
 * - GET    /category             -> List categories
 * - POST   /category             -> Create category
 * - PUT    /category/{id}        -> Update category
 * - DELETE /category/{id}        -> Delete category
 *
 * - GET    /movie                -> List movies
 * - POST   /movie                -> Create movie
 * - PUT    /movie/{id}           -> Update movie
 * - DELETE /movie/{id}           -> Delete movie
 */
@RestController
@RequestMapping("/v1/api/admin")
class AdminController(
    private val adminService: AdminService
) {

    // ---------------- USER Management by Admin ----------------
    @GetMapping("/userList")
    fun listAllUsers(): ResponseEntity<ResponseModel> {
        val users = adminService.listAllUsers().map { it.toUserDto() }
        return ResponseEntity.ok(ResponseModel(success = true, data = users, message = "Users fetched successfully"))
    }

    @GetMapping("/userDetails/{id}")
    fun getUserDetails(@PathVariable id: Long): ResponseEntity<ResponseModel> {
        val user = adminService.getUser(id)
        return user?.let {
            ResponseEntity.ok(ResponseModel(success = true, data = it.toUserDto(), message = "User fetched successfully"))
        } ?: ResponseEntity(ResponseModel(success = false, message = "User not found"), HttpStatus.NOT_FOUND)
    }

    @PostMapping("/createUser")
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<ResponseModel> {
        return try {
            val created = adminService.createUser(userDto.toUserEntity())
            ResponseEntity.ok(ResponseModel(success = true, data = created.toUserDto(), message = "User created successfully"))
        } catch (ex: Exception) {
            ResponseEntity(ResponseModel(success = false, message = "Failed to create user: ${ex.message}"), HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/updateUser/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody userDto: UserDto
    ): ResponseEntity<ResponseModel> {
        return try {
            val updated = adminService.updateUser(id, userDto.toUserEntity())
            ResponseEntity.ok(ResponseModel(success = true, data = updated.toUserDto(), message = "User updated successfully"))
        } catch (ex: Exception) {
            ResponseEntity(ResponseModel(success = false, message = "Failed to update user: ${ex.message}"), HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<ResponseModel> {
        return try {
            adminService.deleteUser(id)
            ResponseEntity.ok(ResponseModel(success = true, message = "User deleted successfully"))
        } catch (ex: Exception) {
            ResponseEntity(ResponseModel(success = false, message = "Failed to delete user: ${ex.message}"), HttpStatus.NOT_FOUND)
        }
    }

    // ---------------- CATEGORY Management by Admin ----------------
    @GetMapping("/categoryList")
    fun listAllCategories(): ResponseEntity<ResponseModel> {
        val categories = adminService.listAllCategories().map { it.toCategoryDto() }
        return ResponseEntity.ok(ResponseModel(success = true, data = categories, message = "Categories fetched successfully"))
    }

    @PostMapping("/createCategory")
    fun createCategory(@RequestBody categoryDto: CategoryDto): ResponseEntity<ResponseModel> {
        return try {
            val created = adminService.createCategory(categoryDto.toCategoryEntity())
            ResponseEntity.ok(ResponseModel(success = true, data = created.toCategoryDto(), message = "Category created successfully"))
        } catch (ex: Exception) {
            ResponseEntity(ResponseModel(success = false, message = "Failed to create category: ${ex.message}"), HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/updateCategory/{id}")
    fun updateCategory(
        @PathVariable id: Long,
        @RequestBody categoryDto: CategoryDto
    ): ResponseEntity<ResponseModel> {
        return try {
            val updated = adminService.updateCategory(id, categoryDto.toCategoryEntity())
            ResponseEntity.ok(ResponseModel(success = true, data = updated.toCategoryDto(), message = "Category updated successfully"))
        } catch (ex: Exception) {
            ResponseEntity(ResponseModel(success = false, message = "Failed to update category: ${ex.message}"), HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping("/deleteCategory/{id}")
    fun deleteCategory(@PathVariable id: Long): ResponseEntity<ResponseModel> {
        return try {
            adminService.deleteCategory(id)
            ResponseEntity.ok(ResponseModel(success = true, message = "Category deleted successfully"))
        } catch (ex: Exception) {
            ResponseEntity(ResponseModel(success = false, message = "Failed to delete category: ${ex.message}"), HttpStatus.NOT_FOUND)
        }
    }

    // ---------------- MOVIE Management by Admin ----------------
    @GetMapping("/movieList")
    fun listAllMovies(): ResponseEntity<ResponseModel> {
        val movies = adminService.listAllMovies().map { it.toMovieDto() }
        return ResponseEntity.ok(ResponseModel(success = true, data = movies, message = "Movies fetched successfully"))
    }

    @PostMapping("/createMovie")
    fun createMovie(@RequestBody movieDto: MovieDto): ResponseEntity<ResponseModel> {
        return try {
            val created = adminService.createMovie(movieDto.toMovieEntity())
            ResponseEntity.ok(ResponseModel(success = true, data = created.toMovieDto(), message = "Movie created successfully"))
        } catch (ex: Exception) {
            ResponseEntity(ResponseModel(success = false, message = "Failed to create movie: ${ex.message}"), HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/updateMovie/{id}")
    fun updateMovie(
        @PathVariable id: Long,
        @RequestBody movieDto: MovieDto
    ): ResponseEntity<ResponseModel> {
        return try {
            val updated = adminService.updateMovie(id, movieDto.toMovieEntity())
            ResponseEntity.ok(ResponseModel(success = true, data = updated.toMovieDto(), message = "Movie updated successfully"))
        } catch (ex: Exception) {
            ResponseEntity(ResponseModel(success = false, message = "Failed to update movie: ${ex.message}"), HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping("/deleteMovie/{id}")
    fun deleteMovie(@PathVariable id: Long): ResponseEntity<ResponseModel> {
        return try {
            adminService.deleteMovie(id)
            ResponseEntity.ok(ResponseModel(success = true, message = "Movie deleted successfully"))
        } catch (ex: Exception) {
            ResponseEntity(ResponseModel(success = false, message = "Failed to delete movie: ${ex.message}"), HttpStatus.NOT_FOUND)
        }
    }
}