package com.indiannewssroom.app.ui.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.indiannewssroom.app.adapters.PostAdapter
import com.indiannewssroom.app.databinding.FragmentHomeBinding
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_HOME
import com.indiannewssroom.app.util.Constants.Companion.TYPE_HORIZONTAL
import com.indiannewssroom.app.util.Constants.Companion.bollywood_cinema
import com.indiannewssroom.app.util.Constants.Companion.breaking_news
import com.indiannewssroom.app.util.Constants.Companion.game_and_player
import com.indiannewssroom.app.util.Constants.Companion.general_knowledge
import com.indiannewssroom.app.util.Constants.Companion.viral_news
import com.indiannewssroom.app.viewmodel.MainViewModel
import com.todkars.shimmer.ShimmerRecyclerView

class HomeFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: PostAdapter
    private lateinit var mRecyclerView: ShimmerRecyclerView
    private var isLoading = true
    private var userScroll = true
    private var pageNo = 1
    private var myTurn = true
    private var perPage = 5

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        mAdapter = PostAdapter(requireContext())
        mRecyclerView = binding.myRecyclerView
        mRecyclerView.adapter = mAdapter

        setupRecyclerView()

        mainViewModel.postResponseHorizontal.observe(viewLifecycleOwner, {
            val postdata = it.data
            if (postdata!=null)
                mAdapter.setDataOther(mainViewModel.horizontalList)
            hideShimmerEffect()
        })

        firstApiCall()

        return binding.root
    }

    private fun firstApiCall() {
        if (myTurn){
            mainViewModel.apiCall(breaking_news.second, TYPE_HORIZONTAL,perPage, pageNo, FRAGMENT_NAME_HOME)
            mainViewModel.apiCall(bollywood_cinema.second, TYPE_HORIZONTAL,perPage, pageNo, FRAGMENT_NAME_HOME)
            mainViewModel.apiCall(game_and_player.second, TYPE_HORIZONTAL, perPage, pageNo, FRAGMENT_NAME_HOME)
            mainViewModel.apiCall(viral_news.second, TYPE_HORIZONTAL, perPage, pageNo, FRAGMENT_NAME_HOME)
            mainViewModel.apiCall(general_knowledge.second, TYPE_HORIZONTAL, perPage, pageNo, FRAGMENT_NAME_HOME)
            Log.d("Cut12", "called")
            myTurn = mainViewModel.isFirst
        }
    }

    private fun setupRecyclerView() {
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