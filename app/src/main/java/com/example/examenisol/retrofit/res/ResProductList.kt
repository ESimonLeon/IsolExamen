package com.example.examenisol.retrofit.res

import com.example.examenisol.retrofit.model.ProductResults
import com.example.examenisol.retrofit.model.ProductStatus

data class ResProductList(
    val status: ProductStatus,
    val pageType: String,
    val plpResults: ProductResults
)

