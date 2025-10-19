package com.example.vknews.domain.usecase

import com.example.vknews.domain.news.NewsItem
import com.example.vknews.domain.repository.NewsRepository

class GetLatestNewsUseCase(
    private val repository: NewsRepository
) {
    suspend operator fun invoke() : Result<List<NewsItem>>{
        return repository.getLatestNews()
    }
}