package com.indiannewssroom.app.repository

import android.util.Log
import com.indiannewssroom.app.data.ApiService
import com.indiannewssroom.app.model.PostData
import com.indiannewssroom.app.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteDataSource {

    private fun okHttpProvider(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30 , TimeUnit.SECONDS)
            .connectTimeout(30 , TimeUnit.SECONDS)
            .build()
    }

    private val apicall = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpProvider())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
    
    suspend fun getPostSlide(category: List<String> ): List<PostData> {
        Log.d("Batma", category.toString())
        return apicall.getPostSlide(
            cat1 = category[0],
            cat2 = category[1],
            cat3 = category[2],
            cat4 = category[3],
            cat5 = category[4],
            cat6 = category[5]
        )
    }
    suspend fun getPostCategory(perPage: Int, page: Int, category: String ): List<PostData> {
        return apicall.getPostCategory(categories = category, page = page, postPer = perPage)
    }

}