package com.example.nourifoodapp1.ui.fragment.favorite

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nourifoodapp1.R
import com.example.nourifoodapp1.databinding.FragmentFavoriteBinding
import com.example.nourifoodapp1.utils.setupRecyclerView
import com.example.nourifoodapp1.viewmodel.FavoriteViewModel
import com.google.android.material.snackbar.Snackbar
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

        setHasOptionsMenu(true)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteAll_favorite_recipes_menu){
            favoriteViewModel.deleteAllFavoriteRecipe()
            Snackbar.make(binding.root,"Delete All", Snackbar.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_recipes_menu,menu)
    }

}