package com.example.vknews.presentation.news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun HomeScreen(
    onCommentsClickListener: (feedPostId: Int) -> Unit
){

    val viewModel : NewsFeedViewModel = viewModel()

    val screenState = viewModel.newsFeedScreenState.collectAsState()

    when(val currentState = screenState.value){
        is NewsFeedScreenState.Posts -> {
            FeedPosts(
                posts = currentState.posts,
                viewModel = viewModel,
                onCommentsClickListener = onCommentsClickListener
            )
        }
        is NewsFeedScreenState.Initial -> {

        }
    }
}