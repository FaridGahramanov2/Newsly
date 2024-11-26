package com.faridjeyhunhuseyinteymur.newsly.data.repository

import com.faridjeyhunhuseyinteymur.newsly.data.model.Article

//This class Contains mock data for now.


class NewsRepository {
    fun getMockNews(): List<Article> {
        return listOf(
            Article(
                "TotalEnergies pauses business with Adani Group",
                "French oil major TotalEnergies halted investments into Adani Group, after the Indian ports-to-power conglomerate was engulfed in a crisis over an alleged multi-million-dollar bribery scheme.",
                R.drawable.placeholder_news,
                "Business"
            ),
            Article(
                "Israel-Lebanon ceasefire deal approved",
                "Israeli Prime Minister Benjamin Netanyahu approved the emerging ceasefire deal with Hezbollah 'in principle' during a security consultation with Israeli officials Sunday...",
                R.drawable.placeholder_news,
                "Politics"
            ),
            Article(
                "New AI breakthrough in medical research",
                "Scientists announce major advancement in AI-powered drug discovery platform...",
                R.drawable.placeholder_news,
                "Technology"
            )
        )
    }

    // TODO: Will be implemented in Part 2
    // suspend fun getTopHeadlines(): Resource<List<Article>>
    // suspend fun getNewsByCategory(category: String): Resource<List<Article>>
}