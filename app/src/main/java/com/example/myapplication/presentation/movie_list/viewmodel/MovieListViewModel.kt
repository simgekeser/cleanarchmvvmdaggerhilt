package com.example.myapplication.presentation.movie_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Resource
import com.example.myapplication.domain.use_case.get_movies.GetCoinsUseCase
import com.example.myapplication.presentation.movie_list.viewmodel.states.MovieListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
   private val getMovieUseCase: GetCoinsUseCase
):ViewModel(){

   private val _state = MutableStateFlow(MovieListState())
   val state: StateFlow<MovieListState> = _state

   init {
       getMovies()
   }

   private fun getMovies(){
      getMovieUseCase().onEach { result->
         when(result){
            is Resource.Success -> {
               _state.value = MovieListState(movies = result.data ?: emptyList())
            }
            is Resource.Error -> {
               _state.value = MovieListState(error = result.message ?: "An unexpected error occured")
            }
            is Resource.Loading -> {
               _state.value= MovieListState(isLoading = true)
            }
         }
      }.launchIn(viewModelScope)
   }
}