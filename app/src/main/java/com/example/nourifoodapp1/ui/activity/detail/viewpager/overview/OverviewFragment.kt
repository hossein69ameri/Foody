package com.example.nourifoodapp1.ui.activity.detail.viewpager.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import coil.load
import com.example.foody.models.Result
import com.example.nourifoodapp1.R
import com.example.nourifoodapp1.databinding.FragmentOverviewBinding
import dagger.hilt.android.AndroidEntryPoint
import org.jsoup.Jsoup

@AndroidEntryPoint
class OverviewFragment : Fragment() {
    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentOverviewBinding.inflate(layoutInflater)

        //get bundle
        val args = arguments
        val bundle: Result? = args?.getParcelable("recipeBundle")

        binding.apply {
            //html parser
            val desc = bundle?.summary?.let { Jsoup.parse(it).text() }
            summaryTextView.text = desc

            //set data
            mainImageView.load(bundle?.image)
            titleTextView.text = bundle?.title
            likesTextView.text = bundle?.aggregateLikes.toString()
            timeTextView.text = bundle?.readyInMinutes.toString()
            if (bundle?.vegetarian == true) {
                vegetarianImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
                vegetarianTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green)) }

            if (bundle?.vegan == true) {
                veganImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
                veganTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green)) }

            if (bundle?.glutenFree == true) {
                glutenFreeImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
                glutenFreeTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green)) }

            if (bundle?.dairyFree == true) {
                dairyFreeImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
                dairyFreeTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green)) }

            if (bundle?.veryHealthy == true) {
                healthyImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
                healthyTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green)) }

            if (bundle?.cheap == true) {
                cheapImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
                cheapTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green)) }

        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}