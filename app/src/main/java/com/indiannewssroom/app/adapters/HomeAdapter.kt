package com.indiannewssroom.app.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.indiannewssroom.app.R
import com.indiannewssroom.app.model.PostData

class HomeAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var group = arrayListOf<String>()
    private var postdatahorizontal = arrayListOf<PostData>()
    var postdatavertical = arrayListOf<PostData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.home_row_layout, parent, false)
        return HomeViewHolder(mView)
    }

    class HomeViewHolder(mView: View) : RecyclerView.ViewHolder(mView)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val groupdata = group[position]
//        holder.itemView.findViewById<TextView>(R.id.group_title).text = groupdata
//        holder.itemView.findViewById<RelativeLayout>(R.id.head_parent).setOnClickListener{
//        }
        setLists(holder.itemView.findViewById(R.id.group_recycler_view), position)
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
        val verticalList = VerticalAdapter(context)
        verticalList.setData(postdatavertical)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = verticalList
        recyclerView?.isNestedScrollingEnabled = true
    }

    private fun setHorizontalList(recyclerView: RecyclerView?) {
        val horizontalList = HorizontalAdapter()
        horizontalList.setData(postdatahorizontal)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.adapter = horizontalList
        recyclerView?.isNestedScrollingEnabled = true
    }

    override fun getItemCount(): Int {
        return group.size
    }
    fun setHorizontalData(data: List<PostData>) {
        postdatahorizontal.clear()
        postdatahorizontal.addAll(data)
    }
    fun setVerticalData(data: List<PostData>) {
        postdatavertical.clear()
        Log.d("MyData1", postdatavertical.size.toString())
        Log.d("MyData2", data.size.toString())
        postdatavertical.addAll(data)
    }

    fun groupData(groupData: List<String>){
        group = groupData as ArrayList<String>
    }


}