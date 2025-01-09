package com.faridjeyhunhuseyinteymur.newsly.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val category: String? = null,
    val source: Source? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    var isSaved: Boolean = false
) : Parcelable {
    constructor() : this(
        author = null,
        content = null,
        description = null,
        publishedAt = null,
        category = null,
        source = null,
        title = null,
        url = null,
        urlToImage = null,
        isSaved = false
    )


}