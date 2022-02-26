package com.roberts.tvshows.di

import com.roberts.tvshows.data.network.JsonPlaceholderAPI
import com.roberts.tvshows.data.repostory.ShowsRepository
import com.roberts.tvshows.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule  {

    @Provides
    @Singleton
    fun provideJsonPlaceholderAPI(): JsonPlaceholderAPI {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(JsonPlaceholderAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: JsonPlaceholderAPI) : ShowsRepository{
        return ShowsRepository(api)

    }
}