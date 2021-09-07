package com.khaled.aleftask.data.model

data class Image(
    val admin_id: Int,
    val created_at: String,
    val id: Int,
    val image_name: String,
    val imageable_id: Int,
    val imageable_type: String,
    val title: String,
    val updated_at: String
)