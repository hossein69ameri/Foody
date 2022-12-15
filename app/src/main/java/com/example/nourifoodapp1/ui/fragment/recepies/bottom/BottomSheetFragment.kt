package com.example.nourifoodapp1.ui.fragment.recepies.bottom

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.nourifoodapp1.R
import com.example.nourifoodapp1.databinding.FragmentBottomSheetBinding
import com.example.nourifoodapp1.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class BottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBottomSheetBinding

    private val mainViewModel: MainViewModel by viewModels()
    private var mealTypeChip = "main course"
    private var dietTypeChip = "gluten free"
    private var mealTypeChipId = 0
    private var dietTypeChipId = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBottomSheetBinding.inflate(layoutInflater)

        //save state chip
        mainViewModel.readMealAndDiet.asLiveData().observe(viewLifecycleOwner){
            mealTypeChip = it.selectedMealType
            dietTypeChip = it.selectedDietType
            updateChip(it.selectedMealTypeId,binding.mealTypeChipGroup)
            updateChip(it.selectedDietTypeId,binding.dietTypeChipGroup)
        }

        //get value chip
        binding.mealTypeChipGroup.setOnCheckedChangeListener { group , selectedChipId ->
            val chip = group.findViewById<Chip>(selectedChipId)
            val selectedMealType = chip.text.toString().toLowerCase(Locale.ROOT)
            mealTypeChip = selectedMealType
            mealTypeChipId = selectedChipId
        }

        //get value chip
        binding.dietTypeChipGroup.setOnCheckedChangeListener { group , selectedChipId ->
            val chip = group.findViewById<Chip>(selectedChipId)
            val selectedDietType = chip.text.toString().toLowerCase(Locale.ROOT)
            dietTypeChip = selectedDietType
            dietTypeChipId = selectedChipId
        }

        //click in apply
        binding.applyBtn.setOnClickListener {
            mainViewModel.saveMealAndDiet(mealTypeChip,mealTypeChipId,dietTypeChip,dietTypeChipId)
            val action =
                BottomSheetFragmentDirections.actionBottomSheetFragmentToRecipesFragment(true)
            findNavController().navigate(action)
        }

        return binding.root
    }

    private fun updateChip(chipId: Int, chipGroup: ChipGroup) {
    if (chipId != 0){
            try {
                chipGroup.findViewById<Chip>(chipId).isChecked = true
            }catch (e : Exception){
                Log.d("TAG", e.message.toString())
            }
       }
    }
}