package com.example.vknews.presentation.main

import androidx.lifecycle.ViewModel
import com.vk.id.VKID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Initial)
    val authState = _authState.asStateFlow()

    fun checkAuth(){
        if(VKID.instance.accessToken == null){
            _authState.value = AuthState.NotAuthorized
        } else {
            _authState.value = AuthState.Authorized
        }
    }

    fun successAuth(){
        _authState.value = AuthState.Authorized
    }

    fun failAuth(){
        _authState.value = AuthState.NotAuthorized
    }

}