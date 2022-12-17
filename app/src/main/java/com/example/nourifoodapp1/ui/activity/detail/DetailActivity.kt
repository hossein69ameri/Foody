package com.example.nourifoodapp1.ui.activity.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.example.nourifoodapp1.R
import com.example.nourifoodapp1.databinding.ActivityDetailBinding
import com.example.nourifoodapp1.ui.activity.detail.viewpager.ingredient.IngredientFragment
import com.example.nourifoodapp1.ui.activity.detail.viewpager.instruction.InstructionsFragment
import com.example.nourifoodapp1.ui.activity.detail.viewpager.overview.OverviewFragment
import com.example.nourifoodapp1.ui.activity.detail.viewpager.PagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val args : DetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        val adapter = PagerAdapter(bundle,fragments,titles,supportFragmentManager)
        binding.apply {
            viewPager.adapter = adapter
            tabLayout.setupWithViewPager(viewPager)
        }



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}