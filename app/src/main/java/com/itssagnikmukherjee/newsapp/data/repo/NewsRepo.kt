package com.itssagnikmukherjee.newsapp.data.repo

import androidx.compose.ui.util.fastMap
import com.itssagnikmukherjee.newsapp.data.local.ArticleDao
import com.itssagnikmukherjee.newsapp.data.local.ArticleEntity
import com.itssagnikmukherjee.newsapp.data.remote.NewsApiService
import kotlinx.coroutines.flow.Flow
import java.util.Collections.emptyList
import kotlin.collections.*

class NewsRepository(
    private val api: NewsApiService,
    private val dao: ArticleDao
) {
    val articles: Flow<List<ArticleEntity>> = dao.getAllArticles()
    suspend fun fetchAndStoreArticles(apiKey: String) {
        try {
            val response = api.getTopHeadlines(apiKey = apiKey)

            val entities = response.articles?.mapNotNull { article ->
                article.url?.let { url ->
                    ArticleEntity(
                        url = url,
                        title = article.title,
                        description = article.description,
                        urlToImage = article.urlToImage,
                        publishedAt = article.publishedAt
                    )
                }
            } ?: emptyList()

            dao.insertArticles(entities)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}