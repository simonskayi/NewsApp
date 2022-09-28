package com.kwekboss.allnews.database

import androidx.room.TypeConverter
import com.kwekboss.allnews.model.Source

class TypeConvertors {

    @TypeConverter
    fun fromSource(source:Source):String{
        return source.name
    }
    @TypeConverter
    fun toSource(name:String):Source{
        return Source(name,name)
    }
}
