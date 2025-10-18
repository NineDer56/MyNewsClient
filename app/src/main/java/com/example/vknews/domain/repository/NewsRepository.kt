package com.example.vknews.domain.repository

import com.example.vknews.domain.news.NewsItem

interface NewsRepository {

    suspend fun getLatestNews() : Result<List<NewsItem>>
}