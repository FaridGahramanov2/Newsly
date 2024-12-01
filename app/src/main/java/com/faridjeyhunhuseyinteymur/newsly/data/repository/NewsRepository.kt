package com.faridjeyhunhuseyinteymur.newsly.data.repository

import com.faridjeyhunhuseyinteymur.newsly.R
import com.faridjeyhunhuseyinteymur.newsly.data.model.Article
import com.faridjeyhunhuseyinteymur.newsly.data.model.Source

//This class Contains mock data for now.


class NewsRepository {
    private val mockArticles = listOf(
        Article(
            title = "TotalEnergies pauses business with Adani Group",
            description = "French oil major TotalEnergies halted investments into Adani Group, after the Indian ports-to-power conglomerate was engulfed in a crisis over an alleged multi-million-dollar bribery scheme.",
            category = "Business",
            imageId = R.drawable.ic_home
        ),
        Article(
            title = "Israel-Lebanon ceasefire deal approved",
            description = "Israeli Prime Minister Benjamin Netanyahu approved the emerging ceasefire deal with Hezbollah 'in principle' during a security consultation with Israeli officials Sunday...",
            category = "Politics",
            imageId = R.drawable.ic_category
        )
    )

    fun getNews(category: String? = null): List<Article> {
        return if (category != null && category != "all") {
            mockArticles.filter {
                it.category.equals(category, ignoreCase = true)
            }
        } else {
            mockArticles
        }
    }

    fun getCategories(): List<String> {
        return listOf("All", "Business", "Politics", "Technology", "Sports", "Entertainment")
    }
}