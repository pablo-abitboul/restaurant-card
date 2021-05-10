package com.thefork.data.repository

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.thefork.data.source.LocalDataSource
import com.thefork.data.source.RemoteDataSource
import com.thefork.testshared.mockedRestaurantDetail
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RestaurantRepositoryTest {
    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    lateinit var restaurantRepository: RestaurantRepository

    private val apiKey = "1a2b3c4d"
    private val restaurantId = 14163
    private val method = "restaurant_get_info"

    @Before
    fun setUp() {
        restaurantRepository =
            RestaurantRepository(localDataSource, remoteDataSource, apiKey)
    }

    @Test
    fun `getRestaurantDetail gets from local data source first`() {
        runBlocking {

            val restaurantDetail = mockedRestaurantDetail.copy(restaurantId)
            whenever(localDataSource.isEmpty()).thenReturn(false)
            whenever(localDataSource.getRestaurantDetail(restaurantId)).thenReturn(restaurantDetail)

            val result = restaurantRepository.getRestaurantDetail(method, restaurantId)

            assertEquals(restaurantDetail, result)
        }
    }

    @Test
    fun `getRestaurantDetail saves remote data to local`() {
        runBlocking {

            val remoteRestaurantDetail = mockedRestaurantDetail.copy(restaurantId)
            whenever(localDataSource.isEmpty()).thenReturn(true)
            whenever(remoteDataSource.getRestaurantDetail(apiKey, method, restaurantId)).thenReturn(
                remoteRestaurantDetail
            )

            restaurantRepository.getRestaurantDetail(method, restaurantId)

            verify(localDataSource).saveRestaurantDetail(remoteRestaurantDetail)
        }
    }
}