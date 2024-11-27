package com.faridjeyhunhuseyinteymur.newsly.ui.news.list

import com.faridjeyhunhuseyinteymur.newsly.data.repository.NewsRepository

class NewsListViewModel : ViewModel() {
    private val repository = NewsRepository()

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadMockNews()
    }

    private fun loadMockNews() {
        _isLoading.value = true
        _articles.value = repository.getMockNews()
        _isLoading.value = false
    }

    // TODO: Will be implemented in Part 2
    // suspend fun loadNews()
    // suspend fun loadNewsByCategory(category: String)
}