package com.example.vknews.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.vknews.domain.FeedPost

@Composable
fun AppNavGraph(
    modifier: Modifier,
    navController: NavHostController,
    newsFeedContent: @Composable () -> Unit,
    commentsContent: @Composable (feedPostId: Int) -> Unit,
    favouriteContent: @Composable () -> Unit,
    profileContent: @Composable () -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        navigation(
            startDestination = Screen.NewsFeed.route,
            route = Screen.Home.route
        ) {

            composable(route = Screen.NewsFeed.route) {
                newsFeedContent()
            }

            composable(
                route = Screen.Comments.route,
                arguments = listOf(
                    navArgument(Screen.KEY_FEED_POST) {
                        type = FeedPost.NavigationType
                    }
                )
            ) {
                val feedPost = it.arguments?.getParcelable<FeedPost>(Screen.KEY_FEED_POST)
                    ?: throw RuntimeException("arguments are null")
                commentsContent(feedPost.id)
            }
        }

        composable(route = Screen.Favourite.route) {
            favouriteContent()
        }

        composable(route = Screen.Profile.route) {
            profileContent()
        }
    }

}