package com.roberts.tvshows.data.network

import com.roberts.tvshows.model.Shows
import retrofit2.http.GET

interface JsonPlaceholderAPI {
    @GET("shows")
    suspend fun getShows() : List<Shows>
}