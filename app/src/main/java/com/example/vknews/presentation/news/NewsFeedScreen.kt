package com.example.vknews.presentation.news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun HomeScreen(
    onCommentsClickListener: (feedPostId: Int) -> Unit
){

    val viewModel : NewsFeedViewModel = viewModel()

    val screenState = viewModel.newsState.collectAsState()

    when(val currentState = screenState.value){
        is NewsState.News -> {
            FeedPosts(
                posts = currentState.news,
                viewModel = viewModel,
                onCommentsClickListener = onCommentsClickListener
            )
        }

        is NewsState.Initial -> {
            viewModel.loadNews()
        }

        is NewsState.Error -> {

        }

        is NewsState.Loading -> {

        }
    }
}