package com.indiannewssroom.app.ui.fragments.e

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.indiannewssroom.app.R
import com.indiannewssroom.app.adapters.VerticalAdapter
import com.indiannewssroom.app.databinding.FragmentDBinding
import com.indiannewssroom.app.databinding.FragmentEBinding
import com.indiannewssroom.app.util.Constants
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_E
import com.indiannewssroom.app.util.Constants.Companion.lifestyle
import com.indiannewssroom.app.util.Constants.Companion.religion
import com.indiannewssroom.app.viewmodel.MainViewModel

class FragmentE : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding : FragmentEBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: VerticalAdapter
    private lateinit var mRecyclerView: RecyclerView
    private var perPage = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEBinding.inflate(inflater, container, false)
        mAdapter = VerticalAdapter()
        mRecyclerView = binding.fragmentRecyclerE
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        /** here tv_and_serial is a Pair<>() */

        mainViewModel.apiCall(lifestyle.second, perPage, FRAGMENT_NAME_E)
        mainViewModel.postResponseE.observe(viewLifecycleOwner, {
            val postdata = it.data
            if (postdata!=null)
                mAdapter.setData(postdata)
            mAdapter.notifyDataSetChanged()
        })
        return binding.root

    }

}