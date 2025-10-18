package com.example.vknews.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vknews.ui.theme.VkNewsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()



        setContent {
            VkNewsTheme {
                val viewModel : MainViewModel = viewModel()
                val authState = viewModel.authState.collectAsStateWithLifecycle()

                when(authState.value){
                    is AuthState.Authorized -> {
                        MainScreen()
                    }
                    is AuthState.NotAuthorized -> {
                        AuthScreen(viewModel)
                    }
                    is AuthState.Initial -> {
                        viewModel.checkAuth()
                    }
                }
            }

        }
    }
}




