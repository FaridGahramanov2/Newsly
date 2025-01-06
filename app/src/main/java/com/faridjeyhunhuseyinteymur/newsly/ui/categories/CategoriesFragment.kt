package com.faridjeyhunhuseyinteymur.newsly.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.faridjeyhunhuseyinteymur.newsly.R
import com.faridjeyhunhuseyinteymur.newsly.databinding.FragmentCategoriesBinding
import com.google.android.material.chip.Chip

class CategoriesFragment : Fragment() {
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CategoriesViewModel by activityViewModels()

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
    }

    private fun setupCategories() {
        val categories = listOf(
            "Business",
            "Entertainment",
            "Health",
            "Science",
            "Sports",
            "Technology",
            "General"
        )
        binding.categoryChipGroup.removeAllViews()

        categories.forEach { category ->
            val chip = Chip(requireContext()).apply {
                text = category
                isCheckable = true
                isChecked = category == viewModel.selectedCategory.value
                setChipBackgroundColorResource(
                    if (isChecked) R.color.chip_checked_background
                    else R.color.chip_unchecked_background
                )

                setOnCheckedChangeListener { _, isChecked ->
                    setChipBackgroundColorResource(
                        if (isChecked) R.color.chip_checked_background
                        else R.color.chip_unchecked_background
                    )
                }

                setOnClickListener {
                    val apiCategory = category.lowercase()
                    viewModel.setSelectedCategory(category)
                    updateChipSelection(category)
                    findNavController().navigate(
                        R.id.newsListFragment,
                        bundleOf("category" to apiCategory)
                    )
                }
            }
            binding.categoryChipGroup.addView(chip)
        }

        viewModel.selectedCategory.observe(viewLifecycleOwner) { selectedCategory ->
            updateChipSelection(selectedCategory)
        }
    }

    private fun updateChipSelection(selectedCategory: String) {
        for (i in 0 until binding.categoryChipGroup.childCount) {
            val chip = binding.categoryChipGroup.getChildAt(i) as Chip
            val isSelected = chip.text == selectedCategory
            chip.isChecked = isSelected
            chip.setChipBackgroundColorResource(
                if (isSelected) R.color.chip_checked_background  // Removed *
                else R.color.chip_unchecked_background
            )
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.selectedCategory.value?.let { selectedCategory ->
            updateChipSelection(selectedCategory)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}