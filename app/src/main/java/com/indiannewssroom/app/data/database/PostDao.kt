package com.indiannewssroom.app.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("SELECT * FROM post_table ORDER BY id ASC")
    fun readPosts(): LiveData<List<PostEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(postEntity: PostEntity)

    @Delete
    fun deletePost(postEntity: PostEntity)

    @Query("DELETE FROM post_table")
    fun clearDatabase(): Int

}