package com.indiannewssroom.app.ui.fragments.h

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.indiannewssroom.app.R
import com.indiannewssroom.app.adapters.VerticalAdapter
import com.indiannewssroom.app.databinding.FragmentGBinding
import com.indiannewssroom.app.databinding.FragmentHBinding
import com.indiannewssroom.app.util.Constants
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_H
import com.indiannewssroom.app.util.Constants.Companion.TYPE_SINGLE
import com.indiannewssroom.app.util.Constants.Companion.digital_duniya
import com.indiannewssroom.app.util.Constants.Companion.science_and_technology
import com.indiannewssroom.app.util.NetworkListener
import com.indiannewssroom.app.util.observeOnce
import com.indiannewssroom.app.viewmodel.MainViewModel
import com.todkars.shimmer.ShimmerRecyclerView

class FragmentH : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding : FragmentHBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: VerticalAdapter
    private lateinit var mRecyclerView: ShimmerRecyclerView
    private var perPage = 10
    private var myTurn = true
    private var this_category = science_and_technology.second
    private var pageNo = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHBinding.inflate(inflater, container, false)
        mAdapter = VerticalAdapter(requireContext())
        mRecyclerView = binding.fragmentRecyclerH

        setupRecyclerView()

//        mainViewModel.networkText.observeOnce(viewLifecycleOwner, { isConnected ->
//            if (isConnected){
                firstApiCall()
//            }
//        })

        binding.cgFragmentH.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            val selectedCategory = chip.text.toString()
            val myCat = myTranslator(selectedCategory)
            Log.d("Cut3", myCat)
            this_category = myCat
            showShimmerEffect()
            mainViewModel.apiCall(this_category, TYPE_SINGLE, perPage, pageNo, FRAGMENT_NAME_H)
        }

        mainViewModel.postResponseH.observe(viewLifecycleOwner, {
            val postdata = it.data
            if (postdata!=null){
                mAdapter.setDataOther(postdata)
                mAdapter.notifyDataSetChanged()
                binding.refreshDataH.isRefreshing = false
                hideShimmerEffect()
            }
        })

        binding.refreshDataH.setOnRefreshListener {
            showShimmerEffect()
            mainViewModel.apiCall(this_category, TYPE_SINGLE, perPage, pageNo, FRAGMENT_NAME_H)
//            binding.refreshDataB.isRefreshing = true
        }

        return binding.root

    }

    private fun firstApiCall() {
        /**this apiCall will only launch once(at startup)*/
        if (myTurn){
            mainViewModel.apiCall(this_category, TYPE_SINGLE, perPage, pageNo, FRAGMENT_NAME_H)
            Log.d("MYKat", "called")
            myTurn = mainViewModel.isFirst
        }
    }

    private fun myTranslator(selectedCategory: String): String {
        var trans = science_and_technology.second

        when(selectedCategory){
            "All" -> {
                trans =  science_and_technology.second
            }
            digital_duniya.first -> {
                trans = digital_duniya.second
            }
        }
        return trans
    }

    private fun setupRecyclerView() {
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun showShimmerEffect() {
        mRecyclerView.showShimmer()
    }

    private fun hideShimmerEffect() {
        mRecyclerView.hideShimmer()
    }

}