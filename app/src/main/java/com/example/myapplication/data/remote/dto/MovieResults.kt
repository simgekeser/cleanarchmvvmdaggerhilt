package com.example.myapplication.data.remote.dto

import com.example.myapplication.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieResults(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreİds: List<Int>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)


fun MovieResults.toMovie(): Movie {
    return Movie(
        id = id,
        original_title=originalTitle,
        overview = overview,
        popularity=popularity,
        poster_path=posterPath,
        release_date = releaseDate
    )
}
