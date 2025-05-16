package com.example.examenisol.retrofit.model

data class ProductResults(
    val label: String,
    val plpState: ProductResultsState?,
    val sortOptions: ArrayList<ProductResultsSortOptions> = arrayListOf(),
    val refinementGroups: ArrayList<ProductResultsRefinementGroups> = arrayListOf(),
    val records: ArrayList<ProductResultsRecords> = arrayListOf()
) {

    data class ProductResultsState(
        val categoryId: String,
        val currentSortOption: String,
        val currentFilters: String,
        val firstRecNum: Int,
        val lastRecNum: Int,
        val recsPerPage: Int,
        val totalNumRecs: Int,
        val plpSellerName: String
    )

    data class ProductResultsSortOptions(
        val sortBy: String,
        val label: String
    )

    data class ProductResultsRefinementGroups(
        val name: String,
        val refinement: ArrayList<RefinementGroups> = arrayListOf()
    ) {

        data class RefinementGroups(
            val count: Int,
            val label: String,
            val refinementId: String,
            val selected: Boolean,
            val type: String,
            val searchName: String,
        )
    }

    data class ProductResultsRecords(
        val productId: String,
        val skuRepositoryId: String,
        val productDisplayName: String,
        val productType: String,
        val productRatingCount: Int,
        val productAvgRating: Double,
        val promotionalGiftMessage: String,
        val listPrice: Double,
        val minimumListPrice: Double,
        val maximumListPrice: Double,
        val promoPrice: Double,
        val minimumPromoPrice: Double,
        val maximumPromoPrice: Double,
        val isHybrid: Boolean,
        val isMarketPlace: Boolean,
        val isImportationProduct: Boolean,
        val brand: String,
        val seller: String,
        val category: String,
        val dwPromotionInfo: DwPromotionInfo,
        val isExpressFavoriteStore: Boolean,
        val isExpressNearByStore: Boolean,
        val smImage: String,
        val lgImage: String,
        val xlImage: String,
        val groupType: String,
        val variantsColor: ArrayList<VariantsColor>?,
    ) {
        data class DwPromotionInfo(
            val dwToolTipInfo: String,
            val dWPromoDescription: String
        )

        data class VariantsColor(
            val colorName: String,
            val colorHex: String,
            val colorImageURL: String,
            val colorMainURL: String?,
            val skuId: String?,
            val galleryImages: String?
        )
    }
}