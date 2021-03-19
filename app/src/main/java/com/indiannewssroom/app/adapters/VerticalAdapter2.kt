package com.indiannewssroom.app.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.indiannewssroom.app.databinding.BigArticleRowLayoutBinding
import com.indiannewssroom.app.databinding.PostRowLayoutBinding
import com.indiannewssroom.app.model.PostData
import com.indiannewssroom.app.ui.DetailsActivity
import com.indiannewssroom.app.ui.fragments.a.FragmentADirections
import com.indiannewssroom.app.ui.fragments.b.FragmentBDirections
import com.indiannewssroom.app.ui.fragments.c.FragmentCDirections
import com.indiannewssroom.app.ui.fragments.d.FragmentDDirections
import com.indiannewssroom.app.ui.fragments.e.FragmentEDirections
import com.indiannewssroom.app.ui.fragments.f.FragmentFDirections
import com.indiannewssroom.app.ui.fragments.g.FragmentGDirections
import com.indiannewssroom.app.ui.fragments.h.FragmentHDirections
import com.indiannewssroom.app.ui.fragments.home.HomeFragmentDirections
import com.indiannewssroom.app.util.Constants.Companion.BUNDLE_DATA
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_A
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_B
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_C
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_D
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_E
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_F
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_G
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_H
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_HOME
import com.indiannewssroom.app.util.Constants.Companion.INTENT_DATA
import com.indiannewssroom.app.util.PostDiffUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat
import java.util.*

class VerticalAdapter2 (val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var postdata = mutableListOf<PostData>()
    private val FIRST_VIEW_TYPE = 1
    private val OTHER_VIEW_TYPE = 2

    class SmallViewHolder(private val binding: PostRowLayoutBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(postData: PostData, context: Context) {
            binding.txtPostTitle.text = postData.title!!.rendered
            binding.txtPostTitle.setOnClickListener {
                Toast.makeText(binding.root.context, adapterPosition.toString(), Toast.LENGTH_SHORT).show()
            }

            binding.clPostRow.setOnClickListener {

                if (postData!=null){
                    val intent = Intent(context, DetailsActivity::class.java)
                    val bundle = Bundle()
                    bundle.putParcelable(BUNDLE_DATA, postData)
                    intent.putExtra(INTENT_DATA, bundle)
                    context.startActivity(intent)
                }

            }
            binding.txtUpldTime.apply {
                text = postData.date?.toDate()?.formatTo("dd MMM yyyy")
            }
            binding.imgPost.apply {
                if (postData?.embedded?.wpFeaturedmedia != null){
                        load(postData.embedded.wpFeaturedmedia[0]?.sourceUrl){
                            crossfade(600)
//                            error(R.drawable.error_placeholder)
                        }

                }
            }
        }
        /**this returns the respective Directions class using fragmentName to compare */
        private fun returnAction (fragmentName: String, postData: PostData) : NavDirections {

            var action : NavDirections = HomeFragmentDirections.actionNavHomeToDetailFragment(postData)
            when(fragmentName){
                FRAGMENT_NAME_HOME ->{
                    action = HomeFragmentDirections.actionNavHomeToDetailFragment(postData)
                }
                FRAGMENT_NAME_A ->{
                    action = FragmentADirections.actionNavFragmentAToDetailFragment(postData)
                }
                FRAGMENT_NAME_B ->{
                    action = FragmentBDirections.actionNavFragmentBToDetailFragment(postData)
                }
                FRAGMENT_NAME_C ->{
                    action = FragmentCDirections.actionNavFragmentCToDetailFragment(postData)
                }
                FRAGMENT_NAME_D ->{
                    action = FragmentDDirections.actionNavFragmentDToDetailFragment(postData)
                }
                FRAGMENT_NAME_E ->{
                    action = FragmentEDirections.actionNavFragmentEToDetailFragment(postData)
                }
                FRAGMENT_NAME_F ->{
                    action = FragmentFDirections.actionNavFragmentFToDetailFragment(postData)
                }
                FRAGMENT_NAME_G ->{
                    action = FragmentGDirections.actionNavFragmentGToDetailFragment(postData)
                }
                FRAGMENT_NAME_H ->{
                    action = FragmentHDirections.actionNavFragmentHToDetailFragment(postData)
                }

            }
            return action
        }
    }


    class BigViewHolder(private val binding: BigArticleRowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(postData: PostData?,context: Context) {
            if (postData != null) {
                binding.txtposttitle.text = postData.title?.rendered
//                binding.txtCatTitle.text = postData.embedded?.wpTerm?.get(0)?.get(0)?.name
//                postData.embedded.wpTerm[0]?.let { Log.d("MYCAt", it.toString()) }
            }

            binding.imgPostImage.apply {
                if (postData?.embedded?.wpFeaturedmedia != null){
                    val c = CoroutineScope(Dispatchers.IO)
                    c.run {
                        load(postData.embedded.wpFeaturedmedia[0]?.sourceUrl){
                            crossfade(600)
                        }
                    }

                }
            }
            binding.bigRowLayout.setOnClickListener {
                if (postData!=null){
                    val intent = Intent(context, DetailsActivity::class.java)
                    val bundle = Bundle()
                    bundle.putParcelable(BUNDLE_DATA, postData)
                    intent.putExtra(INTENT_DATA, bundle)
                    context.startActivity(intent)
                }
            }
        }

        /**this returns the respective Directions class using fragmentName to compare */
        private fun returnAction (fragmentName: String, postData: PostData) : NavDirections {

            var action : NavDirections = HomeFragmentDirections.actionNavHomeToDetailFragment(postData)
                when(fragmentName){
                    FRAGMENT_NAME_HOME ->{
                        action = HomeFragmentDirections.actionNavHomeToDetailFragment(postData)
                    }
                    FRAGMENT_NAME_A ->{
                        action = FragmentADirections.actionNavFragmentAToDetailFragment(postData)
                    }
                    FRAGMENT_NAME_B ->{
                        action = FragmentBDirections.actionNavFragmentBToDetailFragment(postData)
                    }
                    FRAGMENT_NAME_C ->{
                        action = FragmentCDirections.actionNavFragmentCToDetailFragment(postData)
                    }
                    FRAGMENT_NAME_D ->{
                        action = FragmentDDirections.actionNavFragmentDToDetailFragment(postData)
                    }
                    FRAGMENT_NAME_E ->{
                        action = FragmentEDirections.actionNavFragmentEToDetailFragment(postData)
                    }
                    FRAGMENT_NAME_F ->{
                        action = FragmentFDirections.actionNavFragmentFToDetailFragment(postData)
                    }
                    FRAGMENT_NAME_G ->{
                        action = FragmentGDirections.actionNavFragmentGToDetailFragment(postData)
                    }
                    FRAGMENT_NAME_H ->{
                        action = FragmentHDirections.actionNavFragmentHToDetailFragment(postData)
                    }

            }
            return action
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
            (holder as BigViewHolder).bind(postdata[position],context)
        }else {
            (holder as SmallViewHolder).bind(postdata[position],context)
        }
    }

    override fun getItemCount(): Int {
        return postdata.size
    }

    fun clearList(){
        postdata.clear()
    }

    fun setData(data: List<PostData>) {
//        val myDiffUtil = PostDiffUtils(postdata, data)
//        val diffUtilResult = DiffUtil.calculateDiff(myDiffUtil)
//        postdata.clear()
        postdata.addAll(data)
//        diffUtilResult.dispatchUpdatesTo(this)
    }

    fun setDataOther(data: List<PostData>) {
        val myDiffUtil = PostDiffUtils(postdata, data)
        val diffUtilResult = DiffUtil.calculateDiff(myDiffUtil)
//        postdata.clear()
        postdata.addAll(data)
        diffUtilResult.dispatchUpdatesTo(this)
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

private fun String.toDate(dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss", timeZone: TimeZone = TimeZone.getTimeZone("UTC")): Date {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this)
}

private fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}
