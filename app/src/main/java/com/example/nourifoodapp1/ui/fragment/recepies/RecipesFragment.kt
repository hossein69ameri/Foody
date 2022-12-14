package com.example.nourifoodapp1.ui.fragment.recepies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nourifoodapp1.R
import com.example.nourifoodapp1.databinding.FragmentRecipesBinding
import com.example.nourifoodapp1.utils.NetworkResult
import com.example.nourifoodapp1.utils.observeOnce
import com.example.nourifoodapp1.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RecipesFragment : Fragment() {
    private lateinit var binding: FragmentRecipesBinding
    private val args by navArgs<RecipesFragmentArgs>()

    @Inject
    lateinit var foodsAdapter: FoodsAdapter

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRecipesBinding.inflate(layoutInflater)
        setupRecyclerView()
        readDatabase()

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_RecipesFragment_to_bottomSheetFragment)
        }

        return binding.root
    }

    private fun readDatabase() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observeOnce(viewLifecycleOwner) { database ->
                if (database.isNotEmpty() && !args.backToBtmSheet) {
                    Log.d("RecipesFragment", "readDatabase called!")
                    foodsAdapter.setData(database[0].foodRecipe.results)
                } else {
                    requestApiData()
                }
            }
        }
    }
    private fun setupRecyclerView() {
        binding.recyclerMain.adapter = foodsAdapter
        binding.recyclerMain.layoutManager = LinearLayoutManager(requireContext())
    }
    private fun requestApiData() {
        Log.d("RecipesFragment", "requestApiData called!")
        mainViewModel.getRecipes(mainViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let { foodsAdapter.setData(it.results) }
                }
                is NetworkResult.Error -> {
                    loadDataFromCache()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {

                }
            }
        }
    }
    private fun loadDataFromCache() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observe(viewLifecycleOwner) { database ->
                if (database.isNotEmpty()) {
                    foodsAdapter.setData(database[0].foodRecipe.results)
                }
            }
        }
    }
}