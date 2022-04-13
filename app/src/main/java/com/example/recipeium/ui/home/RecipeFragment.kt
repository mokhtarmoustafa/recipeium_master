package com.example.recipeium.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipeium.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {
    //region variables
    private lateinit var binding: FragmentRecipeBinding
    //endregion


    //region events
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRecipeBinding.inflate(inflater, container, false)
        
        binding.shimmerViewContainer.startShimmer()
        return binding.root
    }

    //endregion

    companion object {
        private const val TAG = "RecipeFragment"
    }
}