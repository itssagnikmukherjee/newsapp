package com.itssagnikmukherjee.newsapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.itssagnikmukherjee.newsapp.data.repo.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    val articles = repository.articles.asLiveData()

    fun refresh(apiKey: String) {
        viewModelScope.launch {
            repository.fetchAndStoreArticles(apiKey)
        }
    }
}