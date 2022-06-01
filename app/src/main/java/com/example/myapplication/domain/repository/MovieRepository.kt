package com.example.myapplication.domain.repository

import com.example.myapplication.data.remote.dto.MovieDetailDto
import com.example.myapplication.data.remote.dto.MovieDto

interface MovieRepository {

    suspend fun getPopularMovies() : MovieDto

    suspend fun getMovieById(id:String) : MovieDetailDto
}