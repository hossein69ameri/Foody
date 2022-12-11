package com.example.nourifoodapp1.ui.fragment.recepies

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foody.models.Result
import com.example.nourifoodapp1.R
import com.example.nourifoodapp1.databinding.RecipesRowLayoutBinding
import javax.inject.Inject

class RecepisAdapter @Inject constructor() : RecyclerView.Adapter<RecepisAdapter.ViewHolder>() {

    private lateinit var binding: RecipesRowLayoutBinding
    private var foodList = emptyList<Result>()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = RecipesRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //getItem from PagingDataAdapter
        holder.bind(foodList[position])
        //Not duplicate items
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = foodList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "ResourceType")
        fun bind(result: Result) {
            binding.apply {
                recipeImageView.load(result.image){
                    crossfade(true)
                    crossfade(500)
                }
                titleTextView.text = result.title
                descriptionTextView.text = result.summary
                heartTextView.text = result.aggregateLikes.toString()
                clockTextView.text = result.readyInMinutes.toString()
                if (result.vegan){
                    leafTextView.setTextColor(ContextCompat.getColor(context, R.color.green))
                    leafImageView.setBackgroundResource(ContextCompat.getColor(context,R.color.green))
                }
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(result)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((Result) -> Unit)? = null

    fun setOnItemClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(data: List<Result>) {
        val moviesDiffUtil = MoviesDiffUtils(foodList, data)
        val diffUtils = DiffUtil.calculateDiff(moviesDiffUtil)
        foodList = data
        diffUtils.dispatchUpdatesTo(this)
    }

    class MoviesDiffUtils(private val oldItem: List<Result>, private val newItem: List<Result>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }
    }
}