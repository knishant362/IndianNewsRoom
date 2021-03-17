package com.indiannewssroom.app.ui.fragments.a

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MediatorLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.indiannewssroom.app.R
import com.indiannewssroom.app.adapters.VerticalAdapter
import com.indiannewssroom.app.data.NetworkResults
import com.indiannewssroom.app.databinding.FragmentABinding
import com.indiannewssroom.app.model.PostData
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_A
import com.indiannewssroom.app.util.Constants.Companion.tv_and_serial
import com.indiannewssroom.app.viewmodel.MainViewModel

class FragmentA : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding : FragmentABinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: VerticalAdapter
    private lateinit var mRecyclerView: RecyclerView
    private var perPage = 10

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentABinding.inflate(inflater, container, false)
        mAdapter = VerticalAdapter()
        mRecyclerView = binding.fragmentRecyclerA
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        /** here tv_and_serial is a Pair<>() */

        mainViewModel.apiCall(tv_and_serial.second, perPage,FRAGMENT_NAME_A)
        mainViewModel.postResponseA.observe(viewLifecycleOwner, {
            val postdata = it.data
            if (postdata!=null)
            mAdapter.setData(postdata)
            mAdapter.notifyDataSetChanged()
        })
        return binding.root
    }

}