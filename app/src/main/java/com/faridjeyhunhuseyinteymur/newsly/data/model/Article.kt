package com.faridjeyhunhuseyinteymur.newsly.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Article(
    val title: String,
    val description: String,
    val category: String,
    val imageId: Int
): Parcelable
