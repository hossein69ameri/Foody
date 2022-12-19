package com.example.nourifoodapp1.ui.activity.detail.viewpager.instruction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.foody.models.Result
import com.example.nourifoodapp1.databinding.FragmentInstructionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InstructionsFragment : Fragment() {
    private var _binding: FragmentInstructionsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentInstructionsBinding.inflate(layoutInflater)

        //get bundle
        val args = arguments
        val bundle: Result? = args?.getParcelable("recipeBundle")

        //set data in webView
        binding.instructionsWebView.webViewClient = object : WebViewClient() {}
        val webViewClient: String = bundle!!.sourceUrl
        binding.instructionsWebView.loadUrl(webViewClient)


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}