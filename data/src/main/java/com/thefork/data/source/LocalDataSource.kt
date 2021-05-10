package com.thefork.data.source

import com.thefork.domain.RestaurantDetail

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveRestaurantDetail(restaurantDetail: RestaurantDetail)
    suspend fun getRestaurantDetail(id: Int): RestaurantDetail
}