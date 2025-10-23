package com.example.vknews.presentation.news

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknews.domain.usecase.GetLatestNewsUseCase
import com.example.vknews.domain.usecase.GetSnapshotUseCase
import com.example.vknews.domain.usecase.LoadNextNewsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsFeedViewModel @Inject constructor(
    private val getLatestNewsUseCase: GetLatestNewsUseCase,
    private val getSnapshotUseCase: GetSnapshotUseCase,
    private val loadNextNewsUseCase: LoadNextNewsUseCase
) : ViewModel() {


    private val loadNextDataEvent = MutableSharedFlow<Unit>(
        extraBufferCapacity = 1
    )

    val newsState: StateFlow<NewsState> =
        merge(
            getLatestNewsUseCase().map {
                NewsState.News(
                    news = it,
                    isLoadingMore = false
                ) as NewsState
            },
            loadNextDataEvent.map {
                NewsState.News(
                    news = getSnapshotUseCase(),
                    isLoadingMore = true
                ) as NewsState
            }
        )
            .catch { e ->
                Log.d("NewsFeedViewModel", e.message ?: "Unknown error")
            }
            .onStart {
                emit(NewsState.Loading)
                loadNextNewsUseCase()
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = NewsState.Initial
            )


    fun loadMoreNews() {
        viewModelScope.launch {
            loadNextDataEvent.emit(Unit)
            loadNextNewsUseCase()
        }
}
}


private fun <T> Flow<T>.merge(another: Flow<T>): Flow<T> {
    return merge(this, another)
}