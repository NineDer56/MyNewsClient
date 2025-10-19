package com.example.vknews.domain.news

data class NewsResponse(
    val status : String,
    val newsItems : List<NewsItem>,
    val nextPage : String
)