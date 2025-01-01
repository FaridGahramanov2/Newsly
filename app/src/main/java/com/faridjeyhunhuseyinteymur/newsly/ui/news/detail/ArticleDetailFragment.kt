package com.faridjeyhunhuseyinteymur.newsly.ui.news.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.faridjeyhunhuseyinteymur.newsly.R
import com.faridjeyhunhuseyinteymur.newsly.databinding.FragmentArticleDetailBinding

class ArticleDetailFragment : Fragment(R.layout.fragment_article_detail) {
    private var _binding: FragmentArticleDetailBinding? = null
    private val binding get() = _binding!!
    private val args: ArticleDetailFragmentArgs by navArgs()
    private val viewModel: ArticleDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentArticleDetailBinding.bind(view)

        viewModel.setArticle(args.article)
        observeArticle()
    }

    private fun observeArticle() {
        viewModel.article.observe(viewLifecycleOwner) { article ->
            binding.apply {
                titleTextView.text = article.title
                descriptionTextView.text = article.description
                article.urlToImage?.let { url ->
                    Glide.with(this@ArticleDetailFragment)
                        .load(url)
                        .into(newsImageView)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}