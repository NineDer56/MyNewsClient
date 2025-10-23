package com.example.vknews.domain.usecase

import com.example.vknews.domain.repository.NewsRepository

class LoadNextNewsUseCase(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(){
        repository.loadNextNews()
    }
}