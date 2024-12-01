package com.faridjeyhunhuseyinteymur.newsly.ui.news.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.faridjeyhunhuseyinteymur.newsly.data.model.Article
import com.faridjeyhunhuseyinteymur.newsly.databinding.FragmentArticleDetailBinding

class ArticleDetailFragment : Fragment() {
    private var _binding: FragmentArticleDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ArticleDetailViewModel by viewModels()

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

        try {
            arguments?.let { args ->
                val article = Article(
                    title = args.getString("title", ""),
                    description = args.getString("description", ""),
                    category = args.getString("category", ""),
                    imageId = args.getInt("imageId", 0)
                )
                viewModel.setArticle(article)
            }

            viewModel.article.observe(viewLifecycleOwner) { article ->
                binding.apply {
                    titleTextView.text = article.title
                    descriptionTextView.text = article.description
                    categoryChip.text = article.category
                    newsImageView.setImageResource(article.imageId)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
