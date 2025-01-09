package com.faridjeyhunhuseyinteymur.newsly.ui.news.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.faridjeyhunhuseyinteymur.newsly.data.model.Article
import com.faridjeyhunhuseyinteymur.newsly.databinding.ItemNewsBinding

class NewsAdapter(
    var onItemClick: ((Article) -> Unit)? = null,
    var onItemSaveClick: ((Article, Boolean) -> Unit)? = null
) : ListAdapter<Article, NewsViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsViewHolder(binding).also { viewHolder ->

            binding.root.setOnClickListener {
                val position = viewHolder.adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val article = getItem(position)
                    onItemClick?.invoke(article)
                }
            }

            binding.saveButton.setOnClickListener {
                val position = viewHolder.adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val article = getItem(position)
                    val newSaveState = !article.isSaved
                    article.isSaved = newSaveState
                    binding.invalidateAll()
                    Log.d("NewsAdapter", "Save button clicked for article: ${article.title}")
                    onItemSaveClick?.invoke(article, newSaveState)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }

    class NewsDiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}
