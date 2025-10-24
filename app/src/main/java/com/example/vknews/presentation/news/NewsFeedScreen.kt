package com.example.vknews.presentation.news

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vknews.di.ViewModelFactory
import com.example.vknews.di.getNewsApplicationComponent


@Composable
fun HomeScreen(
    onCommentsClickListener: (feedPostId: Int) -> Unit
){
    val component = getNewsApplicationComponent()
    val viewModelFactory = component.getViewModelFactory()
    val viewModel : NewsFeedViewModel = viewModel(factory = viewModelFactory)
    val screenState = viewModel.newsState.collectAsState(NewsState.Initial)

    HomeScreenContent(
        viewModelFactory = viewModelFactory,
        onCommentsClickListener = onCommentsClickListener,
        screenState = screenState
    )
}

@Composable
fun HomeScreenContent(
    viewModelFactory: ViewModelFactory,
    onCommentsClickListener: (feedPostId: Int) -> Unit,
    screenState : State<NewsState>
){
    when(val currentState = screenState.value){
        is NewsState.News -> {
            FeedPosts(
                posts = currentState.news,
                viewModelFactory = viewModelFactory,
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