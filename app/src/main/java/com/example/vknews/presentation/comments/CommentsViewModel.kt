package com.example.vknews.presentation.comments

import androidx.lifecycle.ViewModel
import com.example.vknews.domain.PostComment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CommentsViewModel(
    feedPostId : Int
) : ViewModel() {

    private var _commentsScreenState =
        MutableStateFlow<CommentsScreenState>(CommentsScreenState.Initial)

    val commentsScreenState = _commentsScreenState.asStateFlow()

    init{
        loadComments(feedPostId)
    }

    private fun loadComments(feedPostId: Int) {
        val comments = mutableListOf<PostComment>().apply {
            repeat(10) {
                add(PostComment(id = it, author = "Author$it"))
            }
        }

        _commentsScreenState.value = CommentsScreenState.Comments(
            feedPostId = feedPostId,
            comments = comments
        )
    }
}