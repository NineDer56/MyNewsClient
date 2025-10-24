package com.example.vknews.domain.news

// @Immutable ставим если объект будет передаваться как аргумент в графе, чтобы можно было устанвоить равенство объектов
data class NewsItem(
    val articleId : String,
    val title : String,
    val link : String,
    val keywords : List<String>,
    val creator : List<String>,
    val description : String,
    val pubDate : String,
    val imageUrl : String,
    val videoUrl : String,

    val sourceName : String,
    val sourceUrl : String,
    val sourceIcon : String
)
