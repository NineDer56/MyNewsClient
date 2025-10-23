package com.example.vknews.di

import android.app.Application

class NewsApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.create()
    }
}