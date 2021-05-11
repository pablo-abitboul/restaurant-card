package com.thefork.restaurantcard

import android.app.Application

class RestaurantCardApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}