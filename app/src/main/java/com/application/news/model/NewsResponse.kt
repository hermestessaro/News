package com.application.news.model

import com.application.news.model.News

class NewsResponse (
    val current_page: Int,
    val per_page: Int,
    val total_pages: Int,
    val total_items: Int,
    val data: List<News>
)