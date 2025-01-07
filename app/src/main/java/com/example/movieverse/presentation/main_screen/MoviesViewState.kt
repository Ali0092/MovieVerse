package com.example.movieverse.presentation.main_screen

import com.example.movieverse.domain.model.MoviesModel

data class MoviesViewState(
    val isLoading: Boolean = false,
    val movies: MoviesModel = MoviesModel(),
    val error: String? = null
)
