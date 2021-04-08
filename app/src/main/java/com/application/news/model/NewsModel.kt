package com.application.news.model

import com.google.gson.annotations.SerializedName

data class News(
    val title: String,
    val description: String,
    val content: String,
    val author: String,
    @SerializedName("published_at")
    val publishedAt: String,
    val highlight: Boolean,
    val url: String,
    @SerializedName("image_url")
    val imageUrl: String
)