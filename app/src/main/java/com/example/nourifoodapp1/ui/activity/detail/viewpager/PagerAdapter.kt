package com.example.nourifoodapp1.ui.activity.detail.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(
    private val bundle: Bundle,
    private val fragment: ArrayList<Fragment>,
    private val title: ArrayList<String>,
    private val fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return fragment.size
    }

    override fun getItem(position: Int): Fragment {
        fragment[position].arguments = bundle
        return fragment[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }


}