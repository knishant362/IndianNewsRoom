package com.indiannewssroom.app.data

import com.indiannewssroom.app.model.PostData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    @GET("/wp-json/wp/v2/posts")
    suspend fun getPostCategory(
        @Query("per_page") postPer : Int = 20,
        @Query("page") page : Int = 1,
        @Query("_embed") embed : String = "true",
        @Query("_fields[]") id : String = "id",
        @Query("_fields[]") title : String = "title",
        @Query("_fields[]") content : String = "content",
        @Query("_fields[]") date : String = "date",
        @Query("_fields[]") links : String = "_links",
        @Query("categories") categories: String = "29"

    ): List<PostData>

    @GET("/wp-json/wp/v2/posts")
    suspend fun getPostSlide(
        @Query("_embed") embed : String = "true",
        @Query("per_page") postPer : Int = 15,
        @Query("page") page : Int = 1,
        @Query("_fields[]") id : String = "id",
        @Query("_fields[]") title : String = "title",
        @Query("_fields[]") content : String = "content",
        @Query("_fields[]") date : String = "date",
        @Query("_fields[]") links : String = "_links",
        @Query("categories") cat1: String = "29",
        @Query("categories") cat2: String = "29",
        @Query("categories") cat3: String = "29",
        @Query("categories") cat4: String = "29",
        @Query("categories") cat5: String = "29",
        @Query("categories") cat6: String = "29"
    ): List<PostData>

}