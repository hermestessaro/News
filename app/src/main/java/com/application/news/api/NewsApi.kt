package com.application.news.api

import com.application.news.model.ApiAnswer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("/v1/client/news")
    suspend fun getNews(
        @Query("current_page") page:Int?,
        @Query("per_page") per_page:Int?,
        @Query("published_at") published_at:String?) : Response<ApiAnswer?>

    @GET("/v1/client/news/highlights")
    suspend fun getHighlights() : Response<ApiAnswer?>
}