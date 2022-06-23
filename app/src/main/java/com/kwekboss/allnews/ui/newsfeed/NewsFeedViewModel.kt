package com.kwekboss.allnews.ui.newsfeed


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.kwekboss.allnews.repository.Repository



class NewsFeedViewModel(private val repository: Repository) : ViewModel() {

  val newsData = Pager(PagingConfig(pageSize = 20)){
      repository.NewsDataSource()
  }.liveData.cachedIn(viewModelScope)
}

