package com.example.nourifoodapp1.ui.activity.detail.viewpager.ingredient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foody.models.Result
import com.example.nourifoodapp1.databinding.FragmentIngredientBinding
import com.example.nourifoodapp1.utils.setupRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IngredientFragment : Fragment() {
    private var _binding: FragmentIngredientBinding ?= null
    private val binding get() = _binding!!
    @Inject
    lateinit var ingredientAdapter: IngredientAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       _binding = FragmentIngredientBinding.inflate(layoutInflater)

        //get bundle
        val args = arguments
        val bundle : Result? = args?.getParcelable("recipeBundle")

        //set data in adapter
        bundle?.extendedIngredients?.let { ingredientAdapter.setData(it) }

        //set data in recycler
        binding.ingredientsRecyclerview.setupRecyclerView(LinearLayoutManager(requireContext()),ingredientAdapter)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}