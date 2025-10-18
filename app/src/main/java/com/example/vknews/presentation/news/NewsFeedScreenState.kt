package com.example.vknews.presentation.news

import com.example.vknews.domain.FeedPost

sealed class NewsFeedScreenState {

    data object Initial : NewsFeedScreenState()

    data class Posts(val posts : List<FeedPost>) : NewsFeedScreenState()
}