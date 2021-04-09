package com.application.news.api

import android.content.Context
import com.application.news.viewmodel.FeedViewModel
import com.application.news.viewmodel.LoginViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: NewsService)
    fun inject(feedviewModel: FeedViewModel)
    fun inject(loginViewModel: LoginViewModel)
}