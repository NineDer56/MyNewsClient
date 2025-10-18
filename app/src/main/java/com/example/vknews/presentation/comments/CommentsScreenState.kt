package com.example.vknews.presentation.comments

import com.example.vknews.domain.PostComment

sealed class CommentsScreenState {

    data object Initial : CommentsScreenState()

    data class Comments(val feedPostId : Int, val comments : List<PostComment>) : CommentsScreenState()
}