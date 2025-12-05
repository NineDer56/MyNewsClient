package com.example.vknews.di

import com.example.vknews.data.network.ApiService
import com.example.vknews.data.repository.NewsRepositoryImpl
import com.example.vknews.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.sync.Mutex
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
interface  NetworkModule {

    @Singleton
    @Binds
    fun bindNewsRepository(newsRepository: NewsRepositoryImpl): NewsRepository

    companion object {
        private const val BASE_URL = "https://newsdata.io/api/1/"

        @Singleton
        @Provides
        fun provideRetrofit(
            okHttpClient: OkHttpClient
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }

        @Singleton
        @Provides
        fun provideOkHttpsClient(
            interceptor: HttpLoggingInterceptor
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
        }

        @Singleton
        @Provides
        fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
                redactHeader("Authorization")
                redactHeader("X-Api-Key")
            }
        }

        @Singleton
        @Provides
        fun provideApiService(
            retrofit: Retrofit
        ): ApiService {
            return retrofit.create(ApiService::class.java)
        }

        @Singleton
        @Provides
        fun provideMutex() : Mutex {
            return Mutex()
        }
    }
}