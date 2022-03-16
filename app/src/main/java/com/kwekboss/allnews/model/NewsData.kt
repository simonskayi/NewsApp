package com.kwekboss.allnews.model

data class NewsData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)