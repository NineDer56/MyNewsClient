package com.example.vknews.data.mapper

import com.example.vknews.data.model.NewsItemDto
import com.example.vknews.data.model.NewsResponseDto
import com.example.vknews.domain.news.NewsItem
import com.example.vknews.domain.news.NewsResponse
import javax.inject.Inject

class NewsMapper @Inject constructor(){

    fun newsResponseDtoToEntity(dto: NewsResponseDto): NewsResponse {
        return NewsResponse(
            status = dto.status,
            newsItems = dto.newsItems.map { resultDtoToEntity(it) },
            nextPage = dto.nextPage
        )
    }

    fun resultDtoToEntity(dto: NewsItemDto): NewsItem {
        return NewsItem(
            articleId = dto.articleId,
            title = dto.title ?: "",
            link = dto.link ?: "",
            keywords = dto.keywords ?: emptyList(),
            creator = dto.creator ?: emptyList(),
            description = dto.description ?: "",
            pubDate = dto.pubDate ?: "",
            imageUrl = dto.imageUrl ?: "",
            videoUrl = dto.videoUrl ?: "",
            sourceName = dto.sourceName ?: "",
            sourceUrl = dto.sourceUrl ?: "",
            sourceIcon = dto.sourceIcon ?: ""
        )
    }

}