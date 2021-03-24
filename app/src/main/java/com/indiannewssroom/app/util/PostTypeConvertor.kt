package com.indiannewssroom.app.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.indiannewssroom.app.model.PostData

class PostTypeConvertor {

    var gson = Gson()

    @TypeConverter
    fun postDataToString(postData: PostData): String{
        return gson.toJson(postData)
    }

    @TypeConverter
    fun  stringToPostData(data: String) : PostData {
        val listType = object : TypeToken<PostData>() {}.type
        return gson.fromJson(data, listType)
    }

}