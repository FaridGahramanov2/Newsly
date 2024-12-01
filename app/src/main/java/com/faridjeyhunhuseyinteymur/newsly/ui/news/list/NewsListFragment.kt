package com.faridjeyhunhuseyinteymur.newsly.ui.news.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.faridjeyhunhuseyinteymur.newsly.R
import com.faridjeyhunhuseyinteymur.newsly.databinding.FragmentNewsListBinding
import com.faridjeyhunhuseyinteymur.newsly.ui.news.list.NewsAdapter
import com.faridjeyhunhuseyinteymur.newsly.ui.news.list.NewsListViewModel

class NewsListFragment : Fragment() {
    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsListViewModel by viewModels()
    private val newsAdapter = NewsAdapter()

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
        observeNews()
    }

    private fun setupRecyclerView() {
        binding.newsRecyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeNews() {
        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            newsAdapter.submitList(articles)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}