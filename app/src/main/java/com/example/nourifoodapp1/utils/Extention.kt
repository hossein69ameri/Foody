package com.example.nourifoodapp1.utils

import android.view.View
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.nourifoodapp1.data.database.entity.FoodJokeEntity
import com.example.nourifoodapp1.data.model.FoodJoke

//recycler view
fun RecyclerView.setupRecyclerView(layoutManager: RecyclerView.LayoutManager, adapter: RecyclerView.Adapter<*>) {
    this.layoutManager = layoutManager
    this.adapter = adapter
}

//visibility
fun View.isVisibility(isShow: Boolean, view: View){
    if (isShow){
        this.visibility = View.VISIBLE
        view.visibility = View.GONE
    }else{
        this.visibility = View.GONE
        view.visibility = View.VISIBLE
    }
}

//liveData
fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            removeObserver(this)
            observer.onChanged(t)
        }
    })
}

fun setErrorViewsVisibility(
    view: View,
    apiResponse: NetworkResult<FoodJoke>?,
    database: List<FoodJokeEntity>?
){
    if(database != null){
        if(database.isEmpty()){
            view.visibility = View.VISIBLE
            if(view is TextView){
                if(apiResponse != null){
                    view.text = apiResponse.message.toString()
                }
            }
        }
    }
    if(apiResponse is NetworkResult.Success){
        view.visibility = View.INVISIBLE
    }
}