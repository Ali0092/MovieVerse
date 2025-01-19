package com.example.movieverse.data.repository

import com.example.movieverse.data.api.TMDBApiInterface
import com.example.movieverse.data.model.MoviesDTO
import com.example.movieverse.data.model.TVShowDTO
import com.example.movieverse.domain.repository.MoviesRepository

class MoviesRepositoryImpl(private val tmdbApiInterface: TMDBApiInterface): MoviesRepository {

    override suspend fun getPopularMovies(): MoviesDTO {
       return tmdbApiInterface.getPopularMovies()
    }

    override suspend fun getUpcomingMovies(): MoviesDTO {
        return tmdbApiInterface.getUpcomingMovies()
    }

    override suspend fun getTVShows(): TVShowDTO {
        return tmdbApiInterface.getTVShows()
    }

}