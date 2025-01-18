package com.example.movieverse.data.repository

import com.example.movieverse.data.api.TMDBApiInterface
import com.example.movieverse.data.model.MoviesResponse
import com.example.movieverse.data.model.TVShowResponse
import com.example.movieverse.domain.repository.MoviesRepository

class MoviesRepositoryImpl(private val tmdbApiInterface: TMDBApiInterface): MoviesRepository {

    override suspend fun getPopularMovies(): MoviesResponse {
       return tmdbApiInterface.getPopularMovies()
    }

    override suspend fun getUpcomingMovies(): MoviesResponse {
        return tmdbApiInterface.getUpcomingMovies()
    }

    override suspend fun getTVShows(): TVShowResponse {
        return tmdbApiInterface.getTVShows()
    }

}