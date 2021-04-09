package com.application.news.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class News(
    @PrimaryKey
    @ColumnInfo(name="title")
    val title: String,
    @ColumnInfo(name="description")
    val description: String?,
    @ColumnInfo(name="content")
    val content: String?,
    @ColumnInfo(name="author")
    val author: String?,
    @ColumnInfo(name="published_at")
    @SerializedName("published_at")
    val publishedAt: String?,
    @ColumnInfo(name="highlight")
    val highlight: Boolean?,
    @ColumnInfo(name="url")
    val url: String?,
    @ColumnInfo(name="image_url")
    @SerializedName("image_url")
    val imageUrl: String?,
    @ColumnInfo(name="favorited")
    var favorited: Boolean
)