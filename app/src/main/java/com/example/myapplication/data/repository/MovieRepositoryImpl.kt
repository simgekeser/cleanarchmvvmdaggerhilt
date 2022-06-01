package com.example.myapplication.data.repository

import com.example.myapplication.data.remote.api.MovieApi
import com.example.myapplication.data.remote.dto.MovieDetailDto
import com.example.myapplication.data.remote.dto.MovieDto
import com.example.myapplication.domain.repository.MovieRepository
import javax.inject.Inject


//ın our domain layer we cannot only define our repositories,so we create an interface here
class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
): MovieRepository{
    override suspend fun getPopularMovies(): MovieDto {
       return api.getPopularMovies()
    }

    override suspend fun getMovieById(id: String): MovieDetailDto {
       return api.getMovieById(id)
    }
}