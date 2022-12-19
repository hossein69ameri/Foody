package com.example.nourifoodapp1.ui.fragment.foodjoke

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nourifoodapp1.R
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
    private var _binding: FragmentFoodjokeBinding? = null
    private val binding get() = _binding!!
    private var foodJoke = "Empty"

    private val foodJokeViewModel: FoodJokeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFoodjokeBinding.inflate(layoutInflater)

        setHasOptionsMenu(true)

        //get data from api or cache
        foodJokeViewModel.getFoodJoke(API_KEY)
        foodJokeViewModel.foodJokeResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    binding.apply {
                        progressBar.isVisibility(false, foodJokeCardView)
                    }
                    Log.e("TAG", "onViewCreated: ${response.data!!.text}")
                    binding.foodJokeTextView.text = response.data.text
                    foodJoke = response.data.text
                }
                is NetworkResult.Error -> {
                    loadDataFromCache()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.apply {
                        progressBar.isVisibility(false, foodJokeCardView)
                    }
                }
                is NetworkResult.Loading -> {
                    binding.apply {
                        progressBar.isVisibility(true, foodJokeCardView)
                    }
                }
            }
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.food_joke_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.share_food_joke_menu) {
            val shareIntent = Intent().apply {
                this.action = Intent.ACTION_SEND
                this.putExtra(Intent.EXTRA_TEXT, foodJoke)
                this.type = "text/plain"
            }
            startActivity(shareIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadDataFromCache() {
        lifecycleScope.launch {
            foodJokeViewModel.readFoodJoke.observe(viewLifecycleOwner) {
                if (it.isNotEmpty() && it != null) {
                    binding.apply {
                        progressBar.isVisibility(false, foodJokeCardView)
                    }
                    binding.foodJokeTextView.text = it[0].foodJoke.text
                    foodJoke = it[0].foodJoke.text
                } else {
                    foodJokeViewModel.isShow.observe(viewLifecycleOwner) {
                        if (it) {
                            binding.apply {
                                foodJokeErrorImageView.visibility = View.VISIBLE
                                foodJokeErrorTextView.visibility = View.VISIBLE
                                foodJokeCardView.visibility = View.GONE
                            }
                        } else {
                            binding.apply {
                                foodJokeErrorImageView.visibility = View.GONE
                                foodJokeErrorTextView.visibility = View.GONE
                                foodJokeCardView.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}