package com.indiannewssroom.app.ui.fragments.a

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.indiannewssroom.app.adapters.VerticalAdapter
import com.indiannewssroom.app.databinding.FragmentABinding
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_A
import com.indiannewssroom.app.util.Constants.Companion.breaking_news
import com.indiannewssroom.app.viewmodel.MainViewModel
import com.todkars.shimmer.ShimmerRecyclerView

class FragmentA : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding : FragmentABinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: VerticalAdapter
    private lateinit var mRecyclerView: ShimmerRecyclerView
    private var perPage = 10
    private var myTurn = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentABinding.inflate(inflater, container, false)
        mAdapter = VerticalAdapter(requireContext())
        mRecyclerView = binding.fragmentRecyclerA

        setupRecyclerView()

        firstApiCall()

        mainViewModel.postResponseA.observe(viewLifecycleOwner, {
            val postdata = it.data
            if (postdata!=null)
            mAdapter.setDataOther(postdata)
            mAdapter.notifyDataSetChanged()
            hideShimmerEffect()
        })
        return binding.root
    }

    private fun firstApiCall() {
        /**this apiCall will only launch once(at startup)*/
        if (myTurn){
            mainViewModel.apiCall(breaking_news.second, perPage,FRAGMENT_NAME_A)
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