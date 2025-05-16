package com.example.examenisol.retrofit

import com.example.examenisol.BuildConfig
import com.example.examenisol.retrofit.res.ResProductList
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface ApiServiceRetrofitInterface {

    @GET(BuildConfig.PATH_SEARCH_PRODUCTS)
    suspend fun getProductsList(
        @Query("page-number") results: Int,
        @Query("search-string") pQuerySearch: String?,
        @Query("minSortPrice") sortPrice: Int?,
    ): Response<ResProductList>
}