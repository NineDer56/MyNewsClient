package com.example.vknews.data.repository

import com.example.vknews.data.mapper.NewsMapper
import com.example.vknews.data.network.ApiFactory
import com.example.vknews.domain.news.NewsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class NewsRepositoryImpl {

    private val apiService = ApiFactory().apiService
    private val mapper = NewsMapper()
    private val mutex = Mutex()

    private var currentPage: String? = null

    private var _news = mutableListOf<NewsItem>()

    private val loadNextNewsEvent = MutableSharedFlow<Unit>(
        extraBufferCapacity = 1
    )

    val latestNews: Flow<List<NewsItem>> =
        loadNextNewsEvent
            .onStart { emit(Unit) }
            .map {
                val page = mutex.withLock { currentPage }

                val response = withContext(Dispatchers.IO) {
                    apiService.getLatestNews(page)
                }

                val mapped = response.newsItems
                    .map { mapper.resultDtoToEntity(it) }

                val snapshot = mutex.withLock {
                    currentPage = response.nextPage
                    _news.addAll(mapped)
                    _news = _news.distinctBy { it.articleId }.toMutableList()
                    _news.toList()
                }

                snapshot
            }
            .retry() {
                delay(2000L)
                true
            }

    suspend fun loadNextNews() {
        loadNextNewsEvent.emit(Unit)
    }

    suspend fun snapshot() : List<NewsItem> = mutex.withLock { _news.toList() }


//    private val loadNextNewsFlow = flow {
//        loadNextNewsEvent.emit(Unit)
//        loadNextNewsEvent.collect {
//            val response = apiService.getLatestNews(currentPage)
//            currentPage = response.nextPage
//
//            val results = response.newsItems
//                .map {
//                    mapper.resultDtoToEntity(it)
//                }
//            _news.addAll(results)
//            emit(news)
//        }
//    }

//.stateIn(
//            scope = coroutineScope,
//            started = SharingStarted.Lazily,
//            initialValue = emptyList()
//        )
}