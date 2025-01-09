package com.faridjeyhunhuseyinteymur.newsly.ui.news.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faridjeyhunhuseyinteymur.newsly.data.model.APIResponse
import com.faridjeyhunhuseyinteymur.newsly.data.repository.NewsRepository
import com.faridjeyhunhuseyinteymur.newsly.util.Resource
import kotlinx.coroutines.launch

class NewsListViewModel : ViewModel() {
    private val repository = NewsRepository()
    private val _news = MutableLiveData<Resource<APIResponse>>()
    val news: LiveData<Resource<APIResponse>> = _news

    fun getNews(category: String? = null) = viewModelScope.launch {
        _news.value = Resource.Loading()
        try {
            val response = repository.getNews(category)
            if (response is Resource.Success) {
                val filteredArticles = response.data?.articles?.filter { article ->
                    val title = article.title ?: ""
                    val description = article.description ?: ""

                    !title.contains("[Removed]", ignoreCase = true) &&
                            description.isNotEmpty() &&
                            !description.contains("[Removed]", ignoreCase = true)
                }

                _news.value = Resource.Success(
                    APIResponse(
                        articles = filteredArticles ?: emptyList(),
                        status = response.data?.status ?: "",
                        totalResults = response.data?.totalResults ?: 0
                    )
                )
            } else {
                _news.value = Resource.Error(response.message ?: "Unknown error")
            }
        } catch (e: Exception) {
            _news.value = Resource.Error(e.message ?: "An unexpected error occurred")
        }
    }
}