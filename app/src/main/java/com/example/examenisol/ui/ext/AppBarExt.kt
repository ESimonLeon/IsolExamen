package com.example.examenisol.ui.ext

import android.view.View
import com.example.examenisol.R
import com.example.examenisol.ui.MainActivity
import androidx.appcompat.widget.SearchView
import com.example.examenisol.utils.setLoadQueryValidateFocus
import com.example.examenisol.utils.setLoadQueryValidateText


fun MainActivity.loadAppBarListener() {

    loadNavigationAppBar()

    loadMenuAppBar()
}


fun MainActivity.loadNavigationAppBar() {
    binding.topAppBar.setNavigationOnClickListener {
        finish()
    }
}

fun MainActivity.loadMenuAppBar() {
    binding.topAppBar.setOnMenuItemClickListener { menuItem ->
        when (menuItem.itemId) {
            R.id.action_default -> {
                loadFilterListDefault()
                true
            }

            R.id.action_max -> {
                loadFilterListMaxPrice()
                true
            }

            R.id.action_min -> {
                loadFilterListMinPrice()
                true
            }

            R.id.search -> {
                setViewSearchListener(menuItem.actionView)
                true
            }

            else -> false
        }
    }
}

fun MainActivity.setViewSearchListener(actionView: View?) {
    actionView ?: return

    val searchView = actionView as SearchView

    searchView.setLoadQueryValidateFocus {
        loadFilterEmptySearch()
    }

    searchView.setLoadQueryValidateText { lamQuery ->
        loadFilterSearch(lamQuery)
    }

}


fun MainActivity.loadFilterSearch(query: String) {
    pQuerySearch = query
    cleanListProducts()
}

fun MainActivity.loadFilterEmptySearch() {
    pQuerySearch = null
    cleanListProducts()
}

fun MainActivity.loadFilterListMinPrice() {
    if (pQuerySortPrice == 0) return

    pQuerySortPrice = 0
    cleanListProducts()
}

fun MainActivity.loadFilterListMaxPrice() {
    if (pQuerySortPrice == 1) return

    pQuerySortPrice = 1
    cleanListProducts()
}

fun MainActivity.loadFilterListDefault() {
    pQuerySortPrice ?: return

    pQuerySortPrice = null
    cleanListProducts()
}

fun MainActivity.cleanListProducts() {
    productsListAdapter.notifyItemRangeRemoved(0, productsListArray.size)
    productsListArray.clear()
    pQueryPage = 1
    sendSearchProducts()
}
