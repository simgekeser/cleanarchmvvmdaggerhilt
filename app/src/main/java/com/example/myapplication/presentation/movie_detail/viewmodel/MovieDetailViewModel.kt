package com.example.myapplication.presentation.movie_detail.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Constants
import com.example.myapplication.common.Resource
import com.example.myapplication.domain.use_case.get_movie.GetCoinUseCase
import com.example.myapplication.presentation.movie_detail.viewmodel.states.MovieDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
   private val getMovieDetailUseCase: GetCoinUseCase,
   savedStateHandle: SavedStateHandle
):ViewModel(){

   private val _state = mutableStateOf(MovieDetailState())
   val state: State<MovieDetailState> = _state

   init {
       savedStateHandle.get<String>(Constants.PARAM_MOVIE_ID)?.let { movieId ->
          getMovieDetail(movieId)
       }
   }

   private fun getMovieDetail(coinId : String){
      getMovieDetailUseCase(movieId = coinId).onEach { result->
         when(result){
            is Resource.Success -> {
               _state.value = MovieDetailState(movie = result.data)
            }
            is Resource.Error -> {_state
               _state.value = MovieDetailState(error = result.message ?: "An unexpected error occured")
            }
            is Resource.Loading -> {
               _state.value= MovieDetailState(isLoading = true)
            }
         }
      }.launchIn(viewModelScope)
   }

}