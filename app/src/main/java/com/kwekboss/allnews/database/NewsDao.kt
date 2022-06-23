package com.kwekboss.allnews.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kwekboss.allnews.model.Article

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news:Article)

    @Delete
    suspend fun delete(news:Article)

    @Query("Select * From news_database")
    fun allNews():LiveData<List<Article>>
}