package com.example.nourifoodapp1.ui.fragment.recepies

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nourifoodapp1.R
import com.example.nourifoodapp1.databinding.FragmentRecipesBinding
import com.example.nourifoodapp1.utils.NetworkListener
import com.example.nourifoodapp1.utils.NetworkResult
import com.example.nourifoodapp1.utils.observeOnce
import com.example.nourifoodapp1.utils.setupRecyclerView
import com.example.nourifoodapp1.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class RecipesFragment : Fragment(), SearchView.OnQueryTextListener {
    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<RecipesFragmentArgs>()
    private lateinit var networkListener: NetworkListener

    @Inject
    lateinit var foodsAdapter: FoodsAdapter

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRecipesBinding.inflate(layoutInflater)


        setHasOptionsMenu(true)
//        setupRecyclerView()
        //Check Internet Connection
        lifecycleScope.launchWhenStarted {
            networkListener = NetworkListener()
            networkListener.checkNetworkAvailability(requireContext()).collect{
            mainViewModel.networkStatus = it
                mainViewModel.netWorkConnection()
                readDatabase()
            }
        }

        //check backOnline
        mainViewModel.readBackOnline.observe(viewLifecycleOwner){ mainViewModel.backOnline = it }

        //click in fab
        binding.floatingActionButton.setOnClickListener {
            if (mainViewModel.networkStatus){
                findNavController().navigate(R.id.action_RecipesFragment_to_bottomSheetFragment)
            }else{
                mainViewModel.netWorkConnection()
            }

        }

        //click in adapter
        foodsAdapter.setOnItemClickListener {
        val direction = RecipesFragmentDirections.actionRecipesFragmentToDetailActivity(it)
            findNavController().navigate(direction)
        }

        return binding.root
    }

    //search
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.recepie_menu,menu)
        val search = menu.findItem(R.id.search_menu)
        val searchView = search.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query !=null) {
            searchData(query)
        }
        return true
    }
    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    //search
    private fun searchData(query: String){
        mainViewModel.searchResepies(mainViewModel.applySearchQueries(query))
        mainViewModel.searchData.observe(viewLifecycleOwner){ response ->
            when(response){
                is NetworkResult.Success -> {
                    val foodRecipe = response.data
                    foodRecipe?.let { foodsAdapter.setData(it.results) }
                    binding.recyclerMain.setupRecyclerView(LinearLayoutManager(requireContext()),foodsAdapter)
                }
                is NetworkResult.Error -> {
                    loadDataFromCache()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                }
            }
        }
    }
    //read data from database
    private fun readDatabase() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observeOnce(viewLifecycleOwner) { database ->
                if (database.isNotEmpty() && !args.backToBtmSheet) {
                    Log.d("RecipesFragment", "readDatabase called!")
                    foodsAdapter.setData(database[0].foodRecipe.results)
                    binding.recyclerMain.setupRecyclerView(LinearLayoutManager(requireContext()),foodsAdapter)
                } else {
                    requestApiData()
                }
            }
        }
    }

    //read data from api
    private fun requestApiData() {
        Log.d("RecipesFragment", "requestApiData called!")
        mainViewModel.getRecipes(mainViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let { foodsAdapter.setData(it.results) }
                    binding.recyclerMain.setupRecyclerView(LinearLayoutManager(requireContext()),foodsAdapter)
                }
                is NetworkResult.Error -> {
                    loadDataFromCache()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {

                }
            }
        }
    }


    private fun loadDataFromCache() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observe(viewLifecycleOwner) { database ->
                if (database.isNotEmpty()) {
                    foodsAdapter.setData(database[0].foodRecipe.results)
                    binding.recyclerMain.setupRecyclerView(LinearLayoutManager(requireContext()),foodsAdapter)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}