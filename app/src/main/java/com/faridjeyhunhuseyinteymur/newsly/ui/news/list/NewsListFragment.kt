package com.faridjeyhunhuseyinteymur.newsly.ui.news.list

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.faridjeyhunhuseyinteymur.newsly.R
import com.faridjeyhunhuseyinteymur.newsly.data.model.Article
import com.faridjeyhunhuseyinteymur.newsly.databinding.FragmentNewsListBinding
import com.faridjeyhunhuseyinteymur.newsly.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
class NewsListFragment : Fragment(R.layout.fragment_news_list) {
    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsListViewModel by viewModels()
    private val args: NewsListFragmentArgs by navArgs()
    private lateinit var newsAdapter: NewsAdapter

    private lateinit var firestore: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth

    private val savedArticles = mutableSetOf<String>() // Store URLs of saved articles

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewsListBinding.bind(view)

        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Use Safe Args to get category
        val category = args.category ?: "general"
        viewModel.getNews(category)

        fetchSavedArticles() // Load saved articles from Firebase
        setupRecyclerView()
        setupClickListener()
        setupSaveClickListener()
        observeNews()
    }

    private fun fetchSavedArticles() {
        val userId = firebaseAuth.currentUser?.email
        if (userId != null) {
            firestore.collection("users")
                .document(userId)
                .collection("saved_articles")
                .get()
                .addOnSuccessListener { documents ->
                    savedArticles.clear()
                    documents.forEach { doc ->
                        val articleUrl = doc.getString("url")
                        articleUrl?.let { savedArticles.add(it) }
                    }
                    newsAdapter.notifyDataSetChanged()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, "Failed to load saved articles: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun setupClickListener() {
        newsAdapter.onItemClick = { article ->
            val action = NewsListFragmentDirections
                .actionNewsListFragmentToArticleDetailFragment(article)
            findNavController().navigate(action)
        }
    }

    private fun setupSaveClickListener() {
        newsAdapter.onItemSaveClick = { article, isSaved ->
            saveOrRemoveArticle(article, isSaved)
        }
    }

    private fun saveOrRemoveArticle(article: Article, isSaved: Boolean) {
        val userId = firebaseAuth.currentUser?.email
        if (userId != null) {
            val documentId = article.url?.replace("[^A-Za-z0-9]".toRegex(), "_")
                ?: UUID.randomUUID().toString()

            val docRef = firestore.collection("users")
                .document(userId)
                .collection("saved_articles")
                .document(documentId)

            if (isSaved) {
                val data = hashMapOf(
                    "author" to article.author,
                    "description" to article.description,
                    "publishedAt" to article.publishedAt,
                    "title" to article.title,
                    "url" to article.url,
                    "urlToImage" to article.urlToImage
                )
                docRef.set(data)
                    .addOnSuccessListener {
                        savedArticles.add(article.url ?: "")
                        newsAdapter.notifyDataSetChanged()
                        Toast.makeText(context, "Article saved!", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Error saving article: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                docRef.delete()
                    .addOnSuccessListener {
                        savedArticles.remove(article.url)
                        newsAdapter.notifyDataSetChanged()
                        Toast.makeText(context, "Article removed!", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Error removing article: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter(
            onItemClick = { article ->
                val action = NewsListFragmentDirections
                    .actionNewsListFragmentToArticleDetailFragment(article)
                findNavController().navigate(action)
            },
            onItemSaveClick = { article, isSaved ->
                saveOrRemoveArticle(article, isSaved)
            }
        )
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
                            val updatedArticles = response.articles.map { article ->
                                article.copy(isSaved = savedArticles.contains(article.url))
                            }
                            newsAdapter.submitList(updatedArticles)
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