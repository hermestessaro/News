package com.application.news.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.application.news.api.ContextModule
import com.application.news.api.DaggerApiComponent
import com.application.news.api.NewsService
import com.application.news.database.NewsDatabase
import com.application.news.model.News
import com.application.news.util.NewsBoundaryCallback
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeedViewModel(application: Application): ViewModel() {

    @Inject
    lateinit var newsService: NewsService

    var regularNewsLiveData: LiveData<PagedList<News>>
    var highlightedNewsLiveData: LiveData<PagedList<News>>

    private val config = PagedList.Config.Builder()
        .setPageSize(20)
        .setEnablePlaceholders(true)
        .build()

    init {
        DaggerApiComponent.builder().contextModule(ContextModule(application)).build().inject(this)
        regularNewsLiveData = initializedNormalPagedListBuilder(config, application).build()
        highlightedNewsLiveData = initializedHighlightPagedListBuilder(config, application).build()
    }

    private fun initializedNormalPagedListBuilder(
        config: PagedList.Config,
        application: Application
    ): LivePagedListBuilder<Int, News> {
        return LivePagedListBuilder(
            NewsDatabase(application).newsDao().getNews(), config)
            .setBoundaryCallback(NewsBoundaryCallback(newsService, NewsDatabase(application))
        )
    }

    private fun initializedHighlightPagedListBuilder(
        config: PagedList.Config,
        application: Application
    ): LivePagedListBuilder<Int, News> {
        return LivePagedListBuilder(
            NewsDatabase(application).newsDao().getHighlightedNews(), config)
            .setBoundaryCallback(NewsBoundaryCallback(newsService, NewsDatabase(application))
        )
    }

    fun updateFavorite(value: Boolean, newsTitle: String, context: Context){
        GlobalScope.launch {
            NewsDatabase(context).newsDao().updateFavorited(value, newsTitle)
        }
    }
}