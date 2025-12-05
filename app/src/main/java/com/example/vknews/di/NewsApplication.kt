package com.example.vknews.di

import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

class NewsApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.create()
    }
}


@Composable
fun getNewsApplicationComponent() : ApplicationComponent{
    Log.d("NewsApplication", "getNewsApplicationComponent")
    return (LocalContext.current.applicationContext as NewsApplication).component
}