package com.example.vknews.data.repository

import android.util.Log
import com.example.vknews.data.mapper.NewsMapper
import com.example.vknews.data.network.ApiFactory
import com.example.vknews.domain.news.NewsItem
import com.example.vknews.domain.repository.NewsRepository

class NewsRepositoryImpl : NewsRepository {

    private val apiService = ApiFactory().apiService
    private val mapper = NewsMapper()

    private var currentPage: String? = null

    override suspend fun getLatestNews(): Result<List<NewsItem>> {

        return runCatching {
            val response = apiService.getLatestNews(currentPage)
            currentPage = response.nextPage
            Log.d("OkHttp", currentPage.toString())

            val results = response.newsItems
                .map {
                    mapper.resultDtoToEntity(it)
                }
            results
        }
    }
}