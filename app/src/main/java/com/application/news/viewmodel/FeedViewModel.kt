package com.application.news.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.application.news.api.DaggerApiComponent
import com.application.news.api.NewsService
import com.application.news.database.NewsDatabase
import com.application.news.model.News
import com.application.news.util.NewsBoundaryCallback
import javax.inject.Inject

class FeedViewModel(application: Application): ViewModel() {

    @Inject
    lateinit var newsService: NewsService

    var newsLiveData: LiveData<PagedList<News>>

    private val config = PagedList.Config.Builder()
        .setPageSize(20)
        .setEnablePlaceholders(true)
        .build()

    init {
        DaggerApiComponent.create().inject(this)
        newsLiveData = initializedPagedListBuilder(config, application).build()
    }

    private fun initializedPagedListBuilder(
        config: PagedList.Config,
        application: Application
    ): LivePagedListBuilder<Int, News> {
        return LivePagedListBuilder(
            NewsDatabase(application).newsDao().getNews(), config)
            .setBoundaryCallback(NewsBoundaryCallback(newsService, NewsDatabase(application))
        )
    }
}