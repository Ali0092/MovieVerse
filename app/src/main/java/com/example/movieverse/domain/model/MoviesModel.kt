package com.example.movieverse.domain.model

import androidx.annotation.Keep

@Keep
data class MoviesModel(
    val page: Int = 0,
    val results: List<Result> = emptyList()
) {
    @Keep
    data class Result(
        val id: Int = 0,
        val adult: Boolean = false,
        val backdropPath: String = "",
        val originalTitle: String = "",
        val overview: String = "",
        val popularity: Double = 0.0,
        val posterPath: String = "",
        val releaseDate: String = "",
        val title: String = "",
        val voteAverage: Double = 0.0,
        val voteCount: Int = 0
    )
}