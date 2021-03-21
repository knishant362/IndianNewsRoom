package com.indiannewssroom.app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.indiannewssroom.app.R
import com.indiannewssroom.app.model.PostData
import com.indiannewssroom.app.util.PostDiffUtils


class NestAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var group = mutableListOf<String>()
    private var postdatahorizontal = mutableListOf<PostData>()
    var postdatavertical = mutableListOf<PostData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home, parent, false)
        return HomeViewHolder(mView)
    }

    class HomeViewHolder(mView: View) : RecyclerView.ViewHolder(mView)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val groupdata = group[position]
//        holder.itemView.findViewById<TextView>(R.id.group_title).text = groupdata
//        holder.itemView.findViewById<RelativeLayout>(R.id.head_parent).setOnClickListener{
//        }
        setLists(holder.itemView.findViewById(R.id.myRecyclerView), position)
    }

    private fun setLists(recyclerView: RecyclerView?, position: Int) {
        when(position){
            0 -> {
                setHorizontalList(recyclerView)
            }
            1 -> {
                setVerticalList(recyclerView)
            }
        }
    }

    private fun setVerticalList(recyclerView: RecyclerView?) {
        val verticalList = VerticalAdapter2(context)
        verticalList.setDataOther(postdatavertical)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = verticalList
        recyclerView?.isNestedScrollingEnabled = true
    }

    private fun setHorizontalList(recyclerView: RecyclerView?) {
        val horizontalList = HorizontalAdapter(recyclerView!!)
        horizontalList.setData(postdatahorizontal)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.adapter = horizontalList
        LinearSnapHelper().attachToRecyclerView(recyclerView)
        recyclerView?.isNestedScrollingEnabled = true
    }

    override fun getItemCount(): Int {
        return group.size
    }
    fun setHorizontalData(data: List<PostData>) {
        val myDiffUtil = PostDiffUtils(postdatahorizontal, data)
        val diffUtilResult = DiffUtil.calculateDiff(myDiffUtil)
        postdatahorizontal.addAll(data)
        diffUtilResult.dispatchUpdatesTo(this)
    }
    fun setVerticalData(data: List<PostData>) {
        val myDiffUtil = PostDiffUtils(postdatavertical, data)
        val diffUtilResult = DiffUtil.calculateDiff(myDiffUtil)
        postdatavertical.addAll(data)
        diffUtilResult.dispatchUpdatesTo(this)
    }

    fun groupData(groupData: List<String>){
        group = groupData as ArrayList<String>
    }


}