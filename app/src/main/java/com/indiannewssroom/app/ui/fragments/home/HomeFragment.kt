package com.indiannewssroom.app.ui.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.indiannewssroom.app.adapters.NestAdapter
import com.indiannewssroom.app.databinding.FragmentHomeBinding
import com.indiannewssroom.app.model.PostData
import com.indiannewssroom.app.util.Constants
import com.indiannewssroom.app.util.Constants.Companion.bollywood_cinema
import com.indiannewssroom.app.util.Constants.Companion.breaking_news
import com.indiannewssroom.app.util.Constants.Companion.dilchasp
import com.indiannewssroom.app.util.Constants.Companion.entertainment
import com.indiannewssroom.app.util.Constants.Companion.general_knowledge
import com.indiannewssroom.app.util.Constants.Companion.health
import com.indiannewssroom.app.util.Constants.Companion.latest_news
import com.indiannewssroom.app.util.Constants.Companion.lifestyle
import com.indiannewssroom.app.util.Constants.Companion.relationship
import com.indiannewssroom.app.util.Constants.Companion.social_media
import com.indiannewssroom.app.util.Constants.Companion.spiritual
import com.indiannewssroom.app.util.Constants.Companion.viral_news
import com.indiannewssroom.app.util.Status
import com.indiannewssroom.app.viewmodel.MainViewModel
import com.todkars.shimmer.ShimmerRecyclerView

class HomeFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: NestAdapter
    private lateinit var mRecyclerView: ShimmerRecyclerView
    private var myTurn = true

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
        setupObservers()
        firstApiCall()


        return binding.root
    }

    private fun setupObservers() {
        mainViewModel.getPosts().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    hideShimmerEffect()
                    it.data?.let { users -> if (users!=null){
                        Log.d("UserSize", users.size.toString())
                        renderHorizontalList(users.subList(0,14))
                        renderVerticalList(users.subList(15,users.lastIndex))
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


    private fun firstApiCall() {
        if (myTurn){
            mainViewModel.fetchPosts(slideCat(), verticalCat())
            Log.d("Cut12", "called")
            myTurn = mainViewModel.isFirst
        }
    }


    private fun verticalCat(): List<String> {
        val vertical = mutableListOf<String>()
        vertical.add(social_media.second)
        vertical.add(spiritual.second)
        vertical.add(lifestyle.second)
        vertical.add(health.second)
        vertical.add(bollywood_cinema.second)
        vertical.add(general_knowledge.second)
        return vertical
    }

    private fun slideCat(): List<String> {
        val slide = mutableListOf<String>()
        slide.add(breaking_news.second)
        slide.add(latest_news.second)
        slide.add(viral_news.second)
        slide.add(dilchasp.second)
        slide.add(entertainment.second)
        slide.add(relationship.second)
        return slide
    }


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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}