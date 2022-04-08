package com.kwekboss.allnews.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kwekboss.allnews.api.RetrofitInstance
import com.kwekboss.allnews.model.Article
import com.kwekboss.allnews.model.NewsData
import retrofit2.Response

class Repository {

 inner class newsDataSource():PagingSource<Int,Article>(){
     override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
     return state.anchorPosition?.let {
         anchorPosition-> state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
         ?:state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
     }
     }

     override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        try{
            val currentPage = params.key ?:1
            val response = RetrofitInstance.retrofit.newsRequest(page = currentPage)
            val responseData = mutableListOf<Article>()
            val dataReceived =response.body()?.articles ?: emptyList()
            responseData.addAll(dataReceived)

            val prevKey = if (currentPage==1)null else currentPage.minus(1)
            return LoadResult.Page(responseData,prevKey,currentPage.plus(1))

        } catch (error:Exception){
            return LoadResult.Error(error)
        }
     }

 }

}