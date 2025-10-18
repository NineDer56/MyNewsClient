package com.example.vknews.domain.news

data class NewsResponse(
    val status : String,
    val results : List<Result>,
    val nextPage : String
)