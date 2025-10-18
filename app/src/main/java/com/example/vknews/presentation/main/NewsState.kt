package com.example.vknews.presentation.main

import com.example.vknews.domain.news.NewsItem

sealed class NewsState {

    data class News(val news : List<NewsItem>) : NewsState()
    data class Error(val message : String) : NewsState()
    data object Loading : NewsState()
    data object Initial : NewsState()
}