package com.example.movieverse.domain.repository

import com.example.movieverse.data.model.MoviesResponse

interface MoviesRepository {

    suspend fun getPopularMovies(): MoviesResponse

}
