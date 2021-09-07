package com.khaled.aleftask.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.khaled.aleftask.data.model.Product
import com.khaled.aleftask.databinding.AdapterProductItemBinding
import com.khaled.aleftask.util.toggleFavourite

class CustomViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)
class ProductAdapter : ListAdapter<Product, CustomViewHolder>(Companion) {
    companion object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterProductItemBinding.inflate(inflater, parent, false)
        return CustomViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentProduct = getItem(position)
        val itemBinding = holder.binding as AdapterProductItemBinding
        val context = holder.itemView.context
        itemBinding.apply {
            Glide.with(context)
                .load(currentProduct.images.firstOrNull()?.image_name)
                .into(productItemImage)
            productItemTitle.text = currentProduct.title
            productItemDescription.text = currentProduct.description
            favouriteButton.toggleFavourite(currentProduct.isFavourite)
            favouriteButton.setOnClickListener {
                currentProduct.isFavourite = !currentProduct.isFavourite
                favouriteButton.toggleFavourite(currentProduct.isFavourite)
            }
        }
    }
}