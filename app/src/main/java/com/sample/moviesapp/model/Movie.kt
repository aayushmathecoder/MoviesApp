package com.sample.moviesapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(
    var adult: Boolean = false,
    var backdropPath: String? = null,
    @PrimaryKey
    val id: Int = 0,
    var imdbId: String? = null,
    @SerializedName("original_language")
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    @SerializedName("poster_path")
    var posterPath: String? = null,
    @SerializedName("release_date")
    var releaseDate: String? = null,
    var revenue: Integer? = null,
    var runtime: String? = null,
    var status: String? = null,
    var tagline: String? = null,
    var title: String? = null,
    @SerializedName("vote_average")
    var voteAverage: Double? = null,
    @SerializedName("vote_count")
    var voteCount: Double? = null
)