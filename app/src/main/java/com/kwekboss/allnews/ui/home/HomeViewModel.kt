package com.kwekboss.allnews.ui.home
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kwekboss.allnews.api.RetrofitInstance
import com.kwekboss.allnews.model.Article
import com.kwekboss.allnews.model.NewsData
import com.kwekboss.allnews.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeViewModel(private val repository: Repository): ViewModel() {

    var getResponse = MutableLiveData<NewsData>()

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO){
            try{
               val response = repository.makeApiCall()
               if (response.isSuccessful) {
                   val webData = response.body()!!
                   getResponse.postValue(webData)
               }
           }
           catch (e:Exception){
               Log.i("Api",e.printStackTrace().toString())
           }


        }
    }



}

