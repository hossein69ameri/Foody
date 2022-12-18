package com.example.nourifoodapp1.ui.fragment.foodjoke

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nourifoodapp1.databinding.FragmentFoodjokeBinding
import com.example.nourifoodapp1.utils.API_KEY
import com.example.nourifoodapp1.utils.NetworkResult
import com.example.nourifoodapp1.utils.isVisibility
import com.example.nourifoodapp1.utils.setupRecyclerView
import com.example.nourifoodapp1.viewmodel.FoodJokeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FoodJokeFragment : Fragment() {
    private lateinit var binding: FragmentFoodjokeBinding

    private val foodJokeViewModel : FoodJokeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFoodjokeBinding.inflate(layoutInflater)

        //get data from api or cache
        foodJokeViewModel.getFoodJoke(API_KEY)
        foodJokeViewModel.foodJokeResponse.observe(viewLifecycleOwner){ response ->
            when (response) {
                is NetworkResult.Success -> {
                    binding.apply {
                        progressBar.isVisibility(false,foodJokeCardView)
                    }
                    Log.e("TAG", "onViewCreated: ${response.data!!.text}", )
                    binding.foodJokeTextView.text = response.data?.text
                }
                is NetworkResult.Error -> {
                    loadDataFromCache()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.apply {
                        progressBar.isVisibility(false,foodJokeCardView)
                    }
                }
                is NetworkResult.Loading -> {
                    binding.apply {
                        progressBar.isVisibility(true,foodJokeCardView)
                    }
                }
            }
        }

        return binding.root
    }

    private fun loadDataFromCache() {
        lifecycleScope.launch{
            foodJokeViewModel.readFoodJoke.observe(viewLifecycleOwner){
                if (it.isNotEmpty() && it != null){
                    binding.apply {
                        progressBar.isVisibility(false,foodJokeCardView)
                    }
                    binding.foodJokeTextView.text = it[0].foodJoke.text
                }
            }
        }
    }
}