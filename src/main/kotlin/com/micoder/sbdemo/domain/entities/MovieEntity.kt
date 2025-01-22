package com.micoder.sbdemo.domain.entities

import jakarta.persistence.*

/** MOVIE ENTITY & DTO **/
// Demonstrates a unidirectional relationship to Category if needed, or simply store categoryId
@Entity
@Table(name = "tbl_movie")
data class MovieEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    // Example relationship:
    // @ManyToOne
    // @JoinColumn(name = "category_id")
    // val category: CategoryEntity? = null,

    // or store the category name / id directly:
    @Column(name = "movie_category_name")
    val movieCategoryName: String,

    @Column(name = "movie_name")
    val movieName: String,

    @Column(name = "movie_description")
    val movieDescription: String,

    @Column(name = "movie_poster")
    val moviePoster: String,

    @Column(name = "movie_target") // Could be an age or rating
    val movieTarget: String,

    @Column(name = "movie_url")
    val movieUrl: String
)