package com.thefork.usecases

import com.thefork.data.repository.RestaurantRepository
import com.thefork.domain.RestaurantDetail

class GetRestaurantDetail(private val restaurantRepository: RestaurantRepository) {
    suspend fun invoke(method: String, id: Int): RestaurantDetail =
        restaurantRepository.getRestaurantDetail(method, id)
}