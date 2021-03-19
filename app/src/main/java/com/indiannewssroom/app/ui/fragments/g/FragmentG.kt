package com.indiannewssroom.app.ui.fragments.g

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
import com.indiannewssroom.app.databinding.FragmentFBinding
import com.indiannewssroom.app.databinding.FragmentGBinding
import com.indiannewssroom.app.util.Constants
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_F
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_G
import com.indiannewssroom.app.util.Constants.Companion.TYPE_SINGLE
import com.indiannewssroom.app.util.Constants.Companion.dilchasp
import com.indiannewssroom.app.util.Constants.Companion.game_and_player
import com.indiannewssroom.app.util.NetworkListener
import com.indiannewssroom.app.util.observeOnce
import com.indiannewssroom.app.viewmodel.MainViewModel
import com.todkars.shimmer.ShimmerRecyclerView

class FragmentG : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding : FragmentGBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: VerticalAdapter
    private lateinit var mRecyclerView: ShimmerRecyclerView
    private var perPage = 10
    private var myTurn = true
    private val this_category = game_and_player.second
    private var pageNo = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGBinding.inflate(inflater, container, false)
        mAdapter = VerticalAdapter(requireContext())
        mRecyclerView = binding.fragmentRecyclerG

        setupRecyclerView()

//        mainViewModel.networkText.observeOnce(viewLifecycleOwner, { isConnected ->
//            if (isConnected){
                firstApiCall()
//            }
//        })

        mainViewModel.postResponseG.observe(viewLifecycleOwner, {
            val postdata = it.data
            if (postdata!=null)
                mAdapter.setData(postdata)
            mAdapter.notifyDataSetChanged()
            binding.refreshDataG.isRefreshing = false
            hideShimmerEffect()
        })

        binding.refreshDataG.setOnRefreshListener {
            showShimmerEffect()
            mainViewModel.apiCall(this_category, TYPE_SINGLE, perPage, pageNo, FRAGMENT_NAME_G)
//            binding.refreshDataB.isRefreshing = true
        }

        return binding.root
    }

    private fun firstApiCall() {
        /** this apiCall will only launch once(at startup) */
        if (myTurn){
            mainViewModel.apiCall(this_category, TYPE_SINGLE, perPage, pageNo, FRAGMENT_NAME_G)
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