package com.indiannewssroom.app.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.indiannewssroom.app.databinding.BigArticleRowLayoutBinding
import com.indiannewssroom.app.databinding.HorizontalPostRowLayoutBinding
import com.indiannewssroom.app.databinding.PostRowLayoutBinding
import com.indiannewssroom.app.model.PostData
import com.indiannewssroom.app.ui.fragments.home.HomeFragmentDirections
import com.indiannewssroom.app.util.PostDiffUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class VerticalAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var postdata = arrayListOf<PostData>()
    private val FIRST_VIEW_TYPE = 1
    private val OTHER_VIEW_TYPE = 2

    class SmallViewHolder(private val binding: PostRowLayoutBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(postData: PostData) {
            binding.txtPostTitle.text = postData.title.rendered
            binding.txtPostTitle.setOnClickListener {
                Toast.makeText(binding.root.context, adapterPosition.toString(), Toast.LENGTH_SHORT).show()
            }

            binding.clPostRow.setOnClickListener {
                val action = HomeFragmentDirections.actionNavHomeToDetailFragment(postData)
                Navigation.findNavController(it).navigate(action)
            }

            binding.imgPost.apply {
                if (postData?.embedded?.wpFeaturedmedia != null){
                        load(postData.embedded.wpFeaturedmedia[0].sourceUrl){
                            crossfade(600)
                    }

                }
            }
        }
    }


    class BigViewHolder(private val binding: BigArticleRowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(postData: PostData?) {
            if (postData != null) {
                binding.txtposttitle.text = postData.title.rendered
                binding.txtCatTitle.text = postData.embedded.wpTerm[0][0].name
                Log.d("MYCAt",postData.embedded.wpTerm[0].toString())
            }

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
            binding.bigRowLayout .setOnClickListener {
                if (postData!=null){
                    val action = HomeFragmentDirections.actionNavHomeToDetailFragment(postData)
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val smallView : PostRowLayoutBinding
        val bigView : BigArticleRowLayoutBinding

        return if (viewType == FIRST_VIEW_TYPE){
            bigView = BigArticleRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            BigViewHolder(bigView)
        }else {
            smallView = PostRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            SmallViewHolder(smallView)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (getItemViewType(position) == FIRST_VIEW_TYPE){
            (holder as BigViewHolder).bind(postdata[position])
        }else {
            (holder as SmallViewHolder).bind(postdata[position])
        }
    }

    override fun getItemCount(): Int {
        return postdata.size
    }

    fun setData(data: List<PostData>) {
//        val myDiffUtil = PostDiffUtils(postdata, data)
//        val diffUtilResult = DiffUtil.calculateDiff(myDiffUtil)
        postdata.addAll(data)
//        diffUtilResult.dispatchUpdatesTo(this)
    }

    fun setDataOther(data: List<PostData>) {
//        val myDiffUtil = PostDiffUtils(postdata, data)
//        val diffUtilResult = DiffUtil.calculateDiff(myDiffUtil)
        postdata.clear()
        postdata.addAll(data)
//        diffUtilResult.dispatchUpdatesTo(this)
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return if(position%5 == 0) {
            FIRST_VIEW_TYPE
        } else {
            OTHER_VIEW_TYPE
        }
    }

}