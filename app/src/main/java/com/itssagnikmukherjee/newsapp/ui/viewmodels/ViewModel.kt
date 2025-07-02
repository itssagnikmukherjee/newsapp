package com.itssagnikmukherjee.newsapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.itssagnikmukherjee.newsapp.data.common.ResultState
import com.itssagnikmukherjee.newsapp.data.local.ArticleEntity
import com.itssagnikmukherjee.newsapp.data.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _newsState = MutableStateFlow<ResultState<List<ArticleEntity>>>(ResultState.Loading)
    val newsState : StateFlow<ResultState<List<ArticleEntity>>> = _newsState.asStateFlow()

//    val articles = repository.articles.asLiveData()

    fun refresh(apiKey: String) {
        viewModelScope.launch {
            _newsState.value = ResultState.Loading
            try{
                repository.fetchAndStoreArticles(apiKey)
                repository.articles.collectLatest{
                    _newsState.value = ResultState.Success(it)
                }
            }catch (e: Exception){
                _newsState.value = ResultState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}
