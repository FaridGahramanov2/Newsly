package com.faridjeyhunhuseyinteymur.newsly.ui.news.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.faridjeyhunhuseyinteymur.newsly.R
import com.faridjeyhunhuseyinteymur.newsly.databinding.FragmentNewsListBinding

class NewsListFragment : Fragment() {
    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsListViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter

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
        newsAdapter = NewsAdapter().apply {
            onItemClick = { article ->
                try {
                    val bundle = Bundle().apply {
                        putString("title", article.title)
                        putString("description", article.description)
                        putString("category", article.category)
                        putInt("imageId", article.imageId)
                    }
                    findNavController().navigate(
                        R.id.action_newsListFragment_to_articleDetailFragment,
                        bundle
                    )
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