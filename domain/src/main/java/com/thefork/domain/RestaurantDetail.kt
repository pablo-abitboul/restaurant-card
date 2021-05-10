package com.thefork.domain

data class RestaurantDetail(
    val idRestaurant: Int?,
    val name: String,
    val address: String,
    val picsDiaporama: List<PicDiaporama>?,
    val cardPrice: Int?,
    val avgRate: Double?,
    val rateCount: Int?,
    val cardMain1: String?,
    val cardMain2: String?,
    val cardMain3: String?,
    val priceCardMain1: Double?,
    val priceCardMain2: Double?,
    val priceCardMain3: Double?,
    val chefName: String?,
    val taAvgRating: Double?,
    val taReviewCount: Int?
)

data class PicDiaporama(
    val label: String
)
