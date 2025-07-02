package com.itssagnikmukherjee.newsapp.ui.navigation
import kotlinx.serialization.Serializable

@Serializable
object SplashScreen

@Serializable
object MainScreen

@Serializable
data class ArticleScreen(
    val url: String?,
    val title: String?,
    val description: String?,
    val imageUrl: String?,
    val publishedAt: String?
)