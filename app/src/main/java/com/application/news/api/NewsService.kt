package com.application.news.api


import android.content.Context
import com.application.news.model.NewsResponse
import com.application.news.model.LoginRequest
import com.application.news.model.LoginResponse
import com.application.news.model.SignUpRequest
import retrofit2.Response
import javax.inject.Inject

class NewsService(context: Context) {

    @Inject
    lateinit var api: NewsApi

    init {
        DaggerApiComponent.builder().contextModule(ContextModule(context)).build().inject(this)
    }

    suspend fun getNews(page: Int?, perPage: Int?, publishedAt: String?): Response<NewsResponse?> {
        return api.getNews(page, perPage, publishedAt)
    }

    suspend fun getHighlights(): Response<NewsResponse?> {
        return api.getHighlights()
    }

    suspend fun signUp(request: SignUpRequest): Response<LoginResponse> {
        return api.signUp(request.name, request.email, request.password)
    }

    suspend fun signIn(request: LoginRequest): Response<LoginResponse> {
        return api.signIn(request.email, request.password)
    }
}