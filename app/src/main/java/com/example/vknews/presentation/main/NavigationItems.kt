package com.example.vknews.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.vknews.R
import com.example.vknews.navigation.Screen

sealed class NavigationItems(
    val screen : Screen,
    val textResId: Int,
    val icon: ImageVector
) {
    data object Home : NavigationItems(
        Screen.Home,
        R.string.navigation_icon_home,
        Icons.Outlined.Home
    )

    data object Favourite : NavigationItems(
        Screen.Favourite,
        R.string.navigation_icon_favourite,
        Icons.Outlined.Favorite
    )

    data object Profile : NavigationItems(
        Screen.Profile,
        R.string.navigation_icon_profile,
        Icons.Outlined.Person
    )
}