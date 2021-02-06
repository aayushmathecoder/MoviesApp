package com.sample.moviesapp.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class MovieResultInfo (
    @SerializedName("results")
    var moviesList : List<Movie>
)