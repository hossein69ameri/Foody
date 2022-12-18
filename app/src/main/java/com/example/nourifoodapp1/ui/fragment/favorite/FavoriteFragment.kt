package com.example.nourifoodapp1.ui.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nourifoodapp1.databinding.FragmentFavoriteBinding
import com.example.nourifoodapp1.utils.setupRecyclerView
import com.example.nourifoodapp1.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding

    @Inject
    lateinit var favoriteAdapter: FavoriteAdapter

    private val favoriteViewModel : FavoriteViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)

        //get favorite list
        favoriteViewModel.readFavoriteRecipeData.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                binding.apply {
                    noDataImageView.visibility = View.GONE
                    noDataTextView.visibility = View.GONE
                    favoriteRecipesRecyclerView.visibility = View.VISIBLE
                }
                favoriteAdapter.setData(it)
                binding.favoriteRecipesRecyclerView.setupRecyclerView(LinearLayoutManager(requireContext()),favoriteAdapter)
            }else {
                binding.apply {
                    noDataImageView.visibility = View.VISIBLE
                    noDataTextView.visibility = View.VISIBLE
                    favoriteRecipesRecyclerView.visibility = View.GONE
                }
            }

        }

        //on click to item
        favoriteAdapter.setOnItemClickListener {
            val action = FavoriteFragmentDirections.actionFavoriteRecipesFragmentToDetailActivity(it.result)
            findNavController().navigate(action)
        }

        return binding.root
    }

}