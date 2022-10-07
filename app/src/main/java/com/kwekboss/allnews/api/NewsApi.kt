package com.kwekboss.allnews.api

import com.kwekboss.allnews.api.Constants.API_KEY
import com.kwekboss.allnews.api.Constants.NEWS_END_POINT
import com.kwekboss.allnews.api.Constants.SEARCH_NEWS_END_POINT
import com.kwekboss.allnews.model.NewsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
   @GET(NEWS_END_POINT)
    suspend fun newsRequest(
   @Query("language")
    languages:String ="en",
   @Query("page")
      page:Int,
   @Query("apiKey")
   apiKey:String = API_KEY
    ):Response<NewsData>

  @GET(SEARCH_NEWS_END_POINT)
    suspend fun searchRequest(
    @Query("q")
    searchQuery:String,
    @Query("page")
    page:Int = 1,
    @Query("apiKey")
    apiKey:String = API_KEY
    ):Response<NewsData>


}