package com.kwekboss.allnews.ui.home
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kwekboss.allnews.api.RetrofitInstance
import com.kwekboss.allnews.model.Article
import com.kwekboss.allnews.model.NewsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeViewModel(): ViewModel() {

    var getResponse = MutableLiveData<NewsData>()

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO){
            try{
               val response = RetrofitInstance.retrofit.newsRequest()
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

