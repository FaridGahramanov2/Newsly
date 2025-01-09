package com.faridjeyhunhuseyinteymur.newsly.ui.broadcast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.faridjeyhunhuseyinteymur.newsly.databinding.FragmentBroadcastingBinding

class BroadcastingFragment : Fragment() {
    private var _binding: FragmentBroadcastingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBroadcastingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.apply {
            bbcCard.setOnClickListener {
                openInWebView("https://www.bbc.com/news")
            }

            cnnCard.setOnClickListener {
                openInWebView("https://www.cnn.com")
            }
        }
    }

    private fun openInWebView(url: String) {
        binding.webView.apply {
            visibility = View.VISIBLE
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}