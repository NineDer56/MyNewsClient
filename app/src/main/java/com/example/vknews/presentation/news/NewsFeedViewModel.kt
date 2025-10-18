package com.example.vknews.presentation.news

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vknews.domain.FeedPost
import com.example.vknews.domain.StatisticsType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NewsFeedViewModel : ViewModel() {

    private var posts = mutableListOf<FeedPost>().apply {
        repeat(10) {
            add(FeedPost(id = it, groupName = "/dev/null/$it"))
        }
    }


    private var _newsFeedScreenState =
        MutableStateFlow<NewsFeedScreenState>(NewsFeedScreenState.Posts(posts))

    val newsFeedScreenState = _newsFeedScreenState.asStateFlow()


    fun updateStatisticsItem(post: FeedPost, type: StatisticsType) {
        val currentState = _newsFeedScreenState.value
        if(currentState is NewsFeedScreenState.Posts){
            val old = currentState.posts.toMutableList()
            old.apply {
                replaceAll { oldPost ->
                    if (oldPost.id == post.id) {
                        Log.d("update", oldPost.statistics.toString())
                        val newPost = oldPost.copy(
                            statistics = oldPost.statistics.toMutableList().apply {
                                replaceAll { oldItem ->
                                    if (oldItem.type == type) {
                                        oldItem.copy(count = oldItem.count + 1)
                                    } else {
                                        oldItem
                                    }
                                }
                            }
                        )
                        Log.d("update", newPost.statistics.toString())
                        newPost
                    } else {
                        oldPost
                    }
                }
            }

            _newsFeedScreenState.value = NewsFeedScreenState.Posts(old)
        }
    }

    fun deletePost(post: FeedPost) {
        val currentState = _newsFeedScreenState.value
        if(currentState is NewsFeedScreenState.Posts){
            val old = currentState.posts.toMutableList()
            old.remove(post)
            _newsFeedScreenState.value = NewsFeedScreenState.Posts(old)
        }

    }
}