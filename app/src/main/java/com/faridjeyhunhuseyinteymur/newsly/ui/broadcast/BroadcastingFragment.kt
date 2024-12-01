package com.faridjeyhunhuseyinteymur.newsly.ui.broadcast

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.faridjeyhunhuseyinteymur.newsly.databinding.FragmentBroadcastingBinding
import com.google.android.material.snackbar.Snackbar

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
                handleChannelClick("BBC News", "https://www.bbc.com/news")
            }

            cnnCard.setOnClickListener {
                handleChannelClick("CNN News", "https://www.cnn.com")
            }
        }
    }

    private fun handleChannelClick(channelName: String, url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(intent)

                Toast.makeText(
                    context,
                    "Opening $channelName...",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                showError("No browser app found to open $channelName")
            }
        } catch (e: Exception) {
            showError("Unable to open $channelName: ${e.localizedMessage}")
        }
    }

    private fun showError(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_LONG
        ).setAction("OK") {

        }.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = BroadcastingFragment()
    }
}