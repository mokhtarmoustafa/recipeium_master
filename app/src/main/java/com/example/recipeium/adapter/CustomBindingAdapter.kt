package com.example.recipeium.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.example.recipeium.R


@BindingAdapter("loadImageUrl")
fun ImageView.loadImageUrl(imageUrl: String) {
    load(imageUrl) {
        crossfade(600)
        error(R.drawable.place_holder)
    }
}

@BindingAdapter("isVeg")
fun isOrganic(view: View, isOrganic: Boolean) {
    if (isOrganic) {
        when (view) {
            is TextView -> view.setTextColor(ContextCompat.getColor(view.context, R.color.green))
            is ImageView -> view.setColorFilter(ContextCompat.getColor(view.context, R.color.green))
        }
    }
}

