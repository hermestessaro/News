package com.application.news.api

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [ContextModule::class])
class ApiModule {

    private val BASE_URL = "https://mesa-news-api.herokuapp.com"

    @Provides
    fun providesNewsApi(context: Context): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient(context))
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    fun provideNewsService(context: Context): NewsService {
        return NewsService(context)
    }

    private fun getClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }
}