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
                // Filter out removed articles
                val filteredArticles = response.data?.articles?.filter { article ->
                    !article.title.contains("[Removed]", ignoreCase = true) &&
                            !article.description.isNullOrEmpty() &&
                            !article.description.contains("[Removed]", ignoreCase = true)
                }

                // Create new APIResponse with filtered articles
                _news.value = Resource.Success(
                    APIResponse(
                        articles = filteredArticles ?: emptyList(),
                        status = response.data?.status ?: "",
                        totalResults = response.data?.totalResults ?: 0
                    )
                )
            } else {
                _news.value = response
            }
        } catch (e: Exception) {
            _news.value = Resource.Error(e.message ?: "An error occurred")
        }
    }
}