package com.faridjeyhunhuseyinteymur.newsly.ui.news.list

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.faridjeyhunhuseyinteymur.newsly.data.model.Article
import com.faridjeyhunhuseyinteymur.newsly.databinding.ItemNewsBinding

class NewsViewHolder(
    private val binding: ItemNewsBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(article: Article) {
        binding.apply {
            this.article = article
            article.urlToImage?.let {
                Glide.with(itemView.context)
                    .load(it)
                    .into(newsImageView)
            }
            executePendingBindings()
        }
    }
}