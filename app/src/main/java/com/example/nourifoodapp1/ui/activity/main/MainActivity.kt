package com.example.nourifoodapp1.ui.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.nourifoodapp1.R
import com.example.nourifoodapp1.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.AppTheme)
        setContentView(binding!!.root)
        //init navHost
        navController = findNavController(R.id.navHost)
        //set title to app bar
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.RecipesFragment, R.id.FavoriteRecipesFragment, R.id.FoodJokeFragment))
        //set bottom nav
        binding?.bottomNav?.setupWithNavController(navController)
        //set action bar
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}