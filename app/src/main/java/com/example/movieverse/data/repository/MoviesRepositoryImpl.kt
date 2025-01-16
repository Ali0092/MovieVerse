package com.example.movieverse.data.repository

import com.example.movieverse.data.api.TMDBApiInterface
import com.example.movieverse.data.model.MoviesResponse
import com.example.movieverse.domain.repository.MoviesRepository

class MoviesRepositoryImpl(private val tmdbApiInterface: TMDBApiInterface): MoviesRepository {

    override suspend fun getPopularMovies(): MoviesResponse {
       return tmdbApiInterface.getPopularMovies()
    }

    override suspend fun getUpcomingMovies(): MoviesResponse {
        return tmdbApiInterface.getUpcomingMovies()
    }

    override suspend fun getTVShows(): MoviesResponse {
        return tmdbApiInterface.getTVShows()
    }

    //https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc/cjEcqdRdPQJhYre3HUAc5538Gk8.jpg

}