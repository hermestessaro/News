package com.application.news.api

import com.application.news.viewmodel.FeedViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: NewsService)
    fun inject(viewModel: FeedViewModel)
}