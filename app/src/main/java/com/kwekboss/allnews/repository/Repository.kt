package com.kwekboss.allnews.repository

import com.kwekboss.allnews.api.RetrofitInstance
import com.kwekboss.allnews.model.NewsData
import retrofit2.Response

class Repository {
    suspend fun makeApiCall(): Response<NewsData> {
        return RetrofitInstance.webRequest.newsRequest()
    }
}