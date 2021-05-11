package com.thefork.restaurantcard.data

import com.thefork.domain.PicDiaporama
import com.thefork.domain.RestaurantDetail
import com.thefork.restaurantcard.data.network.PicDiaporama as ServerPicDiaporama
import com.thefork.restaurantcard.data.network.RestaurantDetail as ServerRestaurantDetail

fun ServerRestaurantDetail.toDomainRestaurantDetail(): RestaurantDetail =
    RestaurantDetail(
        0,
        name,
        address,
        picsDiaporama?.map { it.toDomainPicDiaporama() },
        cardPrice,
        avgRate,
        rateCount,
        cardMain1,
        cardMain2,
        cardMain3,
        priceCardMain1,
        priceCardMain2,
        priceCardMain3,
        chefName,
        taAvgRating,
        taReviewCount
    )

fun ServerPicDiaporama.toDomainPicDiaporama(): PicDiaporama = PicDiaporama(
    pic,
    label
)