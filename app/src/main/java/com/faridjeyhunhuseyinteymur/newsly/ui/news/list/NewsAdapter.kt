package com.faridjeyhunhuseyinteymur.newsly.ui.news.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faridjeyhunhuseyinteymur.newsly.data.model.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var articles = listOf<Article>()
    var onItemClick: ((Article) -> Unit)? = null

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.apply {
                title.text = article.title
                descriptionTextView.text = article.description
                categoryTextView.text = article.category
                newsImageView.setImageResource(article.imageResourceId)
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