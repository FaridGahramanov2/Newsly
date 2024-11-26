package com.faridjeyhunhuseyinteymur.newsly.ui.main

class MainViewModel : ViewModel() {
    private val repository = NewsRepository()

    private val _selectedCategory = MutableLiveData<String>()
    val selectedCategory: LiveData<String> = _selectedCategory

    fun setCategory(category: String) {
        _selectedCategory.value = category
    }
}