package com.indiannewssroom.app.data

import com.indiannewssroom.app.model.PostData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/wp-json/wp/v2/posts")
    suspend fun getPost(
            @Query("categories") categories: String = "29",
            @Query("per_page") postPer: Int = 5,
            @Query("_embed") embed: String = "true",
    ): Response<List<PostData>>

    @GET("/wp-json/wp/v2/posts")
    suspend fun getPostCategoryPage(
            @Query("per_page") postPer : Int = 10,
            @Query("page") page : Int = 5,
            @Query("_embed") embed : String = "true",
            @Query("categories") categories: String = "29"

    ): Response<List<PostData>>
}