package com.indiannewssroom.app.util

import androidx.recyclerview.widget.DiffUtil
import com.indiannewssroom.app.model.PostData

class PostDiffUtils(
    private val oldList: List<PostData>,
    private val newList: List<PostData>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id == newList[newItemPosition].id -> {
                false
            }
            oldList[oldItemPosition].title == newList[newItemPosition].title -> {
                false
            }
            else -> true
        }
    }
}
