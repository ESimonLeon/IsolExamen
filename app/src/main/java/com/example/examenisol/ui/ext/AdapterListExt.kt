package com.example.examenisol.ui.ext

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examenisol.ui.MainActivity

fun MainActivity.setAdapterListListener() = binding.iContent.rvProducts.apply {
    val linearLayoutManager = LinearLayoutManager(this@setAdapterListListener)
    layoutManager = linearLayoutManager
    adapter = productsListAdapter


    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            if (linearLayoutManager.itemCount == 0 || pQueryPage >= pTotalPages) return

            if ((linearLayoutManager.itemCount - 1) == linearLayoutManager.findLastCompletelyVisibleItemPosition()) {
                pQueryPage++
                sendSearchProducts()
            }

        }
    })
}


