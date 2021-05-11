package com.thefork.restaurantcard.viewmodel

import com.thefork.restaurantcard.common.ScopedViewModel
import com.thefork.usecases.GetRestaurantDetail
import kotlinx.coroutines.CoroutineDispatcher

class RestaurantDetailViewModel(
    private val getRestaurantDetail: GetRestaurantDetail,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher)