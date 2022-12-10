package com.example.nourifoodapp1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.nourifoodapp1.R
import com.example.nourifoodapp1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //init navHost
        navController = findNavController(R.id.navHost)
        //set title to app bar
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.RecipesFragment, R.id.FavoriteRecipesFragment, R.id.FoodJokeFragment))
        //set bottom nav
        binding.bottomNav.setupWithNavController(navController)
        //set action bar
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}