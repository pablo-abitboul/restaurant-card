package com.thefork.usecases

import com.nhaarman.mockitokotlin2.whenever
import com.thefork.data.repository.RestaurantRepository
import com.thefork.testshared.mockedRestaurantDetail
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetRestaurantDetailTest {

    @Mock
    lateinit var restaurantRepository: RestaurantRepository

    private lateinit var getRestaurantDetail: GetRestaurantDetail

    @Before
    fun setUp() {
        getRestaurantDetail = GetRestaurantDetail(restaurantRepository)
    }

    @Test
    fun `invoke calls movies repository`() {
        runBlocking {
            val restaurantId = 14163
            val method = "restaurant_get_info"

            val restaurantDetail = mockedRestaurantDetail.copy(restaurantId)
            whenever(restaurantRepository.getRestaurantDetail(method, restaurantId)).thenReturn(
                restaurantDetail
            )

            val result = getRestaurantDetail.invoke(method, restaurantId)

            assertEquals(restaurantDetail, result)
        }
    }
}