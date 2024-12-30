package com.faridjeyhunhuseyinteymur.newsly.ui.news.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.faridjeyhunhuseyinteymur.newsly.databinding.FragmentArticleDetailBinding

class ArticleDetailFragment : Fragment() {
    private var _binding: FragmentArticleDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ArticleDetailViewModel by viewModels()
    private val args: ArticleDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setArticle(args.article)
        observeArticle()
    }

    private fun observeArticle() {
        viewModel.article.observe(viewLifecycleOwner) { article ->
            binding.apply {
                titleTextView.text = article.title
                descriptionTextView.text = article.description
                categoryChip.text = article.category

                // Use Glide to load image from URL instead of resource
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