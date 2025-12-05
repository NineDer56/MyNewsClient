package com.example.vknews.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vknews.di.NewsApplication
import com.example.vknews.di.ViewModelFactory
import com.example.vknews.di.getNewsApplicationComponent
import com.example.vknews.presentation.news.NewsFeedViewModel
import com.example.vknews.ui.theme.VkNewsTheme
import javax.inject.Inject


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            VkNewsTheme {
                MainScreen()
            }

        }
    }
}




