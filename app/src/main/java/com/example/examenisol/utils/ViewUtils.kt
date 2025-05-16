package com.example.examenisol.utils

import android.content.Context
import android.graphics.Paint
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.graphics.toColorInt
import com.google.android.material.circularreveal.cardview.CircularRevealCardView
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

fun TextView.setPaintFlagStrike() {
    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}

fun loadOnApplyWindowInsets(view: View) =
    ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        insets
    }

fun Context.createCircularVariantColor(colorHex: String): CircularRevealCardView {
    val circularRevealCardView = CircularRevealCardView(this)

    val sizeInPixels = createMarginEnd(20f)

    val layoutParams = LinearLayout.LayoutParams(sizeInPixels, sizeInPixels)

    layoutParams.marginEnd = createMarginEnd(5f)

    circularRevealCardView.layoutParams = layoutParams

    circularRevealCardView.setCardBackgroundColor(colorHex.toColorInt())

    return circularRevealCardView
}

fun Context.createMarginEnd(dimen: Float): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dimen, resources.displayMetrics).toInt()


fun SearchView.setLoadQueryValidateFocus(lamFocus: () -> Unit) {
    setOnQueryTextFocusChangeListener { _, hasFocus ->
        if (!hasFocus && query.isNullOrEmpty()) lamFocus()
    }
}

fun SearchView.setLoadQueryValidateText(lamFocus: (String) -> Unit) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            query.let {
                it ?: return@let
                lamFocus(it)
            }
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    })
}