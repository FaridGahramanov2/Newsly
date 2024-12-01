package com.faridjeyhunhuseyinteymur.newsly.ui.news.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faridjeyhunhuseyinteymur.newsly.data.model.Article
import com.faridjeyhunhuseyinteymur.newsly.data.repository.NewsRepository

class NewsListViewModel : ViewModel() {
    private val repository = NewsRepository()

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    init {
        loadMockNews()
    }

    private fun loadMockNews() {
        _articles.value = repository.getNews()
    }

    fun loadNewsByCategory(category: String) {
        _articles.value = repository.getNews(category)
    }
}