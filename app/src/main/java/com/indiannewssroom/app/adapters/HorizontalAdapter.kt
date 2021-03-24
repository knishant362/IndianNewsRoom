package com.indiannewssroom.app.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.indiannewssroom.app.databinding.HorixontalPostRowLayoutBinding
import com.indiannewssroom.app.model.PostData
import com.indiannewssroom.app.ui.activity.DetailsActivity
import com.indiannewssroom.app.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class HorizontalAdapter(val recyclerView: RecyclerView, val context: Context) : RecyclerView.Adapter<HorizontalAdapter.MyViewHolder>() {

    private var postdata = arrayListOf<PostData>()

    class MyViewHolder(private val binding: HorixontalPostRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(postDataOne: PostData, recyclerView: RecyclerView, size: Int, context: Context) {
            binding.txtposttitle.text = postDataOne.title!!.rendered
            binding.imgPostImage.apply {
                if (postDataOne?.embedded?.wpFeaturedmedia != null){
                    val c = CoroutineScope(Dispatchers.IO)
                    c.run {
                        load(postDataOne.embedded.wpFeaturedmedia[0]?.sourceUrl){
                            crossfade(600)
                        }
                    }
                }
            }
            binding.imgPostImage.setOnClickListener {

                if (postDataOne!=null){
                    val intent = Intent(context, DetailsActivity::class.java)
                    val bundle = Bundle()
                    bundle.putParcelable(Constants.BUNDLE_DATA, postDataOne)
                    intent.putExtra(Constants.INTENT_DATA, bundle)
                    context.startActivity(intent)
                }

            }

            binding.imgForward.setOnClickListener {
                if (adapterPosition < size-1){
                    recyclerView.smoothScrollToPosition(adapterPosition+1)
                    Log.d("MyPos", adapterPosition.toString())
                }else {
                    recyclerView.smoothScrollToPosition(adapterPosition-size-2)
                }

            }
            binding.imgbackword.setOnClickListener {
                if (adapterPosition > 0){
                    recyclerView.smoothScrollToPosition(adapterPosition-1)
                }else{
                    recyclerView.smoothScrollToPosition(adapterPosition+4)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val mView = HorixontalPostRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(mView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val postResult = postdata[position]
        holder.bind(postResult, recyclerView, postdata.size, context)
    }

    override fun getItemCount(): Int {
        Log.d("MyPos", postdata.size.toString())
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