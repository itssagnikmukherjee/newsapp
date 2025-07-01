package com.itssagnikmukherjee.newsapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey val url : String,
    val title : String?,
    val description : String?,
    val urlToImage : String?,
    val publishedAt : String?
)