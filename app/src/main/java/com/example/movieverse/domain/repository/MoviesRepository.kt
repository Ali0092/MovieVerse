package com.example.movieverse.domain.repository

import com.example.movieverse.data.model.MoviesResponse
import com.example.movieverse.data.model.TVShowResponse

interface MoviesRepository {

    suspend fun getPopularMovies(): MoviesResponse

    suspend fun getUpcomingMovies(): MoviesResponse

    suspend fun getTVShows(): TVShowResponse

}
