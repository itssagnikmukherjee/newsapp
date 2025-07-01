package com.itssagnikmukherjee.newsapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.itssagnikmukherjee.newsapp.data.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {
    val articles = repository.articles.asLiveData()

    fun refresh(apiKey: String) {
        viewModelScope.launch {
            repository.fetchAndStoreArticles(apiKey)
        }
    }
}
