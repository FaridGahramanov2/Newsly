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

        // Use arguments bundle for Part 1
        arguments?.getString("title")?.let { title ->
            arguments?.getString("description")?.let { description ->
                arguments?.getString("category")?.let { category ->
                    arguments?.getInt("imageId")?.let { imageId ->
                        val article = Article(
                            title = title,
                            description = description,
                            category = category,
                            imageId = imageId
                        )
                        viewModel.setArticle(article)
                    }
                }
            }
        }

        // Observe article details
        viewModel.article.observe(viewLifecycleOwner) { article ->
            binding.apply {
                titleTextView.text = article.title
                descriptionTextView.text = article.description
                categoryChip.text = article.category
                newsImageView.setImageResource(article.imageId)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(article: Article) = ArticleDetailFragment().apply {
            arguments = Bundle().apply {
                putString("title", article.title)
                putString("description", article.description)
                putString("category", article.category)
                putInt("imageId", article.imageId)
            }
        }
    }
}
