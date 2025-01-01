package com.faridjeyhunhuseyinteymur.newsly.ui.news.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.faridjeyhunhuseyinteymur.newsly.R
import com.faridjeyhunhuseyinteymur.newsly.data.model.Article
import com.faridjeyhunhuseyinteymur.newsly.databinding.ItemNewsBinding

class NewsAdapter(
    var onItemClick: ((Article) -> Unit)? = null,
    var onItemSaveClick: ((Article, Boolean) -> Unit)? = null // Pass save status
) : ListAdapter<Article, NewsAdapter.NewsViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)

        // Handle item click
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(article)
        }

        // Handle save button click
        holder.binding.saveButton.setOnClickListener {
            val newSaveState = !article.isSaved
            onItemSaveClick?.invoke(article, newSaveState) // Notify fragment/activity
            article.isSaved = newSaveState // Toggle save state
            holder.updateSaveButtonIcon(article.isSaved)
        }
    }

    inner class NewsViewHolder(val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.apply {
                titleTextView.text = article.title
                descriptionTextView.text = article.description

                // Load image
                Glide.with(itemView.context)
                    .load(article.urlToImage)
                    .placeholder(R.drawable.ic_bookmark_outlined)
                    .into(newsImageView)

                // Update save button icon based on save state
                updateSaveButtonIcon(article.isSaved)
            }
        }

        fun updateSaveButtonIcon(isSaved: Boolean) {
            val iconResId = if (isSaved) {
                R.drawable.ic_bookmark_filled // Black filled icon
            } else {
                R.drawable.ic_bookmark_outlined // Outline icon
            }
            binding.saveButton.setImageResource(iconResId)
        }
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