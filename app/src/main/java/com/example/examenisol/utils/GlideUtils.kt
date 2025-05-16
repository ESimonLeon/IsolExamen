package com.example.examenisol.utils

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.examenisol.R


fun ImageView.loadImageGlide(url: String) {

    val placeholder = loadImagePlaceholder(this.context)

    Glide.with(this.context).apply {
        clear(this@loadImageGlide)

        placeholder.start()

        this.load(url)
            .placeholder(placeholder)
            .apply(glideOptions(R.drawable.ic_without_connection))
            .into(this@loadImageGlide)
    }

}

fun loadImagePlaceholder(context: Context): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    return circularProgressDrawable
}

fun glideOptions(errorDrawable: Int): RequestOptions {
    return RequestOptions()
        .error(errorDrawable)
}

