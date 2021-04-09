package com.application.news.api


import android.content.Context
import com.application.news.model.ApiAnswer
import com.application.news.model.LoginRequest
import com.application.news.model.SignUpRequest
import retrofit2.Response
import javax.inject.Inject

class NewsService(context: Context) {

    @Inject
    lateinit var api: NewsApi

    init {
        DaggerApiComponent.builder().contextModule(ContextModule(context)).build().inject(this)
    }

    suspend fun getNews(page: Int?, perPage: Int?, publishedAt: String?): Response<ApiAnswer?> {
        return api.getNews(page, perPage, publishedAt)
    }

    suspend fun getHighlights(): Response<ApiAnswer?> {
        return api.getHighlights()
    }

    suspend fun signUp(request: SignUpRequest): Response<String> {
        return api.signUp(request)
    }

    suspend fun signIn(request: LoginRequest): Response<String> {
        return api.signIn(request)
    }
}