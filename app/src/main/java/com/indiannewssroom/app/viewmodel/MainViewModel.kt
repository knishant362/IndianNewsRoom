package com.indiannewssroom.app.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.indiannewssroom.app.data.DataStoreRepository
import com.indiannewssroom.app.data.NetworkResults
import com.indiannewssroom.app.model.PostData
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
import com.indiannewssroom.app.util.Constants.Companion.TYPE_HORIZONTAL
import com.indiannewssroom.app.util.Constants.Companion.TYPE_SINGLE
import com.indiannewssroom.app.util.Constants.Companion.TYPE_VERTICAL
import com.indiannewssroom.app.util.Constants.Companion.bollywood_cinema
import com.indiannewssroom.app.util.Constants.Companion.entertainment
import com.indiannewssroom.app.util.Constants.Companion.humour
import com.indiannewssroom.app.util.Constants.Companion.latest_news
import com.indiannewssroom.app.util.Constants.Companion.viral_news
import com.indiannewssroom.app.util.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class MainViewModel(context: Context) : ViewModel(){

    /**  DATASTORE  */

    private val dataStoreRepository = DataStoreRepository(context)
    val readHome = dataStoreRepository.readHomeCategories

    fun saveHomeCategories(
        category1: String,
        category2: String,
        category3: String,
        category4: String,
        category5: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("Sauda",category1 )
        Log.d("Sauda",category2 )
        Log.d("Sauda",category3 )
        Log.d("Sauda",category4 )
        Log.d("Sauda",category5 )

        dataStoreRepository.saveHomeCategories(category1, category2, category3, category4, category5)
    }




    /**  DATASTORE  */

    /** Default Categories*/
//    var cat1 = viral_news.second
//    var cat2 = entertainment.second
//    var cat3 = bollywood_cinema.second
//    var cat4 = latest_news.second
//    var cat5 = humour.second

    /** Default Categories*/


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


    init {

    }


    fun fetchPosts(
        myCat1: String, myCat2: String, myCat3: String, myCat4: String, myCat5: String
    ) {
        Log.d("NishantCat", "$myCat1  $myCat2  $myCat3  $myCat4  $myCat5")
        viewModelScope.launch {
            myPosts.postValue(Resource.loading(null))
            try{

                coroutineScope{
                    val fetchPostCat0 = async{call.getPostCategory1(5,1,"389")}
                    val fetchPostsCat1 = async{call.getPostCategory1(5,1,myCat1)}
                    val fetchPostsCat2 = async{call.getPostCategory1(5,1,myCat2)}
                    val fetchPostsCat3 = async{call.getPostCategory1(5,1,myCat3)}
                    val fetchPostsCat4 = async{call.getPostCategory1(5,1,myCat4)}
                    val fetchPostsCat5 = async{call.getPostCategory1(5,1,myCat5)}

                    val post0 = fetchPostCat0.await()
                    val post1 = fetchPostsCat1.await()
                    val post2 = fetchPostsCat2.await()
                    val post3 = fetchPostsCat3.await()
                    val post4 = fetchPostsCat4.await()
                    val post5 = fetchPostsCat5.await()

                    val fetchposts = mutableListOf<PostData>()

                    fetchposts.addAll(post0)
                    fetchposts.addAll(post1)
                    fetchposts.addAll(post2)
                    fetchposts.addAll(post3)
                    fetchposts.addAll(post4)
                    fetchposts.addAll(post5)

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


    fun fetchPostSingle(category: String,type : String, perPage: Int, page: Int, fragmentName: String) {

        Log.d("SentSender", category)
        viewModelScope.launch {
            handleData(Resource.loading(null), fragmentName)
            try {
                val postsFromApi = call.getPostCategory1(perPage,page,category)
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

