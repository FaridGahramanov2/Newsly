package com.faridjeyhunhuseyinteymur.newsly.ui.main



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faridjeyhunhuseyinteymur.newsly.data.model.APIResponse
import com.faridjeyhunhuseyinteymur.newsly.data.repository.NewsRepository
import com.faridjeyhunhuseyinteymur.newsly.util.Resource
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = NewsRepository()
    private val _news = MutableLiveData<Resource<APIResponse>>()
    val news: LiveData<Resource<APIResponse>> = _news

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            _news.value = Resource.Loading()
            _news.value = repository.getNews()
        }
    }

    fun refreshNews() {
        getNews()
    }

    fun getNewsByCategory(category: String) {
        viewModelScope.launch {
            _news.value = Resource.Loading()
            _news.value = repository.getNews(category)
        }
    }
}