package com.example.movieverse.domain.repository

import com.example.movieverse.data.model.MoviesResponse

interface MoviesRepository {

    suspend fun getPopularMovies(page: Int = 1): MoviesResponse

}
