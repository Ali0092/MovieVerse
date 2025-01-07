package com.example.movieverse.domain.model

import androidx.annotation.Keep

@Keep
data class MoviesModel(
    val page: Int,
    val results: List<Result>
) {
    @Keep
    data class Result(
        val id: Int,
        val adult: Boolean,
        val backdropPath: String,
        val originalTitle: String,
        val overview: String,
        val popularity: Double,
        val posterPath: String,
        val releaseDate: String,
        val title: String,
        val voteAverage: Double,
        val voteCount: Int
    )
}