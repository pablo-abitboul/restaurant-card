package com.thefork.restaurantcard

import android.app.Application
import com.thefork.data.repository.RestaurantRepository
import com.thefork.data.source.RemoteDataSource
import com.thefork.restaurantcard.data.network.TheForkApi
import com.thefork.restaurantcard.data.network.TheForkApiDataSource
import com.thefork.restaurantcard.ui.MainActivity
import com.thefork.restaurantcard.viewmodel.RestaurantDetailViewModel
import com.thefork.usecases.GetRestaurantDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private val appModule = module {
    single(named("apiKey")) { androidApplication().getString(R.string.api_key) }
    factory<RemoteDataSource> { TheForkApiDataSource(get()) }
    single<CoroutineDispatcher> { Dispatchers.Main }
    single(named("baseUrl")) { "http://api.lafourchette.com/" }
    single { TheForkApi(get(named("baseUrl"))) }
}

val dataModule = module {
    factory { RestaurantRepository(get(), get(named("apiKey"))) }
}

private val scopesModule = module {
    scope(named<MainActivity>()) {
        viewModel { RestaurantDetailViewModel(get(), get()) }
        scoped { GetRestaurantDetail(get()) }
    }
}