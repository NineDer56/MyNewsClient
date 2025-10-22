package com.example.vknews.presentation.news

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun HomeScreen(
    onCommentsClickListener: (feedPostId: Int) -> Unit
){

    val viewModel : NewsFeedViewModel = viewModel()
    val screenState = viewModel.newsState.collectAsState(NewsState.Initial)

    when(val currentState = screenState.value){
        is NewsState.News -> {
            FeedPosts(
                posts = currentState.news,
                viewModel = viewModel,
                onCommentsClickListener = onCommentsClickListener,
                isLoadingMore = currentState.isLoadingMore
            )
        }

        is NewsState.Initial -> {

        }

        is NewsState.Error -> {

        }

        is NewsState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}