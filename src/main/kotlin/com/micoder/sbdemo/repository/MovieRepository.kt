package com.micoder.sbdemo.repository

import com.micoder.sbdemo.domain.entities.MovieEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository : JpaRepository<MovieEntity, Long> {
    fun findByMovieName(movieName: String): MovieEntity?
    fun findByMovieCategoryName(category: String): List<MovieEntity>
}