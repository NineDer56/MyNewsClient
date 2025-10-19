package com.example.vknews.data.network

import com.example.vknews.data.model.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("latest?apikey=pub_9909b1f19ee047f29dd64cfa8c8bbf73&language=ru")
    suspend fun getLatestNews(
        @Query("page") page: String?
    ): NewsResponseDto
}