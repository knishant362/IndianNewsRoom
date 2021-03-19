package com.indiannewssroom.app.ui.fragments.d

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
import com.indiannewssroom.app.databinding.FragmentCBinding
import com.indiannewssroom.app.databinding.FragmentDBinding
import com.indiannewssroom.app.util.Constants
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_D
import com.indiannewssroom.app.util.Constants.Companion.TYPE_SINGLE
import com.indiannewssroom.app.util.Constants.Companion.fashion_and_style
import com.indiannewssroom.app.util.Constants.Companion.food_and_recipes
import com.indiannewssroom.app.util.Constants.Companion.ghrelu_nuskhe
import com.indiannewssroom.app.util.Constants.Companion.health
import com.indiannewssroom.app.util.Constants.Companion.lifestyle
import com.indiannewssroom.app.util.Constants.Companion.relationship
import com.indiannewssroom.app.util.Constants.Companion.religion
import com.indiannewssroom.app.util.Constants.Companion.tour_and_travel
import com.indiannewssroom.app.util.NetworkListener
import com.indiannewssroom.app.util.observeOnce
import com.indiannewssroom.app.viewmodel.MainViewModel
import com.todkars.shimmer.ShimmerRecyclerView

class FragmentD : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding : FragmentDBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: VerticalAdapter
    private lateinit var mRecyclerView: ShimmerRecyclerView
    private var perPage = 10
    private var this_category = lifestyle.second
    private var myTurn = true
    private var pageNo = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDBinding.inflate(inflater, container, false)
        mAdapter = VerticalAdapter(requireContext())
        mRecyclerView = binding.fragmentRecyclerD

//        mainViewModel.networkText.observeOnce(viewLifecycleOwner, { isConnected ->
//            if (isConnected){
                firstApiCall()
//            }
//        })

        firstApiCall()

        binding.cgFragmentD.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            val selectedCategory = chip.text.toString()
            val myCat = myTranslator(selectedCategory)
            Log.d("Cut3", myCat)
            showShimmerEffect()
            this_category = myCat
            mainViewModel.apiCall(this_category, TYPE_SINGLE, perPage, pageNo, FRAGMENT_NAME_D)
        }

        mainViewModel.postResponseD.observe(viewLifecycleOwner, {
            val postdata = it.data
            if (postdata!=null){
                mAdapter.setDataOther(postdata)
                mAdapter.notifyDataSetChanged()
                binding.refreshDataD.isRefreshing = false
                hideShimmerEffect()
            }
        })

        binding.refreshDataD.setOnRefreshListener {
            showShimmerEffect()
            mainViewModel.apiCall(this_category, TYPE_SINGLE, perPage, pageNo, FRAGMENT_NAME_D)
//            binding.refreshDataB.isRefreshing = true
        }

        return binding.root
    }

    private fun firstApiCall() {
        /**this apiCall will only launch once(at startup)*/
        if (myTurn){
            mainViewModel.apiCall(this_category, TYPE_SINGLE, perPage, pageNo, FRAGMENT_NAME_D)
            Log.d("MYKat", "called")
            myTurn = mainViewModel.isFirst
        }
    }

    private fun myTranslator(selectedCategory: String): String {
        var trans = religion.second

        when(selectedCategory){
            "All" -> {
                trans =  lifestyle.second
            }
            health.first -> {
                trans = health.second
            }
            relationship.first -> {
                trans = relationship.second
            }
            fashion_and_style.first -> {
                trans = fashion_and_style.second
            }
            tour_and_travel.first -> {
                trans = tour_and_travel.second
            }
            food_and_recipes.first -> {
                trans = food_and_recipes.second
            }
            ghrelu_nuskhe.first -> {
                trans = ghrelu_nuskhe.second
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