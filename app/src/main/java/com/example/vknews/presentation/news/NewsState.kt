package com.example.vknews.presentation.news

import com.example.vknews.domain.news.NewsItem

sealed class NewsState {

    data class News(
        val news: List<NewsItem>,
        val isLoadingMore: Boolean = false
    ) : NewsState()

    data class Error(val message: String) : NewsState()
    data object Loading : NewsState()
    data object Initial : NewsState()
}