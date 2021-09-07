package com.khaled.aleftask.util

import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.khaled.aleftask.R


fun ImageButton.toggleFavourite(isFavourite: Boolean){
    if (isFavourite){
        ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(Color.RED))
    }else{
        val unFavouriteColor = ContextCompat.getColor(this.context, R.color.grey)
        ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(unFavouriteColor))
    }
}