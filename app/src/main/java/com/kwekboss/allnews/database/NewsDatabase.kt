package com.kwekboss.allnews.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kwekboss.allnews.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class NewsDatabase:RoomDatabase() {
    abstract fun getNewsDao():NewsDao

    companion object{
        @Volatile
        private var INSTANCE:NewsDatabase?=null
        fun getDatabase(context: Context):NewsDatabase{
            val tempInstance= INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "news_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}