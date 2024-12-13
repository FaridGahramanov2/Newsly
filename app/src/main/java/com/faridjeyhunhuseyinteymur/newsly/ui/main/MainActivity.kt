package com.faridjeyhunhuseyinteymur.newsly.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.faridjeyhunhuseyinteymur.newsly.R
import com.faridjeyhunhuseyinteymur.newsly.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        binding.bottomNavigationView.setOnItemReselectedListener { item ->
            when (item.itemId) {
                R.id.newsListFragment -> {
                    navController.popBackStack(R.id.newsListFragment, false)
                }
                R.id.categoriesFragment -> {
                    navController.popBackStack(R.id.categoriesFragment, false)
                }
                R.id.broadcastingFragment -> {
                    navController.popBackStack(R.id.broadcastingFragment, false)
                }
            }
        }
    }
}
