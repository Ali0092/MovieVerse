package com.example.movieverse.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieverse.common.ViewState
import com.example.movieverse.data.repository.GetMoviesRepository
import com.example.movieverse.domain.user_cases.GetMoviesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val getMoviesListUseCase: GetMoviesListUseCase): ViewModel() {

    private val _movies = MutableStateFlow(MoviesViewState())
    val movies: MutableStateFlow<MoviesViewState> = _movies

    fun getPopularMovies() {
        getMoviesListUseCase().onEach { viewState ->
            when (viewState) {
                is ViewState.Loading -> _movies.value = MoviesViewState(isLoading = true)
                is ViewState.Success -> _movies.value = viewState.data?.let { MoviesViewState(movies = it) } ?: MoviesViewState()
                is ViewState.Error -> _movies.value = MoviesViewState(error = viewState.message)

            }
        }.launchIn(viewModelScope)
    }

}