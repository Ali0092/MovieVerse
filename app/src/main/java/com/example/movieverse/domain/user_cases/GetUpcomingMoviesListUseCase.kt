package com.example.movieverse.domain.user_cases

import com.example.movieverse.common.ViewState
import com.example.movieverse.data.model.toMoviesModel
import com.example.movieverse.domain.model.MoviesModel
import com.example.movieverse.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUpcomingMoviesListUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    operator fun invoke(): Flow<ViewState<MoviesModel>> = flow {
        try {
            emit(ViewState.Loading())
            val response = moviesRepository.getUpcomingMovies()
            val responseModel =
                if (response.results.isEmpty()) emptyList<MoviesModel.Result>() else response.results.map { it.toMoviesModel() }
            val finalResponseModel = MoviesModel(page = response.page, results = responseModel)

            emit(ViewState.Success(finalResponseModel))
        } catch (e: Exception) {
            emit(ViewState.Error(message = e.message))
        }
    }

}