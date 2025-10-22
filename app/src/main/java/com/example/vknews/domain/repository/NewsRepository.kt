package com.example.vknews.domain.repository

import com.example.vknews.domain.news.NewsItem
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getLatestNews() : Flow<List<NewsItem>>
}