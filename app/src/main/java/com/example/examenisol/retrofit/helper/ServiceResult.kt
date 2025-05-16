package com.example.examenisol.retrofit.helper

import com.example.examenisol.retrofit.model.ProductResults


sealed class ServiceResult {

    data object ProgressBar : ServiceResult()

    data class ServiceSuccess(val productResults: ProductResults) :
        ServiceResult()

    data class ServiceError(val message: String) : ServiceResult()
}