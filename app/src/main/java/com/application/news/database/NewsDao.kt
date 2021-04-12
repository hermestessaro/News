package com.application.news.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.application.news.model.News

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(news: List<News>): List<Long>

    @Query("SELECT * FROM news WHERE highlight = 0 ORDER BY published_at DESC")
    fun getNews(): DataSource.Factory<Int, News>

    @Query("SELECT * FROM news WHERE highlight = 1 ORDER BY published_at DESC")
    fun getHighlightedNews(): DataSource.Factory<Int, News>
}