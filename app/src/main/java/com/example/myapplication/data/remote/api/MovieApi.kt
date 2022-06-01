package com.example.myapplication.data.remote.api

import com.example.myapplication.data.remote.dto.MovieDetailDto
import com.example.myapplication.data.remote.dto.MovieDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("/movie/popular")
    suspend fun getPopularMovies() : MovieDto

    @GET("/movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_Id") movie_id: String) : MovieDetailDto
}