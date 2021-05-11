package com.thefork.restaurantcard.data.network

import com.thefork.data.source.RemoteDataSource
import com.thefork.domain.RestaurantDetail
import com.thefork.restaurantcard.data.toDomainRestaurantDetail

class TheForkApiDataSource(private val theForkApi: TheForkApi) : RemoteDataSource {
    override suspend fun getRestaurantDetail(
        apiKey: String,
        method: String,
        restaurantId: Int
    ): RestaurantDetail =
        theForkApi.service
            .getRestaurantDetail(apiKey, method, restaurantId)
            .data
            .toDomainRestaurantDetail()
}