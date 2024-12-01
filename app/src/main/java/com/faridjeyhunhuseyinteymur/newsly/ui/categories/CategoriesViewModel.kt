package com.faridjeyhunhuseyinteymur.newsly.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CategoriesViewModel : ViewModel() {
    private val _category = MutableLiveData<String>()
    val category: LiveData<String> = _category

    fun setCategory(category: String) {
        _category.value = category
    }
}