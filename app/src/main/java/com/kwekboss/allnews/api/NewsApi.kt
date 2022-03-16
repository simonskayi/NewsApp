package com.kwekboss.allnews.api

import com.kwekboss.allnews.model.Article
import com.kwekboss.allnews.model.NewsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
   @GET("/v2/top-headlines")
    suspend fun newsRequest(
   @Query("country")
    languages:String ="us",
   @Query("page")
      page:Int = 1,
   @Query("apiKey")
   apiKey:String = AccessKey.apiKey
    ):Response<NewsData>

  @GET("/v2/everything")
    suspend fun searchRequest(
    @Query("q")
    searchQuery:String,
    @Query("page")
    page:Int = 1,
    @Query("apiKey")
    apiKey:String = AccessKey.apiKey
    ):Response<List<Article>>


}