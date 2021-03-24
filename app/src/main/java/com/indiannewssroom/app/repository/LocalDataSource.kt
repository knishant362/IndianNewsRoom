package com.indiannewssroom.app.repository

import androidx.lifecycle.LiveData
import com.indiannewssroom.app.data.database.PostDao
import com.indiannewssroom.app.data.database.PostEntity

class LocalDataSource(private val postDao: PostDao) {

    val readPosts : LiveData<List<PostEntity>> = postDao.readPosts()

    suspend fun addPost (postEntity: PostEntity) {
        postDao.insertPost(postEntity)
    }
    suspend fun deletePost (postEntity: PostEntity) {
        postDao.deletePost(postEntity)
    }

    suspend fun clearDatabase() {
        postDao.clearDatabase()
    }


}