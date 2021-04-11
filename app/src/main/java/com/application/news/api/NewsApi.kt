package com.application.news.api

import com.application.news.model.LoginResponse
import com.application.news.model.NewsResponse
import retrofit2.Response
import retrofit2.http.*

interface NewsApi {
    @GET("/v1/client/news")
    suspend fun getNews(
        @Query("current_page") page:Int?,
        @Query("per_page") per_page:Int?,
        @Query("published_at") published_at:String?) : Response<NewsResponse?>

    @GET("/v1/client/news/highlights")
    suspend fun getHighlights() : Response<NewsResponse?>

    @POST("/v1/client/auth/signup")
    suspend fun signUp(
        @Query("name") name:String,
        @Query("email") email:String,
        @Query("password") password:String,
    ): Response<LoginResponse>

    @POST("/v1/client/auth/signin")
    suspend fun signIn(
        @Query("email") email:String,
        @Query("password") password:String
    ): Response<LoginResponse>
}