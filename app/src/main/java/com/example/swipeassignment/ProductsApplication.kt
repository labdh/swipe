package com.example.swipeassignment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class ProductsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}