package com.faridjeyhunhuseyinteymur.newsly.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faridjeyhunhuseyinteymur.newsly.data.model.APIResponse
import com.faridjeyhunhuseyinteymur.newsly.data.model.Article
import com.faridjeyhunhuseyinteymur.newsly.data.repository.NewsRepository
import com.faridjeyhunhuseyinteymur.newsly.util.Resource
import kotlinx.coroutines.launch

class CategoriesViewModel : ViewModel() {
    private val repository = NewsRepository()
    private val _category = MutableLiveData<String>()
    val category: LiveData<String> = _category

    fun setCategory(category: String) {
        _category.value = category
    }
}