package com.thefork.data.repository

import com.thefork.data.source.RemoteDataSource
import com.thefork.domain.RestaurantDetail

class RestaurantRepository(
    private val remoteDataSource: RemoteDataSource,
    private val apiKey: String

) {
    suspend fun getRestaurantDetail(method: String, id: Int): RestaurantDetail =
        remoteDataSource.getRestaurantDetail(apiKey, method, id)
}
