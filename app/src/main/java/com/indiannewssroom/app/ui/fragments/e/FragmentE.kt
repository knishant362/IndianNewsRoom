package com.indiannewssroom.app.ui.fragments.e

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
import com.indiannewssroom.app.databinding.FragmentDBinding
import com.indiannewssroom.app.databinding.FragmentEBinding
import com.indiannewssroom.app.util.Constants
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_E
import com.indiannewssroom.app.util.Constants.Companion.TYPE_SINGLE
import com.indiannewssroom.app.util.Constants.Companion.bollywood_cinema
import com.indiannewssroom.app.util.Constants.Companion.entertainment
import com.indiannewssroom.app.util.Constants.Companion.lifestyle
import com.indiannewssroom.app.util.Constants.Companion.religion
import com.indiannewssroom.app.util.Constants.Companion.tv_and_serial
import com.indiannewssroom.app.util.NetworkListener
import com.indiannewssroom.app.util.observeOnce
import com.indiannewssroom.app.viewmodel.MainViewModel
import com.todkars.shimmer.ShimmerRecyclerView

class FragmentE : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding : FragmentEBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: VerticalAdapter
    private lateinit var mRecyclerView: ShimmerRecyclerView
    private var perPage = 10
    private var myTurn = true
    private var this_category = entertainment.second
    private var pageNo = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEBinding.inflate(inflater, container, false)
        mAdapter = VerticalAdapter(requireContext())
        mRecyclerView = binding.fragmentRecyclerE

        setupRecyclerView()

//        mainViewModel.networkText.observeOnce(viewLifecycleOwner, { isConnected ->
//            if (isConnected){
                firstApiCall()
//            }
//        })

        binding.cgFragmentE.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            val selectedCategory = chip.text.toString()
            val myCat = myTranslator(selectedCategory)
            Log.d("Cut3", myCat)
            this_category = myCat
            showShimmerEffect()
            mainViewModel.apiCall(this_category, TYPE_SINGLE, perPage, pageNo, FRAGMENT_NAME_E)
        }

        mainViewModel.postResponseE.observe(viewLifecycleOwner, {
            val postdata = it.data
            if (postdata!=null){
                mAdapter.setDataOther(postdata)
                mAdapter.notifyDataSetChanged()
                binding.refreshDataE.isRefreshing = false
                hideShimmerEffect()
            }
        })

        binding.refreshDataE.setOnRefreshListener {
            showShimmerEffect()
            mainViewModel.apiCall(this_category, TYPE_SINGLE, perPage, pageNo, FRAGMENT_NAME_E)
//            binding.refreshDataB.isRefreshing = true
        }

        return binding.root
    }

    private fun firstApiCall() {
        /**this apiCall will only launch once(at startup)*/
        if (myTurn){
            mainViewModel.apiCall(this_category, TYPE_SINGLE, perPage, pageNo, FRAGMENT_NAME_E)
            Log.d("MYKat", "called")
            myTurn = mainViewModel.isFirst
        }
    }

    private fun myTranslator(selectedCategory: String): String {
        var trans = religion.second

        when(selectedCategory){
            "All" -> {
                trans =  entertainment.second
            }
            tv_and_serial.first -> {
                trans = tv_and_serial.second
            }
            bollywood_cinema.first -> {
                trans = bollywood_cinema.second
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