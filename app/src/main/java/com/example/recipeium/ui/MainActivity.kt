package com.example.recipeium.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.recipeium.R
import com.example.recipeium.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //region  variables
    private lateinit var navigation: NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    //endregion

    //region events
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        navigation = navHostFragment.findNavController()
        appBarConfiguration =
            AppBarConfiguration(setOf(R.id.homeFragment, R.id.favouriteFragment, R.id.jokeFragment))

        binding.bottomNavigationView.setupWithNavController(navigation)
        setupActionBarWithNavController(navigation, appBarConfiguration)


    }

    override fun onNavigateUp(): Boolean {
        return navigation.navigateUp() || super.onNavigateUp()
    }

    //endregion

    //region helper functions


    //endregion
}