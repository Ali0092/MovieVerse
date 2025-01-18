package com.example.movieverse.data.model


import androidx.annotation.Keep
import com.example.movieverse.domain.model.MoviesModel
import com.google.gson.annotations.SerializedName

@Keep
data class TVShowResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
    @Keep
    data class Result(
        @SerializedName("adult")
        val adult: Boolean,
        @SerializedName("backdrop_path")
        val backdropPath: String,
        @SerializedName("first_air_date")
        val firstAirDate: String,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("origin_country")
        val originCountry: List<String>,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_name")
        val originalName: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int
    )
}

fun TVShowResponse.Result.toMoviesModel(): MoviesModel.Result {
    return MoviesModel.Result(
        id = this.id,
        adult = this.adult,
        backdropPath = this.backdropPath,
        originalTitle = this.originalName,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.firstAirDate,
        title = this.name,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )

}