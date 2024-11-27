package com.faridjeyhunhuseyinteymur.newsly.ui.news.list

class NewsViewHolder(
    private val binding: ItemNewsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article) {
        binding.apply {
            titleTextView.text = article.title
            descriptionTextView.text = article.description
            newsImageView.setImageResource(article.urlToImage)
            categoryChip.text = article.category
        }
    }
}