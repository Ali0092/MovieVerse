package com.example.movieverse.hilt

import com.example.movieverse.data.api.TMDBApiInterface
import com.example.movieverse.data.repository.MoviesRepositoryImpl
import com.example.movieverse.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getTMDBApiInterface(): TMDBApiInterface  =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TMDBApiInterface::class.java)

    @Provides
    @Singleton
    fun getMainRepository(tmdbApiInterface: TMDBApiInterface): MoviesRepository {
        return MoviesRepositoryImpl(tmdbApiInterface)
    }

}