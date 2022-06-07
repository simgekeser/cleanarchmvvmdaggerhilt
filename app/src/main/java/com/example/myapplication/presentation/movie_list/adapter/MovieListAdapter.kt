package com.example.myapplication.presentation.movie_list.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.MovieListItemBinding
import com.example.myapplication.domain.model.Movie

class MovieListAdapter(private val movieList: ArrayList<Movie>) : RecyclerView.Adapter<MovieListAdapter.MovieListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListHolder{
        val binding = MovieListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieListHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListHolder, position: Int) {
        val movie: Movie = movieList[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            Log.d("MovieListAdapter",movie.original_title)
        }
    }

    override fun getItemCount(): Int {
       return movieList.size
    }
    fun updateMovieDataList(newList: List<Movie>){
        movieList.clear()
        movieList.addAll(newList)
        notifyDataSetChanged()
    }

    class MovieListHolder(private val itemBinding: MovieListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(movie: Movie) {
            movie.apply {
                itemBinding.mavieName.text = movie.original_title
            }
        }
    }
}