package com.example.movieverse.data.model


import android.util.Log
import androidx.annotation.Keep
import com.example.movieverse.domain.model.MoviesModel
import com.google.gson.annotations.SerializedName

@Keep
data class MoviesResponse(
    val page: Int = 0,
    val results: List<Result> = emptyList(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
) {
    @Keep
    data class Result(
        @SerializedName("adult")
        val adult: Boolean = false,
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("backdrop_path")
        val backdropPath: String = "",
        @SerializedName("genre_ids")
        val genreIds: List<Int> = emptyList(),
        @SerializedName("original_language")
        val originalLanguage: String = "",
        @SerializedName("original_title")
        val originalTitle: String = "",
        @SerializedName("overview")
        val overview: String = "",
        @SerializedName("popularity")
        val popularity: Double = 0.0,
        @SerializedName("poster_path")
        val posterPath: String = "",
        @SerializedName("release_date")
        val releaseDate: String = "",
        @SerializedName("title")
        val title: String = "",
        @SerializedName("video")
        val video: Boolean = false,
        @SerializedName("vote_average")
        val voteAverage: Double = 0.0,
        @SerializedName("vote_count")
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