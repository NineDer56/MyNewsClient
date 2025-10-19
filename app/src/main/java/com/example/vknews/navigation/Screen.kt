package com.example.vknews.navigation

import android.net.Uri

sealed class Screen(
    val route: String
) {
    data object Home : Screen(ROUTE_HOME)
    data object NewsFeed : Screen(ROUTE_NEWS_FEED)

//    data object Comments : Screen(ROUTE_COMMENTS) {
//        private const val ROUTE_FOR_ARGS = "route_comments"
//
//        fun getRouteWithArgs(feedPost: FeedPost): String {
//            val feedPostGson = Gson().toJson(feedPost)
//            Log.d("gson", feedPostGson)
//            return "${ROUTE_FOR_ARGS}/${feedPostGson.encode()}"
//        }
//    }

    data object Favourite : Screen(ROUTE_FAVOURITE)
    data object Profile : Screen(ROUTE_PROFILE)

    companion object {

        const val KEY_FEED_POST = "feed_post"

        const val ROUTE_HOME = "route_home"
        const val ROUTE_NEWS_FEED = "route_news_feed"
        const val ROUTE_COMMENTS = "route_comments/{$KEY_FEED_POST}"

        const val ROUTE_FAVOURITE = "route_favourite"
        const val ROUTE_PROFILE = "route_profile"
    }
}

fun String.encode() : String{
    return Uri.encode(this)
}