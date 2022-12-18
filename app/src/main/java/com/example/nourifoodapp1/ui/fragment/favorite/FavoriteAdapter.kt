package com.example.nourifoodapp1.ui.fragment.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.nourifoodapp1.R
import com.example.nourifoodapp1.data.database.entity.FavoriteEntity
import com.example.nourifoodapp1.databinding.FavoriteRecipesRowLayoutBinding
import org.jsoup.Jsoup
import javax.inject.Inject

class FavoriteAdapter @Inject constructor() : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private lateinit var binding: FavoriteRecipesRowLayoutBinding
    private var foodList = emptyList<FavoriteEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = FavoriteRecipesRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        fun bind(item: FavoriteEntity) {
            binding.apply {
                   favoriteTitleTextView.text = item.result.title
                    favoriteRecipeImageView.load(item.result.image){
                        crossfade(true)
                        crossfade(500)
                        error(R.drawable.ic_error_placeholder)
                    }
                    val dec = Jsoup.parse(item.result.summary).text()
                    favoriteDescriptionTextView.text = dec
                    favoriteHeartTextView.text = item.result.aggregateLikes.toString()
                    favoriteClockTextView.text = item.result.readyInMinutes.toString()
                    if (item.result.vegan == true){
                    favoriteLeafImageView.setBackgroundColor(ContextCompat.getColor(favoriteLeafImageView.context, R.color.green))
                     favoriteLeafTextView.setTextColor(ContextCompat.getColor(favoriteLeafTextView.context,R.color.green))
                    }
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((FavoriteEntity) -> Unit)? = null

    fun setOnItemClickListener(listener: (FavoriteEntity) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(data: List<FavoriteEntity>) {
        val moviesDiffUtil = MoviesDiffUtils(foodList, data)
        val diffUtils = DiffUtil.calculateDiff(moviesDiffUtil)
        foodList = data
        diffUtils.dispatchUpdatesTo(this)
    }

    class MoviesDiffUtils(private val oldItem: List<FavoriteEntity>, private val newItem: List<FavoriteEntity>) : DiffUtil.Callback() {
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