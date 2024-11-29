package com.faridjeyhunhuseyinteymur.newsly.data.model

data class Article(
    val title: String,
    val description: String,
    val imageResourceId: Int,  // Using drawable resource IDs for Part 1
    val category: String
)