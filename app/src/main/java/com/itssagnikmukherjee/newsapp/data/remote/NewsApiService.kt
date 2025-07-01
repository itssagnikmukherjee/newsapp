package com.itssagnikmukherjee.newsapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "uk",
        @Query("category") category: String = "business",
        @Query("apiKey") apiKey: String
    ): NewsResponse
}
