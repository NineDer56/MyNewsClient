package com.example.vknews.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknews.data.repository.NewsRepositoryImpl
import com.example.vknews.domain.news.NewsItem
import com.example.vknews.domain.usecase.GetLatestNewsUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsFeedViewModel : ViewModel() {

    private val _newsState = MutableStateFlow<NewsState>(NewsState.Initial)
    val newsState = _newsState.asStateFlow()

    private val repository = NewsRepositoryImpl()
    private val getLatestNewsUseCase = GetLatestNewsUseCase(repository)

    private var loadJob: Job? = null
    private var loadMoreJob: Job? = null

    fun loadNews() {
        if (loadJob?.isActive == true || loadMoreJob?.isActive == true) return

        loadJob = viewModelScope.launch {
            getLatestNewsUseCase()
                .onSuccess {
                    _newsState.value = NewsState.News(it)
                }
                .onFailure {
                    _newsState.value = NewsState.Error(it.message ?: "message is null")
                }
        }
    }

    fun loadMoreNews() {
        if (loadJob?.isActive == true || loadMoreJob?.isActive == true) return

        loadMoreJob = viewModelScope.launch {
            getLatestNewsUseCase()
                .onSuccess { newNews ->
                    val currentState = _newsState.value
                    if (currentState is NewsState.News) {
                        val oldNews = currentState.news
                        _newsState.value = NewsState.News(oldNews + newNews)
                    }
                }
        }
    }

    fun deleteNewsItem(post: NewsItem) {
        val currentState = _newsState.value
        if (currentState is NewsState.News) {
            val old = currentState.news.toMutableList()
            old.remove(post)
            _newsState.value = NewsState.News(old)
        }


//    fun updateStatisticsItem(post: NewsItem, type: StatisticsType) {
//        val currentState = _newsState.value
//        if(currentState is NewsState.News){
//            val old = currentState.news.toMutableList()
//            old.apply {
//                replaceAll { oldPost ->
//                    if (oldPost.articleId == post.articleId) {
//
//                        val newPost = oldPost.copy(
//                            statistics = oldPost.statistics.toMutableList().apply {
//                                replaceAll { oldItem ->
//                                    if (oldItem.type == type) {
//                                        oldItem.copy(count = oldItem.count + 1)
//                                    } else {
//                                        oldItem
//                                    }
//                                }
//                            }
//                        )
//                        Log.d("update", newPost.statistics.toString())
//                        newPost
//                    } else {
//                        oldPost
//                    }
//                }
//            }
//
//            _newsFeedScreenState.value = NewsState.Posts(old)
//        }
//    }


    }
}