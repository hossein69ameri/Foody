package com.example.nourifoodapp1.ui.activity.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.example.nourifoodapp1.R
import com.example.nourifoodapp1.data.database.entity.FavoriteEntity
import com.example.nourifoodapp1.databinding.ActivityDetailBinding
import com.example.nourifoodapp1.ui.activity.detail.viewpager.ingredient.IngredientFragment
import com.example.nourifoodapp1.ui.activity.detail.viewpager.instruction.InstructionsFragment
import com.example.nourifoodapp1.ui.activity.detail.viewpager.overview.OverviewFragment
import com.example.nourifoodapp1.ui.activity.detail.viewpager.PagerAdapter
import com.example.nourifoodapp1.viewmodel.FavoriteViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    private val args : DetailActivityArgs by navArgs()
    private val favoriteViewModel : FavoriteViewModel by viewModels()
    private var saveRecipe = false
    private var savedID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this@DetailActivity,R.color.white))

        //add fragment
        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientFragment())
        fragments.add(InstructionsFragment())
        //add name
        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Instructions")
        titles.add("Ingredient")
        //bundle
        val bundle = Bundle()
        bundle.putParcelable("recipeBundle",args.result)
        //adapter and tabLayout
        val pagerAdapter = PagerAdapter(bundle,fragments,this)
        binding.viewPager2.apply { adapter = pagerAdapter }
        TabLayoutMediator(binding.tabLayout,binding.viewPager2){ tab , position -> tab.text = titles[position] }.attach()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.details_menu,menu)
        val menuItem = menu.findItem(R.id.save_to_favorites_menu)
        checkSaveItem(menuItem)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        } else if (item.itemId == R.id.save_to_favorites_menu && !saveRecipe){
            saveToFavoriteRecipe(item)
        }else if (item.itemId == R.id.save_to_favorites_menu && saveRecipe){
            removeFavoriteRecipe(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun checkSaveItem(menuItem: MenuItem) {
        favoriteViewModel.readFavoriteRecipeData.observe(this){
            for (savedRecipe in it){
                if (savedRecipe.result.id == args.result.id){
                    changeFavoriteColor(menuItem,R.color.yellow)
                    savedID = savedRecipe.id
                    saveRecipe = true
                }else {
                    changeFavoriteColor(menuItem,R.color.white)
                }
            }
        }
    }

    private fun saveToFavoriteRecipe(item: MenuItem) {
        val entity = FavoriteEntity(0,args.result)
        favoriteViewModel.insertFavoriteRecipe(entity)
        changeFavoriteColor(item,R.color.yellow)
        snackBarShow("Saved")
        saveRecipe = true
    }

    private fun removeFavoriteRecipe(item: MenuItem) {
        val entity = FavoriteEntity(savedID,args.result)
        favoriteViewModel.deleteFavoriteRecipe(entity)
        changeFavoriteColor(item,R.color.white)
        snackBarShow("Deleted")
        saveRecipe = false
    }


    private fun snackBarShow(s: String) {
        Snackbar.make(this,binding.root,s,Snackbar.LENGTH_SHORT).show()
    }


    private fun changeFavoriteColor(item: MenuItem, yellow: Int) {
        item.icon!!.setTint(ContextCompat.getColor(this,yellow))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}