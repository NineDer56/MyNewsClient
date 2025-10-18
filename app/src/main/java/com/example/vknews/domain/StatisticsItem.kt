package com.example.vknews.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StatisticsItem(
    val type: StatisticsType,
    val count : Int
) : Parcelable


enum class StatisticsType{
    LIKES, COMMENTS, REPOSTS, VIEWS
}
