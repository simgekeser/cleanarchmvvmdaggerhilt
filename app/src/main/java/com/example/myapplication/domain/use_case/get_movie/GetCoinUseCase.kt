package com.example.myapplication.domain.use_case.get_movie

import com.example.myapplication.common.Resource
import com.example.myapplication.data.remote.dto.toMovie
import com.example.myapplication.data.remote.dto.toMovieDetail
import com.example.myapplication.domain.model.Movie
import com.example.myapplication.domain.model.MovieDetail
import com.example.myapplication.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    //we want to emit multiple values over aa perıod time
    operator fun invoke(movieId: String): Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repository.getMovieById(movieId).toMovieDetail()
            emit(Resource.Success(movieDetail))
        }catch (e : HttpException){
            emit(Resource.Error(e.localizedMessage?:"Unexpected error occured"))
        } catch (e : IOException){
            emit(Resource.Error("Couldnt reach server"))
        }
    }
}