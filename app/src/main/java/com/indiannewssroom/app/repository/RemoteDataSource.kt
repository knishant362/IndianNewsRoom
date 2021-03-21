package com.indiannewssroom.app.repository

import com.indiannewssroom.app.data.ApiService
import com.indiannewssroom.app.model.PostData
import com.indiannewssroom.app.util.Constants.Companion.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    private val apicall = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
    
    suspend fun getPostCategory(perPage: Int, page: Int, category: String ): Response<List<PostData>> {
        return apicall.getPostCategoryPage(categories = category, page = page, postPer = perPage)
    }
    suspend fun getPostCategory1(perPage: Int, page: Int, category: String ): List<PostData> {
        return apicall.getPostCategoryPage1(categories = category, page = page, postPer = perPage)
    }


}