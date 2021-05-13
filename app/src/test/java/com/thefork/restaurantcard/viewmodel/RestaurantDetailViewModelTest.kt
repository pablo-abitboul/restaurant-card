package com.thefork.restaurantcard.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.thefork.domain.RestaurantDetail
import com.thefork.restaurantcard.utils.Resource
import com.thefork.testshared.mockedRestaurantDetail
import com.thefork.usecases.GetRestaurantDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RestaurantDetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("thread")

    @Mock
    lateinit var getRestaurantDetail: GetRestaurantDetail

    private lateinit var viewModel: RestaurantDetailViewModel

    private val apiMethod = "restaurant_get_info"
    private val idRestaurant = 14163

    @Mock
    lateinit var observer: Observer<Resource<RestaurantDetail>>

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = RestaurantDetailViewModel(getRestaurantDetail)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `getRestaurantDetail when fetch data returns success`() {
        runBlocking {
            val restaurantDetail = mockedRestaurantDetail.copy(idRestaurant)
            whenever(getRestaurantDetail.invoke(apiMethod, idRestaurant)).thenReturn(
                restaurantDetail
            )

            viewModel.response.observeForever(observer)
            verify(observer).onChanged(Resource.success(restaurantDetail))
            viewModel.response.removeObserver(observer)
        }
    }

    @Test
    fun `getRestaurantDetail when throws an error returns error`() {
        runBlocking {
            val errorMessage = "Error Message"
            doThrow(RuntimeException(errorMessage))
                .`when`(getRestaurantDetail).invoke(apiMethod, idRestaurant)

            viewModel.response.observeForever(observer)
            verify(observer).onChanged(
                Resource.error(
                    RuntimeException(errorMessage).toString(),
                    null
                )
            )
            viewModel.response.removeObserver(observer)
        }
    }
}