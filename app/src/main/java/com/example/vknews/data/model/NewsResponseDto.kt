package com.example.vknews.data.model

import com.example.vknews.domain.news.Result
import com.google.gson.annotations.SerializedName

data class NewsResponseDto(
    @SerializedName("status") val status : String,
    @SerializedName("results") val results : List<Result>,
    @SerializedName("nextPage") val nextPage : String
)