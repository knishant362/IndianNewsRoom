package com.indiannewssroom.app.ui.fragments.c

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
import com.indiannewssroom.app.adapters.VerticalAdapter
import com.indiannewssroom.app.databinding.FragmentCBinding
import com.indiannewssroom.app.util.Constants
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_C
import com.indiannewssroom.app.util.Constants.Companion.TYPE_SINGLE
import com.indiannewssroom.app.util.Constants.Companion.astrology
import com.indiannewssroom.app.util.Constants.Companion.chanakya_niti
import com.indiannewssroom.app.util.Constants.Companion.lent_and_festivals
import com.indiannewssroom.app.util.Constants.Companion.religion
import com.indiannewssroom.app.util.Constants.Companion.spiritual
import com.indiannewssroom.app.util.Constants.Companion.vastu_shastra
import com.indiannewssroom.app.util.NetworkListener
import com.indiannewssroom.app.util.observeOnce
import com.indiannewssroom.app.viewmodel.MainViewModel
import com.todkars.shimmer.ShimmerRecyclerView

class FragmentC : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding : FragmentCBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: VerticalAdapter
    private lateinit var mRecyclerView: ShimmerRecyclerView
    private var perPage = 10
    private var this_category = religion.second
    private var myTurn = true
    private var isLoading = true
    private var userScroll = true
    private var pageNo = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCBinding.inflate(inflater, container, false)
        mAdapter = VerticalAdapter(requireContext())
        mRecyclerView = binding.fragmentRecyclerC

        setupRecyclerView()

//        mainViewModel.networkText.observeOnce(viewLifecycleOwner, { isConnected ->
//            if (isConnected){
                firstApiCall()
//            }
//        })

        binding.cgFragmentC.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            val selectedCategory = chip.text.toString()
            val myCat = myTranslator(selectedCategory)
            Log.d("Cut3", myCat)
            showShimmerEffect()
            this_category = myCat
            mainViewModel.apiCall(this_category, TYPE_SINGLE, perPage,pageNo, FRAGMENT_NAME_C)
        }
        mainViewModel.postResponseC.observe(viewLifecycleOwner, {
            val postdata = it.data
            if (postdata!=null){
                mAdapter.setDataOther(postdata)
                mAdapter.notifyDataSetChanged()
                binding.refreshDataC.isRefreshing = false
                hideShimmerEffect()
            }
        })

        binding.refreshDataC.setOnRefreshListener {
            showShimmerEffect()
            mainViewModel.apiCall(this_category, TYPE_SINGLE, perPage,pageNo, FRAGMENT_NAME_C)
//            binding.refreshDataB.isRefreshing = true
        }

        return binding.root
    }

    private fun firstApiCall() {
        /**this apiCall will only launch once(at startup)*/
        if (myTurn){
            mainViewModel.apiCall(this_category, TYPE_SINGLE, perPage,pageNo, FRAGMENT_NAME_C)
            Log.d("Cut12", "called")
            myTurn = mainViewModel.isFirst
        }
    }

    /**It compares the input category from chip and compares and give its category from its Pair*/
    private fun myTranslator(selectedCategory: String): String {
        var trans = religion.second

        when(selectedCategory){
            "All" -> {
                trans =  religion.second
            }
            spiritual.first -> {
                trans = spiritual.second
            }
            astrology.first -> {
                trans = astrology.second
            }
            vastu_shastra.first -> {
                trans = vastu_shastra.second
            }
            lent_and_festivals.first -> {
                trans = lent_and_festivals.second
            }
            chanakya_niti.first -> {
                trans = chanakya_niti.second
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