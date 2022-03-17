package com.example.recipeium.ui.main

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.recipeium.adapter.RecipeAdapter
import com.example.recipeium.databinding.FragmentHomeBinding
import com.example.recipeium.models.Result
import com.example.recipeium.util.NetworkResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class HomeFragment : Fragment(), RecipeAdapter.Interaction {
//region variables

    //endregion
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: RecipeAdapter
    //region events

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        getRecipes()
        return binding.root
    }

    override fun onItemSelected(position: Int, item: Result) {
        Toast.makeText(requireContext(), "Item ${item.title} is clicked", Toast.LENGTH_SHORT).show()
    }


    //endregion

    //region helper functions
    private fun showShimmerEffect() {
        binding.shimmerView.visibility=View.VISIBLE
        binding.recyclerView.visibility=View.INVISIBLE
        binding.shimmerView.startShimmer()
    }

    private fun hideShimmerEffect() {
        binding.shimmerView.stopShimmer()
        binding.shimmerView.visibility=View.INVISIBLE
        binding.recyclerView.visibility=View.VISIBLE
    }

    private fun getRecipes() {
        adapter = RecipeAdapter(this)
        binding.recyclerView.adapter=adapter

        viewModel.resultFlow.observe(viewLifecycleOwner, Observer { result ->
            Log.d(TAG, "getRecipes: $result")
            when (result) {
                is NetworkResponse.Loading -> {
                    showShimmerEffect()
                }
                is NetworkResponse.Success -> {
                    binding.groupEmptyData.visibility = View.INVISIBLE
                    hideShimmerEffect()
                    adapter.submitList(result.data)

                }
                is NetworkResponse.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(
                        requireContext(),
                        "${result.exception.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        })
    }

    //endregion
    companion object {
        private const val TAG = "HomeFragment"
    }
}