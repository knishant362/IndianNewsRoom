package com.indiannewssroom.app.ui.fragments.f

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.indiannewssroom.app.adapters.VerticalAdapter2
import com.indiannewssroom.app.databinding.FragmentFBinding
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_F
import com.indiannewssroom.app.util.Constants.Companion.TYPE_SINGLE
import com.indiannewssroom.app.util.Constants.Companion.dilchasp
import com.indiannewssroom.app.util.Status
import com.indiannewssroom.app.viewmodel.MainViewModel
import com.todkars.shimmer.ShimmerRecyclerView

class FragmentF : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding : FragmentFBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: VerticalAdapter2
    private lateinit var mRecyclerView: ShimmerRecyclerView
    private lateinit var mLinearLayoutManager : LinearLayoutManager
    private val this_category = dilchasp.second

    private var myTurn = true
    private var isLoading = false
    private var userScroll = true
    private var postFinish = 1
    private var pageNo = 1
    private var perPage = 20

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFBinding.inflate(inflater, container, false)
        mAdapter = VerticalAdapter2(requireContext())
        mRecyclerView = binding.fragmentRecyclerF
        mLinearLayoutManager = LinearLayoutManager(requireContext())

        setupRecyclerView()
        firstApiCall()

        mRecyclerView.apply {
            setHasFixedSize(true)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                        userScroll = true
                    }
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val totalItem = mLinearLayoutManager.itemCount
                    val visibleItemCount = mLinearLayoutManager.childCount
                    val pastVisibleItems = mLinearLayoutManager.findFirstVisibleItemPosition()
                    val lastItem = mLinearLayoutManager.findLastCompletelyVisibleItemPosition()

                    if (userScroll && (visibleItemCount + pastVisibleItems) == totalItem && dy > 0) {
                        userScroll = false
                        if (postFinish>=0){
                            showSnackBar("Loading")
                            updateRecyclerView()
                        }else{
                            showSnackBar("That's all for Now!")
                        }

                    }
                }
            })
        }

        mainViewModel.getPostFF().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {

                    val postdata = it.data
                    if (postdata!=null)
                        mAdapter.setDataOther(postdata)
                    if (pageNo==1){
                        Log.d("Thiss","htiss")
                    }else{

                        /** this is just to calculate the last ending position of item in RV ,
                         *  to make it shift to this position again after new data is added*/

                        val pos = (mAdapter.itemCount)
                        val myff = 9+(((pageNo-1)*20)-12)
                        val mf = pos-23
                        val diff = mf-myff
                        postFinish = diff
                        if (diff < 0){
                            Log.d("ThisPosFinal", "${mf-myff}  $pageNo  $myff  $pos")
                        }
                        Log.d("ThisPosFinal", "${mf-myff}  $pageNo  $myff  $pos")
                        mRecyclerView.scrollToPosition(myff)
                        isLoading = true
                    }
                    binding.refreshDataF.isRefreshing = false
                    hideShimmerEffect()
                }
                Status.LOADING -> {
                    showShimmerEffect()
                }
                Status.ERROR -> {
                    //Handle Error
                    hideShimmerEffect()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

        binding.refreshDataF.setOnRefreshListener {
            showShimmerEffect()
            pageNo=1
            postFinish = 1
            mAdapter.clearList()
            mainViewModel.fetchPostSingle(this_category, TYPE_SINGLE, perPage, pageNo, FRAGMENT_NAME_F)
        }

        return binding.root
    }
    private fun firstApiCall() {
        /**this apiCall will only launch once(at startup)*/
        if (myTurn){
            isLoading = true
            mainViewModel.fetchPostSingle(this_category, TYPE_SINGLE, perPage, pageNo, FRAGMENT_NAME_F)
            Log.d("MYKat", "called")
            myTurn = mainViewModel.isFirst
        }
    }

    private fun updateRecyclerView() {
        Log.d("MyCalll", isLoading.toString())
        if (isLoading){
            pageNo++
            mainViewModel.fetchPostSingle(this_category,TYPE_SINGLE, perPage,pageNo, FRAGMENT_NAME_F)
            isLoading = false
        }
    }

    private fun showSnackBar(message: String) {
        view?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun setupRecyclerView() {
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = mLinearLayoutManager
        showShimmerEffect()
    }

    private fun showShimmerEffect() {
        mRecyclerView.showShimmer()
    }

    private fun hideShimmerEffect() {
        mRecyclerView.hideShimmer()
    }


}