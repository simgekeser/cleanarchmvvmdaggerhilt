package com.example.myapplication.data.remote.dto

import com.example.myapplication.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieResults>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

fun MovieDto.toMovie(): List<Movie> {
    return results.map {
        Movie(
            id = it.id,
            original_title=it.originalTitle,
            overview = it.overview,
            popularity=it.popularity,
            poster_path=it.posterPath,
            release_date = it.releaseDate
        )
    }
}



