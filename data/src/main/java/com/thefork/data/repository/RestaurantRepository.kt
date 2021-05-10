package com.thefork.data.repository

import com.thefork.data.source.LocalDataSource
import com.thefork.data.source.RemoteDataSource
import com.thefork.domain.RestaurantDetail

class RestaurantRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val apiKey: String

) {
    suspend fun getRestaurantDetail(method: String, restaurantId: Int): RestaurantDetail {

        if (localDataSource.isEmpty()) {
            val restaurantDetail =
                remoteDataSource.getRestaurantDetail(apiKey, method, restaurantId)
            localDataSource.saveRestaurantDetail(restaurantDetail)
        }

        return localDataSource.getRestaurantDetail(restaurantId)
    }
}