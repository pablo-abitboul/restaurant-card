package com.thefork.restaurantcard.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface TheForkApiService {
    @GET("api")
    suspend fun getRestaurantDetail(
        @Query("key") key: String,
        @Query("method") method: String,
        @Query("id_restaurant") restaurantId: Int
    ): GetRestaurantDetailResponse
}