package com.example.movieverse.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieverse.data.repository.GetMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val getMoviesRepository: GetMoviesRepository): ViewModel() {

    fun getPopularMovies() {
        viewModelScope.launch {
            getMoviesRepository.getPopularMovies()
        }
    }

}