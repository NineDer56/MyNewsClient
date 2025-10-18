package com.example.vknews.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vknews.data.network.ApiFactory
import com.example.vknews.data.network.ApiService
import com.example.vknews.ui.theme.VkNewsTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            VkNewsTheme {
                val viewModel : MainViewModel = viewModel()
                val state = viewModel.newsState.collectAsStateWithLifecycle()

                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                    Button(
                        onClick = {
                            viewModel.loadNews()
                        },
                        modifier = Modifier.padding(60.dp)
                    ) {
                        Text("Test")
                    }

                }
            }

        }
    }
}




