package com.faridjeyhunhuseyinteymur.newsly.ui.news.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faridjeyhunhuseyinteymur.newsly.data.model.Article

class ArticleDetailViewModel : ViewModel() {
    private val _article = MutableLiveData<Article>()
    val article: LiveData<Article> = _article

    fun setArticle(article: Article) {
        _article.value = article
    }
}