package com.indiannewssroom.app.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookmarkData (
    var id: Int,
    var title: String,
    var content: String,
    var postImage: String,
    var date: String,
): Parcelable