package com.example.vknews.presentation.main

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vk.id.onetap.compose.onetap.OneTap
import com.vk.id.onetap.compose.onetap.OneTapTitleScenario


@Composable
fun AuthScreen(viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OneTap(
            onAuth = { oAuth, accessToken ->
                Log.d("MainActivity", "onSuccess: $oAuth, $accessToken")
                viewModel.successAuth()
            },
            onFail = { oAuth, fail ->
                Log.d("MainActivity", "onFail: $oAuth, $fail")
                viewModel.failAuth()
            },
            scenario = OneTapTitleScenario.SignIn,
            signInAnotherAccountButtonEnabled = true,
        )

    }
}