package com.indiannewssroom.app.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.indiannewssroom.app.model.PostData
import com.indiannewssroom.app.util.Constants.Companion.POST_TABLE
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
@Entity(tableName = POST_TABLE)
data class PostEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var content: String,
    var postImage: String,
    var date: String,
    ): Parcelable