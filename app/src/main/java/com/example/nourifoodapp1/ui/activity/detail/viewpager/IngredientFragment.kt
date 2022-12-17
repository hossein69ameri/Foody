package com.example.nourifoodapp1.ui.activity.detail.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nourifoodapp1.R
import com.example.nourifoodapp1.databinding.FragmentIngredientBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IngredientFragment : Fragment() {
    private lateinit var binding: FragmentIngredientBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       binding = FragmentIngredientBinding.inflate(layoutInflater)


        return binding.root
    }
}