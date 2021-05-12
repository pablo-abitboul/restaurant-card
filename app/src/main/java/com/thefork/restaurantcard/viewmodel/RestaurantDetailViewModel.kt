package com.thefork.restaurantcard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thefork.domain.RestaurantDetail
import com.thefork.usecases.GetRestaurantDetail
import kotlinx.coroutines.launch

enum class TheForkApiStatus { LOADING, ERROR, DONE }

class RestaurantDetailViewModel(
    private val getRestaurantDetail: GetRestaurantDetail
) : ViewModel() {
    private val _status = MutableLiveData<TheForkApiStatus>()
    val status: LiveData<TheForkApiStatus>
        get() = _status

    private val _response = MutableLiveData<RestaurantDetail>()
    val response: LiveData<RestaurantDetail>
        get() = _response

    private val apiMethod = "restaurant_get_info"

    companion object {
        //const val ID_RESTAURANT: Int = 6861
        //const val ID_RESTAURANT: Int = 40370 Not working
        //const val ID_RESTAURANT: Int = 16409 Not working
        const val ID_RESTAURANT: Int = 14163
    }

    fun getRestaurantDetail(idRestaurant: Int) {
        viewModelScope.launch {
            try {
                _status.value = TheForkApiStatus.LOADING
                val response = getRestaurantDetail.invoke(apiMethod, ID_RESTAURANT)

                _status.value = TheForkApiStatus.DONE
                _response.value = response
            } catch (e: Exception) {
                _status.value = TheForkApiStatus.ERROR
                _response.value = null
            }
        }
    }
}