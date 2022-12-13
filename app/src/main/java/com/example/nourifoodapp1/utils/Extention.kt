package com.example.nourifoodapp1.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView


fun RecyclerView.setupRecyclerView(layoutManager: RecyclerView.LayoutManager, adapter: RecyclerView.Adapter<*>) {
    this.layoutManager = layoutManager
    this.adapter = adapter
}

fun View.isVisibility(isShow: Boolean, view: View){
    if (isShow){
        this.visibility = View.VISIBLE
        view.visibility = View.GONE
    }else{
        this.visibility = View.GONE
        view.visibility = View.VISIBLE
    }
}