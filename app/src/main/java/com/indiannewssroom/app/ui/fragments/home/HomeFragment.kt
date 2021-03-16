package com.indiannewssroom.app.ui.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.indiannewssroom.app.R
import com.indiannewssroom.app.adapters.HomeAdapter
import com.indiannewssroom.app.viewmodel.MainViewModel

class HomeFragment : Fragment() {

//    private var _binding : FragmentHomeBinding? = null
//    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var homeRecyclerView : RecyclerView
    private lateinit var homeAdapter: HomeAdapter
    private var isLoading = true
    private var userScroll = true
    private var pageNo = 1


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

//        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val mView = inflater.inflate(R.layout.fragment_home, container, false)
        homeRecyclerView = mView.findViewById(R.id.myRecyclerView)
        homeAdapter = HomeAdapter(requireContext())
        homeRecyclerView.adapter = homeAdapter
        homeRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val list: ArrayList<String> = ArrayList()
        list.add("Horizontal")
        list.add("Vertical")
        homeAdapter.groupData(list)

        setupHorizontalObserver()
        setupVerticalObserver()

        return mView
    }

    private fun setupVerticalObserver() {
        mainViewModel.postResponseVertical.observe(
                viewLifecycleOwner,
                {
                    val postData = it.data
                    if (postData!= null){
                        Log.d("MYMY", it.data.size.toString())
                        homeAdapter.setVerticalData(postData)
                        homeAdapter.notifyDataSetChanged()
                    }
                }
        )
    }

    private fun setupHorizontalObserver() {
        mainViewModel.postResponseHorizontal.observeOnce(viewLifecycleOwner, {
            val postdata = it.data
            if (postdata!= null){
                Log.d("MYMYOB", it.data.size.toString())
                homeAdapter.setHorizontalData(postdata)
                homeAdapter.notifyDataSetChanged()
            }
        })
    }

    fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
        observe(lifecycleOwner, object : Observer<T> {
            override fun onChanged(t: T?) {
                removeObserver(this)
                observer.onChanged(t)
            }
        })
    }

}