package com.example.vknews.domain

import android.os.Parcelable
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import com.example.vknews.R
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedPost(
    val id : Int,
    val groupName : String = "/dev/null",
    val groupPic : Int = R.drawable.post_comunity_thumbnail,
    val postTime : String = "14:00",
    val postText : String = "Jetpack Compose — декларативный фреймворк для разработки пользовательских интерфейсов на языке программирования Kotlin для приложений на платформе Android.",
    val postPic : Int = R.drawable.post_content_image,
    val statistics: List<StatisticsItem> = listOf(
        StatisticsItem(StatisticsType.LIKES, 45),
        StatisticsItem(StatisticsType.COMMENTS, 12),
        StatisticsItem(StatisticsType.REPOSTS, 6),
        StatisticsItem(StatisticsType.VIEWS, 92)
    )
) : Parcelable {

    companion object{

        val NavigationType : NavType<FeedPost> = object : NavType<FeedPost>(false) {
            override fun put(bundle: SavedState, key: String, value: FeedPost) {
                bundle.putParcelable(key, value)
            }

            override fun get(bundle: SavedState, key: String): FeedPost? {
                return bundle.getParcelable(key)
            }

            override fun parseValue(value: String): FeedPost {
                return Gson().fromJson(value, FeedPost::class.java)
            }
        }
    }
}