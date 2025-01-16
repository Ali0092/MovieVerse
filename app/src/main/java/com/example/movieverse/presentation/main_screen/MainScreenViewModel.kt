package com.example.movieverse.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieverse.common.ViewState
import com.example.movieverse.domain.user_cases.GetPopularMoviesListUseCase
import com.example.movieverse.domain.user_cases.GetUpcomingMoviesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getPopularMoviesListUseCase: GetPopularMoviesListUseCase,
    private val getUpcomingMoviesListUseCase: GetUpcomingMoviesListUseCase
    ): ViewModel() {

    private val _popularMovies = MutableStateFlow(MoviesViewState())
    val popularMovies: MutableStateFlow<MoviesViewState> = _popularMovies

    private val _upcomingMovies = MutableStateFlow(MoviesViewState())
    val upcomingMovies: MutableStateFlow<MoviesViewState> = _upcomingMovies

    fun getPopularMovies() {
        getPopularMoviesListUseCase().onEach { viewState ->
            when (viewState) {
                is ViewState.Loading -> _popularMovies.value = MoviesViewState(isLoading = true)
                is ViewState.Success -> _popularMovies.value = viewState.data?.let { MoviesViewState(movies = it) } ?: MoviesViewState()
                is ViewState.Error -> _popularMovies.value = MoviesViewState(error = viewState.message)

            }
        }.launchIn(viewModelScope)
    }

    fun getUpcomingMovies() {
        getUpcomingMoviesListUseCase().onEach { viewState ->
            when (viewState) {
                is ViewState.Loading -> _upcomingMovies.value = MoviesViewState(isLoading = true)
                is ViewState.Success -> _upcomingMovies.value = viewState.data?.let { MoviesViewState(movies = it) } ?: MoviesViewState()
                is ViewState.Error -> _upcomingMovies.value = MoviesViewState(error = viewState.message)

            }
        }.launchIn(viewModelScope)
    }

}