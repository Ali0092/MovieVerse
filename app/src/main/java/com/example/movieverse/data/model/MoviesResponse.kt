package com.example.movieverse.data.model


import androidx.annotation.Keep
import com.example.movieverse.domain.model.MoviesModel

@Keep
data class MoviesResponse(
    val page: Int = 0,
    val results: List<Result> = emptyList(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
) {
    @Keep
    data class Result(
        val adult: Boolean = false,
        val id: Int = 0,
        val backdropPath: String = "",
        val genreIds: List<Int> = emptyList(),
        val originalLanguage: String = "",
        val originalTitle: String = "",
        val overview: String = "",
        val popularity: Double = 0.0,
        val posterPath: String = "",
        val releaseDate: String = "",
        val title: String = "",
        val video: Boolean = false,
        val voteAverage: Double = 0.0,
        val voteCount: Int = 0
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