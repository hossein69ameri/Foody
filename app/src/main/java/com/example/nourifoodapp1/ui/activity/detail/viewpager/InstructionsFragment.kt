package com.example.nourifoodapp1.ui.activity.detail.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nourifoodapp1.R
import com.example.nourifoodapp1.databinding.FragmentInstructionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InstructionsFragment : Fragment() {
private lateinit var binding: FragmentInstructionsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentInstructionsBinding.inflate(layoutInflater)


        return binding.root
    }
}