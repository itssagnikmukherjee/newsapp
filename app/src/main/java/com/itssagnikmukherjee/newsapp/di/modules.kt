package com.itssagnikmukherjee.newsapp.di

import android.content.Context
import androidx.room.Room
import com.itssagnikmukherjee.newsapp.data.const.Const.BASE_URL
import com.itssagnikmukherjee.newsapp.data.local.AppDatabase
import com.itssagnikmukherjee.newsapp.data.local.ArticleDao
import com.itssagnikmukherjee.newsapp.data.remote.NewsApiService
import com.itssagnikmukherjee.newsapp.data.repo.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideApiService(baseUrl : String): NewsApiService = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(
        GsonConverterFactory.create()).build().create(NewsApiService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "news-db").build()

    @Provides
    fun provideArticleDao(db: AppDatabase): ArticleDao = db.articleDao()

    @Provides
    @Singleton
    fun provideRepository(api: NewsApiService, dao: ArticleDao): NewsRepository =
        NewsRepository(api, dao)
}