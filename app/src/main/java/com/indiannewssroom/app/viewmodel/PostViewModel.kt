package com.indiannewssroom.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.indiannewssroom.app.data.database.PostDatabase
import com.indiannewssroom.app.data.database.PostEntity
import com.indiannewssroom.app.model.PostData
import com.indiannewssroom.app.repository.LocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(application: Application) : AndroidViewModel(application) {

    val readPosts : LiveData<List<PostEntity>>
    private val localDataSource : LocalDataSource

    init {
        val postDao = PostDatabase.getDatabase(application).postDao()
        localDataSource = LocalDataSource(postDao)
        readPosts = localDataSource.readPosts
    }

    fun addPost(postEntity: PostEntity){
        viewModelScope.launch(Dispatchers.IO) {
            localDataSource.addPost(postEntity)
        }
    }

    fun deletePost(postEntity: PostEntity) {
        viewModelScope.launch (Dispatchers.IO) {
            localDataSource.deletePost(postEntity)
        }
    }

    fun clearDatabase() {
        viewModelScope.launch (Dispatchers.IO) {
            localDataSource.clearDatabase()
        }
    }

}