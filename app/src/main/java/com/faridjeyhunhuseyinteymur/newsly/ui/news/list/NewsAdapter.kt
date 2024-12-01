package com.faridjeyhunhuseyinteymur.newsly.ui.news.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faridjeyhunhuseyinteymur.newsly.data.model.Article
import com.faridjeyhunhuseyinteymur.newsly.databinding.ItemNewsBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var articles = listOf<Article>()
    var onItemClick: ((Article) -> Unit)? = null

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.apply {
                titleTextView.text = article.title
                descriptionTextView.text = article.description
                categoryChip.text = article.category
                newsImageView.setImageResource(article.imageId)
                root.setOnClickListener {
                    onItemClick?.invoke(article)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount() = articles.size

    fun submitList(newArticles: List<Article>) {
        articles = newArticles
        notifyDataSetChanged()
    }
}