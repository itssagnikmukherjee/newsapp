package com.itssagnikmukherjee.newsapp.data.remote

data class NewsResponse(
    val articles: List<Article>?
)

data class Article(
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?
)