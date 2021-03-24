package com.indiannewssroom.app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indiannewssroom.app.model.PostData
import com.indiannewssroom.app.repository.RemoteDataSource
import com.indiannewssroom.app.util.Resource
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {
    // TODO: Implement the ViewModel


    private val call = RemoteDataSource()
    val isFirst = false
    private val postResponse = MutableLiveData<Resource<List<PostData>>>()

    fun fetchPostSingle(category: String,perPage: Int, page: Int) {

        Log.d("SentSender", category)
        viewModelScope.launch {
            postResponse.postValue(Resource.loading(null))
            try {
                val postsFromApi = call.getPostCategory(perPage,page,category)
                postResponse.postValue(Resource.success(postsFromApi))
            } catch (e: Exception) {
                postResponse.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getPosts(): LiveData<Resource<List<PostData>>> {
        return postResponse
    }

}