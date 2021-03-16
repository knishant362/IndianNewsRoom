package com.indiannewssroom.app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.indiannewssroom.app.databinding.HorizontalPostRowLayoutBinding
import com.indiannewssroom.app.databinding.PostRowLayoutBinding
import com.indiannewssroom.app.model.PostData
import com.indiannewssroom.app.util.PostDiffUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class HorizontalAdapter() : RecyclerView.Adapter<HorizontalAdapter.MyViewHolder>() {

    private var postdata = arrayListOf<PostData>()

    class MyViewHolder(private val binding: HorizontalPostRowLayoutBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(postData: PostData) {
            binding.txtposttitle.text = postData.title.rendered
            binding.imgPostImage.apply {
                if (postData?.embedded?.wpFeaturedmedia != null){
                    val c = CoroutineScope(Dispatchers.IO)
                    c.run {
                        load(postData.embedded.wpFeaturedmedia[0].sourceUrl){
                            crossfade(600)
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val mView = HorizontalPostRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(mView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val postResult = postdata[position]
        holder.bind(postResult)
    }

    override fun getItemCount(): Int {
        return postdata.size
    }

    fun setData(data: List<PostData>) {
        postdata.addAll(data)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


}