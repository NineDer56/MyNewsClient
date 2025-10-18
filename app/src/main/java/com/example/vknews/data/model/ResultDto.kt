package com.example.vknews.data.model

import com.google.gson.annotations.SerializedName

data class ResultDto(
    @SerializedName("article_id") val articleId : String,
    @SerializedName("title") val title : String,
    @SerializedName("link") val link : String,
    @SerializedName("creator") val creator : List<String>,
    @SerializedName("description") val description : String,
    @SerializedName("pubDate") val pubDate : String,
    @SerializedName("image_url") val imageUrl : String,
    @SerializedName("video_url") val videoUrl : String
)
