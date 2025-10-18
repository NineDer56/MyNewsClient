package com.example.vknews.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknews.data.repository.NewsRepositoryImpl
import com.example.vknews.domain.usecase.GetLatestNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _newsState = MutableStateFlow<NewsState>(NewsState.Initial)
    val newsState = _newsState.asStateFlow()

    private val repository = NewsRepositoryImpl()
    private val getLatestNewsUseCase = GetLatestNewsUseCase(repository)


    init {
        loadNews()
    }

    fun loadNews(){
        viewModelScope.launch {
            _newsState.value = NewsState.Loading

            getLatestNewsUseCase()
                .onSuccess {
                    _newsState.value = NewsState.News(it)
                }
                .onFailure {
                    _newsState.value = NewsState.Error(it.message ?: "message is null")
                }
        }
    }
}