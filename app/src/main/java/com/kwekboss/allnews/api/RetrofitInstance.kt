package com.kwekboss.allnews.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val Base_url = "https://newsapi.org"
    val retrofit = Retrofit.Builder()
        .baseUrl(Base_url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

       val webRequest by lazy {
           retrofit.create(NewsApi::class.java)
       }
}