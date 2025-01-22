package com.micoder.sbdemo.repository

import com.micoder.sbdemo.domain.entities.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<CategoryEntity, Long> {
    fun findByCategoryName(name: String): CategoryEntity?
}