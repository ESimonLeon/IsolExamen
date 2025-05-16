package com.example.examenisol.ui.ext

import android.view.View.GONE
import android.view.View.VISIBLE
import com.example.examenisol.retrofit.helper.ServiceResult
import com.example.examenisol.retrofit.model.ProductResults
import com.example.examenisol.ui.MainActivity
import java.util.ArrayList

fun MainActivity.validateServiceResult(serviceResult: ServiceResult?) = with(serviceResult) {
    this ?: loadEmptyList()
    this ?: return@with

    when (this) {
        ServiceResult.ProgressBar -> showProgressBar()
        is ServiceResult.ServiceSuccess -> loadViewSuccess(this)
        is ServiceResult.ServiceError -> loadViewError(this)
    }

}

fun MainActivity.loadViewSuccess(serviceResult: ServiceResult.ServiceSuccess) =
    serviceResult.apply {

        hideProgressBar()

        setProductsList(productResults.records)

        with(productResults.plpState) {

            this ?: loadEmptyList()
            this ?: return@with

            loadNotEmptyList()

            loadTotalPages(this)

            notificationAdapterInsert(this)

            validateAutomaticScroll(this)
        }
    }

fun MainActivity.validateAutomaticScroll(plpState: ProductResults.ProductResultsState) =
    plpState.apply {
        if (firstRecNum > 1) {
            binding.iContent.rvProducts.scrollToPosition(firstRecNum + 1)
        }
    }

fun MainActivity.notificationAdapterInsert(plpState: ProductResults.ProductResultsState) =
    plpState.apply {
        productsListAdapter.notifyItemRangeInserted(firstRecNum, lastRecNum)
    }


fun MainActivity.setProductsList(records: ArrayList<ProductResults.ProductResultsRecords>) {
    if (records.isEmpty()) {
        loadEmptyList()
        return
    }
    productsListArray.addAll(records)
}

fun MainActivity.loadTotalPages(plpState: ProductResults.ProductResultsState) = plpState.apply {
    pTotalPages = (totalNumRecs / recsPerPage)
}

private fun MainActivity.loadViewError(serviceResult: ServiceResult.ServiceError) {
    hideProgressBar()
    loadEmptyList()

    binding.iContent.emptyList.text = serviceResult.message
}

private fun MainActivity.loadEmptyList() = binding.iContent.apply {
    rvProducts.visibility = GONE
    emptyList.visibility = VISIBLE
}

private fun MainActivity.loadNotEmptyList() = binding.iContent.apply {
    emptyList.visibility = GONE
    rvProducts.visibility = VISIBLE
}

private fun MainActivity.hideProgressBar() {
    binding.iContent.pbLinearLoad.visibility = GONE
}

private fun MainActivity.showProgressBar() {
    binding.iContent.pbLinearLoad.visibility = VISIBLE
}
