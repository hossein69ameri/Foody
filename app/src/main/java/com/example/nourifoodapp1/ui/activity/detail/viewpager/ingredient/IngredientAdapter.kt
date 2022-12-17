package com.example.nourifoodapp1.ui.activity.detail.viewpager.ingredient

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foody.models.ExtendedIngredient
import com.example.nourifoodapp1.R
import com.example.nourifoodapp1.databinding.IngredientsRowLayoutBinding
import com.example.nourifoodapp1.utils.BASE_IMAGE_URL
import javax.inject.Inject

class IngredientAdapter @Inject constructor() : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    private lateinit var binding: IngredientsRowLayoutBinding
    private var foodList = emptyList<ExtendedIngredient>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = IngredientsRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //getItem from PagingDataAdapter
        holder.bind(foodList[position])
        //Not duplicate items
        holder.setIsRecyclable(false)
        //set item

    }

    override fun getItemCount() = foodList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "ResourceType")
        fun bind(item: ExtendedIngredient) {
            binding.apply {
                ingredientName.text = item.name
                ingredientAmount.text = item.amount.toString()
                ingredientConsistency.text = item.consistency
                ingredientOriginal.text = item.original
                ingredientUnit.text = item.unit
                ingredientImageView.load(BASE_IMAGE_URL+item.image){
                    crossfade(true)
                    crossfade(500)
                    error(R.drawable.ic_error_placeholder)
                }
            }
        }
    }

    private var onItemClickListener: ((ExtendedIngredient) -> Unit)? = null

    fun setOnItemClickListener(listener: (ExtendedIngredient) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(data: List<ExtendedIngredient>) {
        val moviesDiffUtil = MoviesDiffUtils(foodList, data)
        val diffUtils = DiffUtil.calculateDiff(moviesDiffUtil)
        foodList = data
        diffUtils.dispatchUpdatesTo(this)
    }

    class MoviesDiffUtils(private val oldItem: List<ExtendedIngredient>, private val newItem: List<ExtendedIngredient>) : DiffUtil.Callback() {
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