package com.example.vknews.data.model

import com.google.gson.annotations.SerializedName

data class NewsItemDto(
    @SerializedName("article_id") val articleId : String,
    @SerializedName("title") val title : String?,
    @SerializedName("link") val link : String?,
    @SerializedName("creator") val creator : List<String>?,
    @SerializedName("description") val description : String?,
    @SerializedName("pubDate") val pubDate : String?,
    @SerializedName("image_url") val imageUrl : String?,
    @SerializedName("video_url") val videoUrl : String?,

    @SerializedName("source_name") val sourceName : String?,
    @SerializedName("source_url") val sourceUrl : String?,
    @SerializedName("source_icon") val sourceIcon : String?
)
