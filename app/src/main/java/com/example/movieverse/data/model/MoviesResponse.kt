package com.example.movieverse.data.model


import androidx.annotation.Keep
import com.example.movieverse.domain.model.MoviesModel

@Keep
data class MoviesResponse(
    val page: Int,
    val results: List<Result>,
    val totalPages: Int,
    val totalResults: Int
) {
    @Keep
    data class Result(
        val adult: Boolean,
        val id: Int,
        val backdropPath: String,
        val genreIds: List<Int>,
        val originalLanguage: String,
        val originalTitle: String,
        val overview: String,
        val popularity: Double,
        val posterPath: String,
        val releaseDate: String,
        val title: String,
        val video: Boolean,
        val voteAverage: Double,
        val voteCount: Int
    )
}

fun MoviesResponse.Result.toMoviesModel(): MoviesModel.Result {

    return MoviesModel.Result(
        id = this.id,
        adult = this.adult,
        backdropPath = this.backdropPath,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )

}