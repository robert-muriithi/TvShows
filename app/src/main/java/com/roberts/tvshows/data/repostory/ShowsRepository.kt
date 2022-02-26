package com.roberts.tvshows.data.repostory

import android.util.Log
import com.roberts.tvshows.data.network.JsonPlaceholderAPI
import com.roberts.tvshows.model.Shows
import com.roberts.tvshows.util.Resource
import javax.inject.Inject

private const val TAG = "ShowsRepository"
class ShowsRepository @Inject constructor(private val api: JsonPlaceholderAPI){
    suspend fun getShows() : Resource<List<Shows>> {
        val response = try {
            api.getShows()
        }
        catch (e : Exception){
            return Resource.Error("Unknown Exception")
        }
        Log.d(TAG, "getShows: $response")
        return Resource.Success(response)
    }

}