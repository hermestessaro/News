package com.application.news.api


import com.application.news.model.ApiAnswer
import retrofit2.Response
import javax.inject.Inject

class NewsService {

    @Inject
    lateinit var api: NewsApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    suspend fun getNews(page: Int?, perPage: Int?, publishedAt: String?): Response<ApiAnswer?> {
        return api.getNews(page, perPage, publishedAt)
    }

    suspend fun getHighlights(): Response<ApiAnswer?> {
        return api.getHighlights()
    }
}