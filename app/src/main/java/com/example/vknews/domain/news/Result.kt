package com.example.vknews.domain.news

data class Result(
    val articleId : String,
    val title : String,
    val link : String,
    val creator : List<String>,
    val description : String,
    val pubDate : String,
    val imageUrl : String,
    val videoUrl : String
)
