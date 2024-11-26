package com.faridjeyhunhuseyinteymur.newsly.ui.categories

class CategoriesViewModel : ViewModel() {
    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>> = _categories

    init {
        loadCategories()
    }

    private fun loadCategories() {
        _categories.value = listOf(
            "Business",
            "Entertainment",
            "General",
            "Health",
            "Science",
            "Sports",
            "Technology"
        )
    }
}