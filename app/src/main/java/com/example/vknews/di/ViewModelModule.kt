package com.example.vknews.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vknews.presentation.news.NewsFeedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @ViewModelKey(NewsFeedViewModel::class)
    @IntoMap
    fun bindNewsFeedViewModel(newsFeedViewModel: NewsFeedViewModel) : ViewModel
}