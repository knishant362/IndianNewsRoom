package com.indiannewssroom.app.ui.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.indiannewssroom.app.R
import com.indiannewssroom.app.adapters.NestAdapter
import com.indiannewssroom.app.adapters.PostAdapter
import com.indiannewssroom.app.databinding.FragmentHomeBinding
import com.indiannewssroom.app.databinding.HorixontalPostRowLayoutBinding
import com.indiannewssroom.app.model.PostData
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_HOME
import com.indiannewssroom.app.util.Constants.Companion.TYPE_HORIZONTAL
import com.indiannewssroom.app.util.Constants.Companion.bollywood_cinema
import com.indiannewssroom.app.util.Constants.Companion.breaking_news
import com.indiannewssroom.app.util.Constants.Companion.game_and_player
import com.indiannewssroom.app.util.Constants.Companion.general_knowledge
import com.indiannewssroom.app.util.Constants.Companion.viral_news
import com.indiannewssroom.app.util.Status
import com.indiannewssroom.app.viewmodel.MainViewModel
import com.todkars.shimmer.ShimmerRecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HomeFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: NestAdapter
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
        mAdapter = NestAdapter(requireContext())
        mRecyclerView = binding.myRecyclerView
        mRecyclerView.adapter = mAdapter

        val list: ArrayList<String> = ArrayList()
        list.add("Horizontal")
        list.add("Vertical")
        mAdapter.groupData(list)

        setupRecyclerView()

        readCategories()
        setupObservers()


        return binding.root
    }

    private fun readCategories() {

        mainViewModel.readHome.asLiveData().observe(viewLifecycleOwner, {value ->

            Log.d("HRR", value.category1)
            Log.d("HRR", value.category2)
            Log.d("HRR", value.category3)
            Log.d("HRR", value.category4)
            Log.d("HRR", value.category5)
            mainViewModel.fetchPosts(value.category1,value.category2,value.category3,value.category4,value.category5 )

        }
        )

//        mainViewModel.readHome.asLiveData().observe(viewLifecycleOwner) {
//
//            Log.d("HRR", it.category1)
//            Log.d("HRR", it.category2)
//            Log.d("HRR", it.category3)
//            Log.d("HRR", it.category4)
//            Log.d("HRR", it.category5)
//
//            mainViewModel.fetchPosts(it.category1,it.category2,it.category3,it.category4,it.category5 )
//        }
    }


    private fun setupObservers() {
        mainViewModel.getPosts().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    hideShimmerEffect()
                    it.data?.let { users -> if (users!=null){
                        Log.d("UserSize", users.size.toString())
                        renderHorizontalList(users.subList(0,4))
                        renderVerticalList(users.subList(5,users.lastIndex))
                    } }
                }
                Status.LOADING -> {
                    showShimmerEffect()
                }
                Status.ERROR -> {
                    //Handle Error
                    showShimmerEffect()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

    }






//    private fun firstApiCall() {
//        if (myTurn){
//            mainViewModel.apiCall(breaking_news.second, TYPE_HORIZONTAL,perPage, pageNo, FRAGMENT_NAME_HOME)
//            mainViewModel.apiCall(bollywood_cinema.second, TYPE_HORIZONTAL,perPage, pageNo, FRAGMENT_NAME_HOME)
//            mainViewModel.apiCall(game_and_player.second, TYPE_HORIZONTAL, perPage, pageNo, FRAGMENT_NAME_HOME)
//            mainViewModel.apiCall(viral_news.second, TYPE_HORIZONTAL, perPage, pageNo, FRAGMENT_NAME_HOME)
//            mainViewModel.apiCall(general_knowledge.second, TYPE_HORIZONTAL, perPage, pageNo, FRAGMENT_NAME_HOME)
//            Log.d("Cut12", "called")
//            myTurn = mainViewModel.isFirst
//        }
//    }

    private fun renderHorizontalList(users: List<PostData>) {
        mAdapter.setHorizontalData(users)
    }

    private fun renderVerticalList(users: List<PostData>) {
        mAdapter.setVerticalData(users)
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