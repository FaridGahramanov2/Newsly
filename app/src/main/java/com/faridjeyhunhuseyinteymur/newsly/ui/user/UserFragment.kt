package com.faridjeyhunhuseyinteymur.newsly.ui.user

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faridjeyhunhuseyinteymur.newsly.R
import com.faridjeyhunhuseyinteymur.newsly.data.model.Article
import com.faridjeyhunhuseyinteymur.newsly.ui.auth.LoginActivity
import com.faridjeyhunhuseyinteymur.newsly.ui.news.list.NewsAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

class UserFragment : Fragment() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        val logoutButton = view.findViewById<View>(R.id.logoutButton)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        userAdapter = NewsAdapter(
            onItemClick = { article ->
                val action = UserFragmentDirections.actionUserFragmentToArticleDetailFragment(article)
                findNavController().navigate(action)
            },
            onItemSaveClick = { article, isSaved ->
                handleSaveToggle(article, isSaved)
            }
        )
        recyclerView.adapter = userAdapter

        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        loadUserArticles()

        logoutButton.setOnClickListener {
            logoutUser()
        }

        return view
    }

    private fun loadUserArticles() {
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            val userId = currentUser.email
            firestore.collection("users")
                .document(userId!!)
                .collection("saved_articles")
                .addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        Toast.makeText(context, "Error loading articles: ${e.message}", Toast.LENGTH_SHORT).show()
                        return@addSnapshotListener
                    }
                    if (snapshot != null && !snapshot.isEmpty) {
                        val articles = snapshot.documents.map {
                            it.toObject(Article::class.java)!!.apply { isSaved = true }
                        }
                        userAdapter.submitList(articles)
                    } else {
                        userAdapter.submitList(emptyList())
                    }
                }
        } else {
            Toast.makeText(context, "User not logged in!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleSaveToggle(article: Article, isSaved: Boolean) {
        val userId = firebaseAuth.currentUser?.email
        if (userId != null) {
            val sanitizedUrl = article.url?.replace("[^A-Za-z0-9]".toRegex(), "_") ?: UUID.randomUUID().toString()

            val docRef = firestore.collection("users")
                .document(userId)
                .collection("saved_articles")
                .document(sanitizedUrl)

            if (isSaved) {
                // Save article
                docRef.set(article)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Article saved!", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Error saving article: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                // Remove article
                docRef.delete()
                    .addOnSuccessListener {
                        Toast.makeText(context, "Article removed!", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Error removing article: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        } else {
            Toast.makeText(context, "User not authenticated", Toast.LENGTH_SHORT).show()
        }
    }

    private fun logoutUser() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(activity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}