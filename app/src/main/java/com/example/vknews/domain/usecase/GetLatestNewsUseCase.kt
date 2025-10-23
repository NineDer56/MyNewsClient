package com.example.vknews.domain.usecase

import com.example.vknews.domain.news.NewsItem
import com.example.vknews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLatestNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
     operator fun invoke() : Flow<List<NewsItem>> {
        return repository.getLatestNewsFlow()
    }
}