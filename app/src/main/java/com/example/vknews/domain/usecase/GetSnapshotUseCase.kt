package com.example.vknews.domain.usecase

import com.example.vknews.domain.news.NewsItem
import com.example.vknews.domain.repository.NewsRepository

class GetSnapshotUseCase(
    private val repository: NewsRepository
) {
    suspend operator fun invoke() : List<NewsItem>{
        return repository.getSnapshot()
    }
}