package com.faridjeyhunhuseyinteymur.newsly.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faridjeyhunhuseyinteymur.newsly.data.model.Article
import com.faridjeyhunhuseyinteymur.newsly.data.repository.NewsRepository

class MainViewModel : ViewModel() {
    private val repository = NewsRepository()

    private val _news = MutableLiveData<List<Article>>()
    val news: LiveData<List<Article>> = _news

    private val _selectedCategory = MutableLiveData<String>()
    val selectedCategory: LiveData<String> = _selectedCategory

    init {
        getNews()
    }

    private fun getNews() {
        _news.value = repository.getNews()
    }

    fun refreshNews() {
        getNews()
    }

    fun getNewsByCategory(category: String) {
        _selectedCategory.value = category
        _news.value = repository.getNews(category)
    }
}