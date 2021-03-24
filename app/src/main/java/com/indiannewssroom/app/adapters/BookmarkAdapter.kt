package com.indiannewssroom.app.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.indiannewssroom.app.data.database.PostEntity
import com.indiannewssroom.app.databinding.BookmarkRowLayoutBinding
import com.indiannewssroom.app.model.BookmarkData
import com.indiannewssroom.app.ui.activity.DetailsActivity
import com.indiannewssroom.app.ui.activity.bookmark.BookmarkDetailActivity
import com.indiannewssroom.app.util.Constants
import com.indiannewssroom.app.util.Constants.Companion.BUNDLE_DATA
import com.indiannewssroom.app.util.Constants.Companion.INTENT_DATA
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class BookmarkAdapter(val context: Context) : RecyclerView.Adapter<BookmarkAdapter.MyViewHolder>() {

    private var postdata = arrayListOf<PostEntity>()

    class MyViewHolder(private val binding: BookmarkRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(postData: PostEntity, context: Context) {
            val postDataOne = postData
            binding.txtPostTitle.text = postDataOne.title
            binding.txtUpldTime.text = postData.date
            binding.imgPost.apply {
                val c = CoroutineScope(Dispatchers.IO)
                c.run {
                    load(postDataOne.postImage){
                        crossfade(600)
                    }
                }
            }
            binding.clPostRow.setOnClickListener {
                if (postDataOne!=null){
                    val intent = Intent(context, BookmarkDetailActivity::class.java)
                    val bundle = Bundle()
//                    BookmarkData
                    val da = BookmarkData(0, postDataOne.title, postDataOne.content, postDataOne.postImage, postDataOne.date)
                    bundle.putParcelable(BUNDLE_DATA, da)
                    intent.putExtra(INTENT_DATA, bundle)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val mView = BookmarkRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(mView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val postResult = postdata[position]
        holder.bind(postResult, context)
    }

    override fun getItemCount(): Int {
        Log.d("MyPos", postdata.size.toString())
        return postdata.size
    }

    fun setData(data: List<PostEntity>) {
        postdata.addAll(data)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


}