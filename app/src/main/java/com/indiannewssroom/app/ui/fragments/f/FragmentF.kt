package com.indiannewssroom.app.ui.fragments.f

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.indiannewssroom.app.R
import com.indiannewssroom.app.adapters.VerticalAdapter
import com.indiannewssroom.app.databinding.FragmentEBinding
import com.indiannewssroom.app.databinding.FragmentFBinding
import com.indiannewssroom.app.util.Constants
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_A
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_F
import com.indiannewssroom.app.util.Constants.Companion.dilchasp
import com.indiannewssroom.app.util.Constants.Companion.humour
import com.indiannewssroom.app.viewmodel.MainViewModel
import com.todkars.shimmer.ShimmerRecyclerView

class FragmentF : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding : FragmentFBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: VerticalAdapter
    private lateinit var mRecyclerView: ShimmerRecyclerView
    private var perPage = 10
    private var myTurn = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFBinding.inflate(inflater, container, false)
        mAdapter = VerticalAdapter(requireContext())
        mRecyclerView = binding.fragmentRecyclerF

        setupRecyclerView()

        firstApiCall()

        mainViewModel.postResponseF.observe(viewLifecycleOwner, {
            val postdata = it.data
            if (postdata!=null)
                mAdapter.setData(postdata)
            mAdapter.notifyDataSetChanged()
            hideShimmerEffect()
        })
        return binding.root
    }
    private fun firstApiCall() {
        /**this apiCall will only launch once(at startup)*/
        if (myTurn){
            mainViewModel.apiCall(dilchasp.second, perPage, FRAGMENT_NAME_F)
            Log.d("MYKat", "called")
            myTurn = mainViewModel.isFirst
        }
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