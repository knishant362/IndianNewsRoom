package com.indiannewssroom.app.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.indiannewssroom.app.data.NetworkResults
import com.indiannewssroom.app.repository.RemoteDataSource
import com.indiannewssroom.app.model.PostData
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel() : ViewModel(){

    private val _postResponseSingle = MutableLiveData<NetworkResults<List<PostData>>>()
    val postResponseSingle : LiveData<NetworkResults<List<PostData>>> get() = _postResponseSingle
    var postResponseHorizontal: MutableLiveData<NetworkResults<List<PostData>>> = MutableLiveData()
    var postResponseVertical: MutableLiveData<NetworkResults<List<PostData>>> = MutableLiveData()

    var mediatorLiveData = MediatorLiveData<NetworkResults<List<PostData>>>()

    private val call = RemoteDataSource()
    var horizontalList = arrayListOf<PostData>()
    var verticalList = arrayListOf<PostData>()
    private val postOnHome = 5

    init{
        apiCallCategory("29", "horizontal",postOnHome)
        apiCallCategory("2697", "vertical",postOnHome)
        apiCallCategory("2701", "vertical",postOnHome)
        apiCallCategory("30", "vertical",postOnHome)
        apiCallCategory("2698", "vertical",postOnHome)

        mediatorLiveData.addSource(postResponseSingle){
            mediatorLiveData.value = it
            Log.d("HelloDAtaa", it.data?.size.toString())
        }

    }

    fun apiCall(category: String, per_page: Int) = viewModelScope.launch {
        safeApiCallCategory(category, "single", per_page)
    }

    private fun apiCallCategory(category: String, type : String, per_page: Int) = viewModelScope.launch {
        safeApiCallCategory(category, type, per_page)
    }

    private suspend fun safeApiCallCategory(category: String, type: String, per_page: Int) {
        val response = call.getPostCategory(category, per_page)
        when(type){
            "horizontal" -> {
                Log.d("CategoryHorizontal", "api called with pageno $category")
                postResponseHorizontal.value = handleResponse(response, type)
            }
            "single" -> {
                val data = handleResponse(response, type)
                Log.d("CategorySingle", "api called with pageno ${data.data!!.size}")
                _postResponseSingle.value = handleResponse(response, type)
            }
            "vertical" -> {
                Log.d("CategoryVertical", "api called with pageno ")
                postResponseVertical.value = handleResponse(response, type)
            }
        }
    }

    private fun handleResponse(response: Response<List<PostData>>, type : String): NetworkResults<List<PostData>> {
        return when {
            response.message().toString().contains("timeout") -> {
                NetworkResults.Error("Timeout")
            }
            response.code() == 402 -> {
                NetworkResults.Error("API Key Limited.")
            }
            response.isSuccessful -> {
                val posts = response.body()
                return handleSuccess(posts, type)
            }
            else -> {
                NetworkResults.Error(response.message())
            }
        }
    }

    private fun handleSuccess(posts: List<PostData>?, type : String): NetworkResults.Success<List<PostData>> {
        if (posts != null) {
            when(type){
                "horizontal" -> {
                    horizontalList.addAll(posts)
                }

                "vertical" -> {
                    verticalList.addAll(posts)
                }
            }
        }
        return when(type){
            "horizontal" -> {
                NetworkResults.Success(horizontalList)
            }
            "vertical" -> {
                NetworkResults.Success(verticalList)
            }
            else -> {
                Log.d("CategorySingleP", "api called with pageno ${posts!!.size}")
                NetworkResults.Success(posts!!)
            }
        }
    }
}
