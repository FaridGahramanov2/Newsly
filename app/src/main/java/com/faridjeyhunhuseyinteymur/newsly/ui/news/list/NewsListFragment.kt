package com.faridjeyhunhuseyinteymur.newsly.ui.news.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.faridjeyhunhuseyinteymur.newsly.R
import com.faridjeyhunhuseyinteymur.newsly.databinding.FragmentNewsListBinding
import com.faridjeyhunhuseyinteymur.newsly.util.Resource

class NewsListFragment : Fragment(R.layout.fragment_news_list) {
    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsListViewModel by viewModels()
    private val newsAdapter = NewsAdapter()
    private val args: NewsListFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewsListBinding.bind(view)

        // Use Safe Args to get category
        val category = args.category ?: "general"
        viewModel.getNews(category)

        setupRecyclerView()
        setupClickListener()
        observeNews()
    }

    private fun setupClickListener() {
        newsAdapter.onItemClick = { article ->
            val action = NewsListFragmentDirections
                .actionNewsListFragmentToArticleDetailFragment(article)
            findNavController().navigate(action)
        }
    }

    private fun setupRecyclerView() {
        binding.newsRecyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun observeNews() {
        viewModel.news.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    hideProgressBar()
                    showRecyclerView()
                    hideEmptyState()
                    resource.data?.let { response ->
                        if (response.articles.isEmpty()) {
                            showEmptyState()
                        } else {
                            newsAdapter.submitList(response.articles)
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    hideEmptyState()
                    resource.message?.let { message ->
                        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                    hideEmptyState()
                    hideRecyclerView()
                }
            }
        }
    }

    private fun hideProgressBar() {
        binding.loadingProgressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.loadingProgressBar.visibility = View.VISIBLE
    }

    private fun hideRecyclerView() {
        binding.newsRecyclerView.visibility = View.GONE
    }

    private fun showRecyclerView() {
        binding.newsRecyclerView.visibility = View.VISIBLE
    }

    private fun hideEmptyState() {
        binding.emptyStateLayout.visibility = View.GONE
    }

    private fun showEmptyState() {
        binding.emptyStateLayout.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}