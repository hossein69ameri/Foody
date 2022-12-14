package com.example.nourifoodapp1.ui.fragment.recepies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foody.models.Result
import com.example.nourifoodapp1.R
import com.example.nourifoodapp1.databinding.RecipesRowLayoutBinding
import javax.inject.Inject

class FoodsAdapter @Inject constructor() : RecyclerView.Adapter<FoodsAdapter.ViewHolder>() {

    private lateinit var binding: RecipesRowLayoutBinding
    private var foodList = emptyList<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = RecipesRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        fun bind(item: Result) {
            binding.apply {
                   titleTextView.text = item.title
                    recipeImageView.load(item.image){
                        crossfade(true)
                        crossfade(500)
                    }
                    descriptionTextView.text = item.summary
                    heartTextView.text = item.aggregateLikes.toString()
                    clockTextView.text = item.readyInMinutes.toString()
                    if (item.vegan == true){
                    leafImageView.setBackgroundColor(ContextCompat.getColor(leafImageView.context, R.color.green))
                     leafTextView.setTextColor(ContextCompat.getColor(leafTextView.context,R.color.green))
                    }
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
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