package com.example.nourifoodapp1.ui.fragment.recepies

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nourifoodapp1.R
import com.example.nourifoodapp1.databinding.FragmentRecipesBinding

import com.example.nourifoodapp1.utils.NetworkResult
import com.example.nourifoodapp1.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipesFragment : Fragment() {
    private lateinit var binding: FragmentRecipesBinding

    @Inject
    lateinit var foodsAdapter: FoodsAdapter

    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRecipesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
        mainViewModel.getRecipes(mainViewModel.applyQueries())
            mainViewModel.recipesResponse.observe(viewLifecycleOwner){
                foodsAdapter.setData(it.results)
                recyclerMain.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = foodsAdapter
                }
            }
        }
    }


}