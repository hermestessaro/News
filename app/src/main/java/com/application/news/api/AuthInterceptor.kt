package com.application.news.api

import android.content.Context
import com.application.news.util.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context): Interceptor {
    private val sessionManager = SessionManager(context)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        requestBuilder.addHeader("Authorization", "Bearer ${sessionManager.fetchToken()}")


        return chain.proceed(requestBuilder.build())
    }
}