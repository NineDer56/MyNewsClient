package com.example.vknews.presentation.news

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknews.data.repository.NewsRepositoryImpl
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NewsFeedViewModel : ViewModel() {

    private val repository = NewsRepositoryImpl()
    //private val getLatestNewsUseCase = GetLatestNewsUseCase(repository)

    private val loadNextDataEvent = MutableSharedFlow<Unit>(
        extraBufferCapacity = 1
    )

    val newsState: StateFlow<NewsState> =
        merge(
            repository.latestNews.map {
                NewsState.News(
                    news = it,
                    isLoadingMore = false
                ) as NewsState
            },
            loadNextDataEvent.map {
                NewsState.News(
                    news = repository.snapshot(),
                    isLoadingMore = true
                ) as NewsState
            }
        )
            .catch { e ->
                Log.d("NewsFeedViewModel", e.message ?: "Unknown error")
            }
            .onStart {
                emit(NewsState.Loading)
                repository.loadNextNews()
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = NewsState.Initial
            )


    fun loadMoreNews() {
        viewModelScope.launch {
            loadNextDataEvent.emit(Unit)
            repository.loadNextNews()
        }
    }
}


private fun <T> Flow<T>.merge(another: Flow<T>): Flow<T> {
    return merge(this, another)
}