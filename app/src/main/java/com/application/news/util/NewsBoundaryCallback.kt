package com.application.news.util

import androidx.annotation.MainThread
import androidx.paging.PagedList
import com.application.news.api.NewsService
import com.application.news.database.NewsDatabase
import com.application.news.model.News
import kotlinx.coroutines.*

class NewsBoundaryCallback(
    private val service: NewsService,
    private val database: NewsDatabase
) : PagedList.BoundaryCallback<News>() {

    var lastRequestedPage = 1

    @MainThread
    override fun onZeroItemsLoaded() {
        CoroutineScope(Dispatchers.IO).launch {
            requestNews()
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: News) {
        lastRequestedPage++
        CoroutineScope(Dispatchers.IO).launch {
            requestNews()
        }
    }

    private fun saveInDatabase(news: List<News>){
        GlobalScope.launch {
            database.newsDao().insertAll(news)
        }
    }

    private suspend fun requestNews(){
        val response = service.getNews(lastRequestedPage, null, null)
        return withContext(Dispatchers.Main){
            try {
                if (response.isSuccessful){
                    response.body()?.let {
                        saveInDatabase(it.data)
                    }
                }
            } catch (e: Throwable){
                e.printStackTrace()
            }
        }
    }
}