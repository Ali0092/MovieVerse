package com.example.movieverse.domain.repository

import com.example.movieverse.data.model.MoviesDTO
import com.example.movieverse.data.model.TVShowDTO

interface MoviesRepository {

    suspend fun getPopularMovies(): MoviesDTO

    suspend fun getUpcomingMovies(): MoviesDTO

    suspend fun getTVShows(): TVShowDTO

}
