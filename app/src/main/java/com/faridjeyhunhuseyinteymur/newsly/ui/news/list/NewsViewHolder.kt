package com.faridjeyhunhuseyinteymur.newsly.ui.news.list

import androidx.recyclerview.widget.RecyclerView
import com.faridjeyhunhuseyinteymur.newsly.data.model.Article
import com.faridjeyhunhuseyinteymur.newsly.databinding.ItemNewsBinding

class NewsViewHolder(
    private val binding: ItemNewsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article) {
        binding.apply {
            titleTextView.text = article.title
            descriptionTextView.text = article.description
            newsImageView.setImageResource(article.imageId)
            categoryChip.text = article.category
        }
    }
}