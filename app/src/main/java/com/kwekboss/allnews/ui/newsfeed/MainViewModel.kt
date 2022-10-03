package com.kwekboss.allnews.ui.newsfeed


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.kwekboss.allnews.database.NewsDatabase
import com.kwekboss.allnews.model.Article
import com.kwekboss.allnews.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository:Repository
    val getSavedNews: LiveData<List<Article>>
    private var _searchResponse = MutableLiveData<List<Article>>()
    val searchResponse:LiveData<List<Article>> = _searchResponse

    init {
    val newsDB = NewsDatabase.getDatabase(application).getNewsDao()
    repository = Repository(newsDB)
    getSavedNews = repository.getAllNews()
}
    val newsData = Pager(PagingConfig(pageSize = 20)){
      repository.NewsDataSource()
  }.liveData.cachedIn(viewModelScope)


    fun saveNews(news:Article){
        viewModelScope.launch (Dispatchers.IO){
            repository.saveNews(news)
        }
    }

    fun deleteNews(news:Article){
        viewModelScope.launch (Dispatchers.IO){
        repository.deleteNews(news)
    }
    }
    fun getSearch(searchKeyword:String){
        viewModelScope.launch {
            val response = repository.searchNews(searchKeyword)
            try {
                if (response.isSuccessful){
                    val responseData = response.body()?.articles
                    _searchResponse.postValue(responseData)
                }
            } catch (e:Exception){
                Log.d("Search Response", response.errorBody().toString())
            }
        }
    }


}

