package com.faridjeyhunhuseyinteymur.newsly.ui.news.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faridjeyhunhuseyinteymur.newsly.data.model.Article
import com.faridjeyhunhuseyinteymur.newsly.data.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsListViewModel : ViewModel() {
    private val repository = NewsRepository()

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadAllNews() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val news = repository.getNews()
                _articles.value = news
            } catch (e: Exception) {
                _articles.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadNewsByCategory(category: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val news = repository.getNews(category)
                _articles.value = news
            } catch (e: Exception) {
                _articles.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}