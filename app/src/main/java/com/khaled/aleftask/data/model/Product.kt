package com.khaled.aleftask.data.model

data class Product(
    val approved_by_admin: Int,
    val avg_rate: Any,
    val category: Category,
    val category_id: Int,
    val created_at: String,
    val description: String,
    val description_ar: String,
    val id: Int,
    val images: List<Image>,
    val is_active: Int,
    val main_image: Any,
    val prepare_time: Int,
    val prices: List<Price>,
    val provider_id: Int,
    val title: String,
    val title_ar: String,
    val updated_at: String,
    var isFavourite: Boolean = false
)