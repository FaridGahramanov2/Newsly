package com.faridjeyhunhuseyinteymur.newsly.ui.categories

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.faridjeyhunhuseyinteymur.newsly.R
import com.faridjeyhunhuseyinteymur.newsly.databinding.FragmentCategoriesBinding
import com.google.android.material.chip.Chip


class CategoriesFragment : Fragment() {
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CategoriesViewModel by viewModels()

    private val categories = listOf(
        "business",
        "politics",
        "technology",
        "sports",
        "entertainment"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCategories()
        observeCategory()
    }

    private fun observeCategory() {
        viewModel.category.observe(viewLifecycleOwner) { category ->
            val bundle = bundleOf("category" to category)
            findNavController().navigate(
                R.id.action_categories_to_newsList,
                bundle
            )
        }
    }

    private fun setupCategories() {
        binding.categoryChipGroup.removeAllViews()

        categories.forEach { category ->
            val chip = Chip(requireContext()).apply {
                text = category.replaceFirstChar { it.uppercase() }
                isCheckable = true
                chipBackgroundColor = getChipBackgroundColor()
                setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))

                setOnClickListener {
                    viewModel.setCategory(category)
                }
            }
            binding.categoryChipGroup.addView(chip)
        }
    }

    private fun getChipBackgroundColor(): ColorStateList {
        val states = arrayOf(
            intArrayOf(android.R.attr.state_checked),
            intArrayOf(-android.R.attr.state_checked)
        )

        val colors = intArrayOf(
            ContextCompat.getColor(requireContext(), R.color.purple_500),
            ContextCompat.getColor(requireContext(), R.color.purple_700)
        )

        return ColorStateList(states, colors)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}