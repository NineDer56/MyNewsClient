package com.example.vknews.data.network

import com.example.vknews.data.model.NewsResponseDto
import retrofit2.http.GET

interface ApiService {

    @GET("latest?apikey=pub_9909b1f19ee047f29dd64cfa8c8bbf73")
    suspend fun getLatestNews() : NewsResponseDto

}