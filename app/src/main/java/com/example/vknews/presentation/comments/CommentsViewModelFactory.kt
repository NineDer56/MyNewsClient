package com.example.vknews.presentation.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CommentsViewModelFactory(
    private val feedPostId : Int
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommentsViewModel(feedPostId) as T
    }
}