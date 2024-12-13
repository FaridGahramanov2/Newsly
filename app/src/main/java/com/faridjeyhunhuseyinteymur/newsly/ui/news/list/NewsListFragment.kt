package com.faridjeyhunhuseyinteymur.newsly.ui.news.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.faridjeyhunhuseyinteymur.newsly.databinding.FragmentNewsListBinding

class NewsListFragment : Fragment() {
    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsListViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter
    private val args: NewsListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadNews()
        observeNews()
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter().apply {
            onItemClick = { article ->
                try {
                    val action = NewsListFragmentDirections
                        .actionNewsListFragmentToArticleDetailFragment(article)
                    findNavController().navigate(action)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        binding.newsRecyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun loadNews() {
        args.category?.let { category ->
            viewModel.loadNewsByCategory(category)
        } ?: viewModel.loadAllNews()
    }

    private fun observeNews() {
        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            newsAdapter.submitList(articles)
            updateEmptyState(articles.isEmpty())
        }
    }

    private fun updateEmptyState(isEmpty: Boolean) {
        binding.apply {
            newsRecyclerView.visibility = if (isEmpty) View.GONE else View.VISIBLE
            emptyStateLayout.visibility = if (isEmpty) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
