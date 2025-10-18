package com.example.vknews.presentation.main

sealed class AuthState {

    data object Authorized : AuthState()

    data object NotAuthorized : AuthState()

    data object Initial : AuthState()
}