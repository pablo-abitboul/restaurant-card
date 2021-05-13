package com.thefork.restaurantcard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thefork.domain.RestaurantDetail
import com.thefork.restaurantcard.utils.Resource
import com.thefork.usecases.GetRestaurantDetail
import kotlinx.coroutines.launch

class RestaurantDetailViewModel(
    private val getRestaurantDetail: GetRestaurantDetail
) : ViewModel() {

    private val _response = MutableLiveData<Resource<RestaurantDetail>>()
    val response: LiveData<Resource<RestaurantDetail>>
        get() = _response

    private val apiMethod = "restaurant_get_info"
    private val idRestaurant = 14163

    init {
        getRestaurantDetail()
    }

    private fun getRestaurantDetail() {
        viewModelScope.launch {
            _response.postValue(Resource.loading(null))
            try {
                val restaurantDetail = getRestaurantDetail.invoke(apiMethod, idRestaurant)
                _response.postValue(Resource.success(restaurantDetail))
            } catch (e: Exception) {
                _response.postValue(Resource.error(e.toString(), null))
            }
        }
    }
}