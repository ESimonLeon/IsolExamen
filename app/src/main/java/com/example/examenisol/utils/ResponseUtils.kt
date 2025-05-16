package com.example.examenisol.utils

import com.example.examenisol.retrofit.helper.ServiceResult
import com.example.examenisol.retrofit.model.ErrorApiService
import com.example.examenisol.retrofit.res.ResProductList
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Response

const val UNEXPECTED_ERROR = "Error inesperado."
const val WITHOUT_CONNECTION_ERROR = "Revisa tu conexión."
const val CONNECTION_TIMED_OUT_ERROR = "fallo el tiempo de espera de la conexión."

fun Response<ResProductList>.validateRepositoryResult(result: (ServiceResult) -> Unit) {
    if (isSuccessful) validateUsersList(body(), result)
    else validateErrorResult(errorBody(), result)
}

private fun validateUsersList(body: ResProductList?, result: (ServiceResult) -> Unit) =
    body.apply {

        this ?: result(loadErrorUnexpected())
        this ?: return@apply

        result(ServiceResult.ServiceSuccess(plpResults))
    }

private fun validateErrorResult(error: ResponseBody?, result: (ServiceResult) -> Unit) =
    with(error) {

        this ?: result(loadErrorUnexpected())
        this ?: return@with

        val errorStr = Gson().fromJson(this.string(), ErrorApiService::class.java)

        errorStr.message.apply {
            this ?: result(loadErrorUnexpected())
            this ?: return@apply

            result(ServiceResult.ServiceError(this))
        }

    }

fun loadErrorUnexpected() = ServiceResult.ServiceError(UNEXPECTED_ERROR)

fun loadErrorConnection() = ServiceResult.ServiceError(WITHOUT_CONNECTION_ERROR)

fun loadErrorTimeoutConnection() = ServiceResult.ServiceError(CONNECTION_TIMED_OUT_ERROR)

