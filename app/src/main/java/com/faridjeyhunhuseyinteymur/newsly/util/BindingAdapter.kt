package com.faridjeyhunhuseyinteymur.newsly.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.faridjeyhunhuseyinteymur.newsly.R

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}


@BindingAdapter("savedStateIcon")
fun ImageView.setSavedStateIcon(isSaved: Boolean) {
    val iconResId = if (isSaved) {
        R.drawable.ic_bookmark_filled
    } else {
        R.drawable.ic_bookmark_outlined
    }
    this.setImageResource(iconResId)
}


@BindingAdapter("textOrPlaceholder")
fun TextView.setTextOrPlaceholder(text: String?) {
    this.text = text ?: "No data available"
}
