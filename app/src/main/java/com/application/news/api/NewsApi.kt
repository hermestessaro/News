package com.application.news.api

import com.application.news.model.ApiAnswer
import com.application.news.model.LoginRequest
import com.application.news.model.SignUpRequest
import retrofit2.Response
import retrofit2.http.*

interface NewsApi {
    @GET("/v1/client/news")
    suspend fun getNews(
        @Query("current_page") page:Int?,
        @Query("per_page") per_page:Int?,
        @Query("published_at") published_at:String?) : Response<ApiAnswer?>

    @GET("/v1/client/news/highlights")
    suspend fun getHighlights() : Response<ApiAnswer?>

    @POST("/v1/client/auth/signup")
    @FormUrlEncoded
    suspend fun signUp(@Body request: SignUpRequest): Response<String>

    @POST("/v1/client/auth/signin")
    @FormUrlEncoded
    suspend fun signIn(@Body request: LoginRequest): Response<String>
}