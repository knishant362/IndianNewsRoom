package com.indiannewssroom.app.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.indiannewssroom.app.data.database.PostDao
import com.indiannewssroom.app.data.database.PostDatabase
import com.indiannewssroom.app.data.database.PostEntity
import com.indiannewssroom.app.model.PostData
import com.indiannewssroom.app.repository.LocalDataSource
import com.indiannewssroom.app.repository.RemoteDataSource
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_A
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_B
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_C
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_D
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_E
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_F
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_G
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_H
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_I
import com.indiannewssroom.app.util.Resource
import kotlinx.coroutines.*

class MainViewModel : ViewModel(){

    private val call = RemoteDataSource()
    val isFirst = false

    private val myPosts = MutableLiveData<Resource<List<PostData>>>()
    private val postsA = MutableLiveData<Resource<List<PostData>>>()
    private val postsB = MutableLiveData<Resource<List<PostData>>>()
    private val postsC = MutableLiveData<Resource<List<PostData>>>()
    private val postsD = MutableLiveData<Resource<List<PostData>>>()
    private val postsE = MutableLiveData<Resource<List<PostData>>>()
    private val postsF = MutableLiveData<Resource<List<PostData>>>()
    private val postsG = MutableLiveData<Resource<List<PostData>>>()
    private val postsH = MutableLiveData<Resource<List<PostData>>>()
    private val postsI = MutableLiveData<Resource<List<PostData>>>()


    fun fetchPosts(
        slideCat: List<String>,
        verticalCat: List<String>
    ) {
        viewModelScope.launch {
            myPosts.postValue(Resource.loading(null))
            try{

                coroutineScope{
                    Log.d("LOLA", slideCat.toString())
                    Log.d("LOLA", verticalCat.toString())
                    val fetchSlide = async{call.getPostSlide(slideCat)}
                    val fetchCat0 = async{call.getPostCategory(5,1,verticalCat[0])}
                    val fetchCat1 = async{call.getPostCategory(5,1,verticalCat[1])}
                    val fetchCat2 = async{call.getPostCategory(5,1,verticalCat[2])}
                    val fetchCat3 = async{call.getPostCategory(5,1,verticalCat[3])}
                    val fetchCat4 = async{call.getPostCategory(5,1,verticalCat[4])}
                    val fetchCat5 = async{call.getPostCategory(5,1,verticalCat[5])}


                    val slides = fetchSlide.await()
                    val post0 = fetchCat0.await()
                    val post1 = fetchCat1.await()
                    val post2 = fetchCat2.await()
                    val post3 = fetchCat3.await()
                    val post4 = fetchCat4.await()
                    val post5 = fetchCat5.await()

                    val fetchposts = mutableListOf<PostData>()


                    fetchposts.addAll(slides)
                    fetchposts.addAll(post0)
                    fetchposts.addAll(post1)
                    fetchposts.addAll(post2)
                    fetchposts.addAll(post3)
                    fetchposts.addAll(post4)
                    fetchposts.addAll(post5)

                    Log.d("MotherBox", fetchposts.size.toString())

                    myPosts.postValue(Resource.success(fetchposts))
                }
            }catch (e: Exception) {
                myPosts.postValue(Resource.error("Something Went Wrong", null))
            }
        }
    }

    fun getPosts(): LiveData<Resource<List<PostData>>> {
        return myPosts
    }


    fun fetchPostSingle(category: String,perPage: Int, page: Int, fragmentName: String) {

        Log.d("SentSender", category)
        viewModelScope.launch {
            handleData(Resource.loading(null), fragmentName)
            try {
                val postsFromApi = call.getPostCategory(perPage,page,category)
                handleData(Resource.success(postsFromApi), fragmentName)
            } catch (e: Exception) {
                handleData(Resource.error(e.toString(), null), fragmentName)
            }
        }
    }


    private fun handleData(
        response : Resource<List<PostData>>,
        fragmentName: String
    ) {
        when(fragmentName){
            FRAGMENT_NAME_A->{
                postsA.postValue(response)
            }
            FRAGMENT_NAME_B-> {
                postsB.postValue(response)
            }
            FRAGMENT_NAME_C->{
                postsC.postValue(response)
            }
            FRAGMENT_NAME_D-> {
                postsD.postValue(response)
            }
            FRAGMENT_NAME_E->{
                postsE.postValue(response)
            }
            FRAGMENT_NAME_F-> {
                postsF.postValue(response)
            }
            FRAGMENT_NAME_G->{
                postsG.postValue(response)
            }
            FRAGMENT_NAME_H-> {
                postsH.postValue(response)
            }
            FRAGMENT_NAME_I-> {
                postsI.postValue(response)
            }
        }
    }


    fun getPostFA(): LiveData<Resource<List<PostData>>> {
        return postsA
    }
    fun getPostFB(): LiveData<Resource<List<PostData>>> {
        return postsB
    }
    fun getPostFC(): LiveData<Resource<List<PostData>>> {
        return postsC
    }
    fun getPostFD(): LiveData<Resource<List<PostData>>> {
        return postsD
    }
    fun getPostFE(): LiveData<Resource<List<PostData>>> {
        return postsE
    }
    fun getPostFF(): LiveData<Resource<List<PostData>>> {
        return postsF
    }
    fun getPostFG(): LiveData<Resource<List<PostData>>> {
        return postsG
    }
    fun getPostFH(): LiveData<Resource<List<PostData>>> {
        return postsH
    }
    fun getPostFI(): LiveData<Resource<List<PostData>>> {
        return postsI
    }

}

