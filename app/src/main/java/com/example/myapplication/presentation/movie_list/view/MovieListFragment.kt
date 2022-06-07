package com.example.myapplication.presentation.movie_list.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMovieListBinding
import com.example.myapplication.presentation.movie_list.adapter.MovieListAdapter
import com.example.myapplication.presentation.movie_list.viewmodel.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var movieAdapter: MovieListAdapter
    private val viewModel: MovieListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieAdapter = MovieListAdapter(arrayListOf())
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(binding.root.context)

        binding.rvMovieList.layoutManager = layoutManager
        binding.rvMovieList.adapter=movieAdapter

        lifecycleScope.launch {
            viewModel.state.collect {
                when {
                    it.isLoading -> {
                        Log.d("MovieListFragment","Loading")
                    }
                    it.error.isNotBlank() -> {
                        Log.d("MovieListFragment","Error")
                    }
                    it.movies.isNotEmpty() ->
                        movieAdapter.updateMovieDataList(it.movies)
                }
            }
        }
    }

}