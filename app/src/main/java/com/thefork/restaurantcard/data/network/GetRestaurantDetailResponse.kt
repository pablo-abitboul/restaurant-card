package com.thefork.restaurantcard.data.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class GetRestaurantDetailResponse(
    val data: RestaurantDetail
)

@Parcelize
data class RestaurantDetail(
    @SerializedName("id_restaurant") val idRestaurant: Int?,
    val name: String,
    val address: String,
    @SerializedName("pics_diaporama") val picsDiaporama: List<PicDiaporama>?,
    @SerializedName("card_price") val cardPrice: Int?,
    @SerializedName("avg_rate") val avgRate: Double?,
    @SerializedName("rate_count") val rateCount: Int?,
    @SerializedName("card_main_1") val cardMain1: String?,
    @SerializedName("card_main_2") val cardMain2: String?,
    @SerializedName("card_main_3") val cardMain3: String?,
    @SerializedName("price_card_main_1") val priceCardMain1: Double?,
    @SerializedName("price_card_main_2") val priceCardMain2: Double?,
    @SerializedName("price_card_main_3") val priceCardMain3: Double?,
    @SerializedName("chef_name") val chefName: String?,
    @SerializedName("trip_advisor_avg_rating") val taAvgRating: Double?,
    @SerializedName("trip_advisor_review_count") val taReviewCount: Int?
) : Parcelable


@Parcelize
data class PicDiaporama(
    @SerializedName("612x344") val pic: String,
    val label: String
) : Parcelable
