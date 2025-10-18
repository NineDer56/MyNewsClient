package com.example.vknews.data.model

import com.google.gson.annotations.SerializedName

data class NewsResponseDto(
    @SerializedName("status") val status : String,
    @SerializedName("results") val newsItems : List<NewsItemDto>,
    @SerializedName("nextPage") val nextPage : String
)