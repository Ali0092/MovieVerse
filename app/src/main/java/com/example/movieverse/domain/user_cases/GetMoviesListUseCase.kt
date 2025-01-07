package com.example.movieverse.domain.user_cases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieverse.common.ViewState
import com.example.movieverse.domain.model.MoviesModel
import com.example.movieverse.domain.repository.MoviesRepository
import javax.inject.Inject

class GetMoviesListUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {


    operator fun invoke(pag:Int = 0, getMoviesList: (LiveData<ViewState<MoviesModel>>) -> Unit ){

//        getMoviesList(ViewState.Loading(MutableLiveData()))


    }


}