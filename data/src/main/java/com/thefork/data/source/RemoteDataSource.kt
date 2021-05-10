package com.thefork.data.source

import com.thefork.domain.RestaurantDetail

interface RemoteDataSource {
    suspend fun getRestaurantDetail(
        apiKey: String,
        method: String,
        restaurantId: Int
    ): RestaurantDetail
}