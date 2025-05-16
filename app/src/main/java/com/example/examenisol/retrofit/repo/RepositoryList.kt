package com.example.examenisol.retrofit.repo

import com.example.examenisol.retrofit.ApiServiceRetrofitInterface
import com.example.examenisol.retrofit.helper.ServiceResult
import com.example.examenisol.utils.loadErrorUnexpected
import com.example.examenisol.utils.validateRepositoryResult

class RepositoryList(private val apiServiceRetrofitInterface: ApiServiceRetrofitInterface) {

    suspend fun getProductsListRepo(
        pageNumber: Int,
        pQuerySearch: String?,
        sortPrice: Int?,
    ): ServiceResult {

        var resultService: ServiceResult = loadErrorUnexpected()

        apiServiceRetrofitInterface.getProductsList(pageNumber, pQuerySearch, sortPrice)
            .validateRepositoryResult { itResponse ->
                resultService = itResponse
            }

        return resultService
    }


}