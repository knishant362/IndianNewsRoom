package com.indiannewssroom.app.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.indiannewssroom.app.data.NetworkResults
import com.indiannewssroom.app.repository.RemoteDataSource
import com.indiannewssroom.app.model.PostData
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_A
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_B
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_C
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_D
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_E
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_F
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_G
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_H
import com.indiannewssroom.app.util.Constants.Companion.TYPE_HORIZONTAL
import com.indiannewssroom.app.util.Constants.Companion.TYPE_SINGLE
import com.indiannewssroom.app.util.Constants.Companion.TYPE_VERTICAL
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel() : ViewModel(){

    private val _postResponseA = MutableLiveData<NetworkResults<List<PostData>>>()
    val postResponseA : LiveData<NetworkResults<List<PostData>>> get() = _postResponseA

    private val _postResponseB = MutableLiveData<NetworkResults<List<PostData>>>()
    val postResponseB : LiveData<NetworkResults<List<PostData>>> get() = _postResponseB

    private val _postResponseC = MutableLiveData<NetworkResults<List<PostData>>>()
    val postResponseC : LiveData<NetworkResults<List<PostData>>> get() = _postResponseC

    private val _postResponseD = MutableLiveData<NetworkResults<List<PostData>>>()
    val postResponseD : LiveData<NetworkResults<List<PostData>>> get() = _postResponseD

    private val _postResponseE = MutableLiveData<NetworkResults<List<PostData>>>()
    val postResponseE : LiveData<NetworkResults<List<PostData>>> get() = _postResponseE

    private val _postResponseF = MutableLiveData<NetworkResults<List<PostData>>>()
    val postResponseF : LiveData<NetworkResults<List<PostData>>> get() = _postResponseF

    private val _postResponseG = MutableLiveData<NetworkResults<List<PostData>>>()
    val postResponseG : LiveData<NetworkResults<List<PostData>>> get() = _postResponseG

    private val _postResponseH = MutableLiveData<NetworkResults<List<PostData>>>()
    val postResponseH : LiveData<NetworkResults<List<PostData>>> get() = _postResponseH

    var postResponseHorizontal: MutableLiveData<NetworkResults<List<PostData>>> = MutableLiveData()
    var postResponseVertical: MutableLiveData<NetworkResults<List<PostData>>> = MutableLiveData()

    private val call = RemoteDataSource()
    var horizontalList = arrayListOf<PostData>()
    var verticalList = arrayListOf<PostData>()
    private val postOnHome = 5

    init{
        apiCallCategory("29", TYPE_HORIZONTAL,postOnHome)
        apiCallCategory("2697", TYPE_VERTICAL,postOnHome)
        apiCallCategory("2701", TYPE_VERTICAL,postOnHome)
        apiCallCategory("30", TYPE_VERTICAL,postOnHome)
        apiCallCategory("2698", TYPE_VERTICAL,postOnHome)
    }

    fun apiCall(category: String, per_page: Int, fragmentName: String) = viewModelScope.launch {
        safeApiCallCategory(category, "single", per_page, fragmentName)
    }

    private fun apiCallCategory(category: String, type : String, per_page: Int) = viewModelScope.launch {
        safeApiCallCategory(category, type, per_page)
    }

    private suspend fun safeApiCallCategory(category: String, type: String, per_page: Int, fragmentName: String = "default") {
        val response = call.getPostCategory(category, per_page)
        when(type){
            TYPE_HORIZONTAL -> {
                Log.d("CategoryHorizontal", "api called with pageno $category")
                postResponseHorizontal.value = handleResponse(response, type)
            }
            TYPE_SINGLE -> {
                val data = handleResponse(response, type)
                Log.d("CategorySingle", "api called with pageno $category")
                Log.d("CategorySingle", "api called with pageno ${data.data!!.size}")

                handleSingleData(fragmentName, response, type)
            }
            TYPE_VERTICAL -> {
                Log.d("CategoryVertical", "api called with pageno ")
                postResponseVertical.value = handleResponse(response, type)
            }
        }
    }

    /**Here we determine from where the request is called*/
    /**After determine that using FRAGMENT_NAME_$ , then we handle response accordingly */
    /**We add data to their respective LiveData instance (ex - FragmentA -> _postResponseA) */
    private fun handleSingleData(fragmentName: String, response: Response<List<PostData>>, type: String) {
        when(fragmentName){
            FRAGMENT_NAME_A->{
                _postResponseA.value = handleResponse(response, type)
            }
            FRAGMENT_NAME_B-> {
                _postResponseB.value = handleResponse(response, type)
            }
            FRAGMENT_NAME_C->{
                _postResponseC.value = handleResponse(response, type)
            }
            FRAGMENT_NAME_D-> {
                _postResponseD.value = handleResponse(response, type)
            }
            FRAGMENT_NAME_E->{
                _postResponseE.value = handleResponse(response, type)
            }
            FRAGMENT_NAME_F-> {
                _postResponseF.value = handleResponse(response, type)
            }
            FRAGMENT_NAME_G->{
                _postResponseG.value = handleResponse(response, type)
            }
            FRAGMENT_NAME_H-> {
                _postResponseH.value = handleResponse(response, type)
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
                TYPE_HORIZONTAL -> {
                    horizontalList.addAll(posts)
                }
                TYPE_VERTICAL -> {
                    verticalList.addAll(posts)
                }
            }
        }
        return when(type){
            TYPE_HORIZONTAL -> {
                NetworkResults.Success(horizontalList)
            }
            TYPE_VERTICAL -> {
                NetworkResults.Success(verticalList)
            }
            else -> {
                Log.d("CategorySingleP", "api called with pageno ${posts!!.size}")
                NetworkResults.Success(posts)
            }
        }
    }
}
