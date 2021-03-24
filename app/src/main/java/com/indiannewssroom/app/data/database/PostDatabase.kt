package com.indiannewssroom.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.indiannewssroom.app.util.Constants.Companion.POST_DATABASE
import com.indiannewssroom.app.util.PostTypeConvertor


@Database(entities = [(PostEntity::class)], version = 1, exportSchema = false)

@TypeConverters(PostTypeConvertor::class)
abstract class PostDatabase : RoomDatabase(){

    abstract fun postDao(): PostDao

    companion object {

        @Volatile
        private var INSTANCE : PostDatabase? = null

        fun getDatabase (context: Context): PostDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, PostDatabase::class.java, POST_DATABASE).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}