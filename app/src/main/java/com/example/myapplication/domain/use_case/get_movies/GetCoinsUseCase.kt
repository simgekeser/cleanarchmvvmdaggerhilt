package com.example.myapplication.domain.use_case.get_movies

import com.example.myapplication.common.Resource
import com.example.myapplication.data.remote.dto.toMovie
import com.example.myapplication.domain.model.Movie
import com.example.myapplication.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    //we want to emit multiple values over aa perıod time
    operator fun invoke(): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading<List<Movie>>())
            val movieResults = repository.getPopularMovies().toMovie()
            emit(Resource.Success(movieResults))
        }catch (e : HttpException){
            emit(Resource.Error<List<Movie>>(e.localizedMessage?:"Unexpected error occured"))
        } catch (e : IOException){
            emit(Resource.Error<List<Movie>>("Couldnt reach server"))
        }
    }
}