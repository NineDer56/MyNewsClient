package com.example.vknews.presentation.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShortNavigationBar
import androidx.compose.material3.ShortNavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.vknews.navigation.AppNavGraph
import com.example.vknews.navigation.rememberNavigationState
import com.example.vknews.presentation.comments.CommentScreen
import com.example.vknews.presentation.news.HomeScreen


@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    val icons = listOf(
        NavigationItems.Home,
        NavigationItems.Favourite,
        NavigationItems.Profile
    )

    Scaffold(
        bottomBar = {
            ShortNavigationBar {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

                icons.forEach { navigationItem ->

                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == navigationItem.screen.route
                    } ?: false

                    ShortNavigationBarItem(
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navigationState.navigateTo(navigationItem.screen.route)
                            }
                        },
                        icon = { Icon(navigationItem.icon, contentDescription = null) },
                        label = { Text(stringResource(navigationItem.textResId)) },
                        colors = NavigationItemColors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            selectedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                            unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                            disabledIconColor = MaterialTheme.colorScheme.onPrimary,
                            disabledTextColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            }
        }
    ) { paddingValues ->

        AppNavGraph(
            modifier = Modifier
                .padding(paddingValues)
                .consumeWindowInsets(paddingValues),
            navController = navigationState.navHostController,
            newsFeedContent = {
                HomeScreen(
                    onCommentsClickListener = {
                        navigationState.navigateToComments(it)
                    }
                )
            },
            commentsContent = { feedPostId ->
                CommentScreen(
                    feedPostId = feedPostId,
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    }
                )
            },
            favouriteContent = {
                TextCounter("Favourite")
            },
            profileContent = {
                TextCounter("Profile")
            }
        )
    }
}


@Composable
fun TextCounter(name: String) {
    var count by rememberSaveable {
        mutableIntStateOf(0)
    }

    Text(
        text = "$name: $count",
        modifier = Modifier.clickable { count++ },
        fontSize = 32.sp
    )
}