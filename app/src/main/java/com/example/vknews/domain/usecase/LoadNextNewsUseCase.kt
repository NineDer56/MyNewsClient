package com.example.vknews.domain.usecase

import com.example.vknews.domain.repository.NewsRepository
import javax.inject.Inject

class LoadNextNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(){
        repository.loadNextNews()
    }
}