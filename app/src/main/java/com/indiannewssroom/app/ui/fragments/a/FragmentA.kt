package com.indiannewssroom.app.ui.fragments.a

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.indiannewssroom.app.adapters.VerticalAdapter2
import com.indiannewssroom.app.databinding.FragmentABinding
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_A
import com.indiannewssroom.app.util.Constants.Companion.TYPE_SINGLE
import com.indiannewssroom.app.util.Constants.Companion.breaking_news
import com.indiannewssroom.app.viewmodel.MainViewModel
import com.todkars.shimmer.ShimmerRecyclerView

class FragmentA : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding : FragmentABinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: VerticalAdapter2
    private lateinit var mRecyclerView: ShimmerRecyclerView
    private lateinit var mLinearLayoutManager : LinearLayoutManager
    private val this_category = breaking_news.second

    private var myTurn = true
    private var isLoading = false
    private var userScroll = true
    private var postFinish = 1
    private var pageNo = 1
    private var perPage = 20

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentABinding.inflate(inflater, container, false)
        mAdapter = VerticalAdapter2(requireContext())
        mRecyclerView = binding.fragmentRecyclerA
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

        mainViewModel.postResponseA.observe(viewLifecycleOwner, {
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
            binding.refreshDataA.isRefreshing = false
            hideShimmerEffect()
        })

        binding.refreshDataA.setOnRefreshListener {
            showShimmerEffect()
            pageNo = 1
            postFinish = 1
            mAdapter.clearList()
            mainViewModel.apiCall(this_category,TYPE_SINGLE, perPage,pageNo, FRAGMENT_NAME_A)
        }

        return binding.root
    }


    private fun firstApiCall() {
        /**this apiCall will only launch once(at startup)*/
        if (myTurn){
            isLoading = true
            mainViewModel.apiCall(this_category, TYPE_SINGLE, perPage, pageNo, FRAGMENT_NAME_A)
            Log.d("MYKat", "called")
            myTurn = mainViewModel.isFirst
        }
    }


    private fun updateRecyclerView() {
        Log.d("MyCalll", isLoading.toString())
        if (isLoading){
            pageNo++
            mainViewModel.apiCall(this_category,TYPE_SINGLE, perPage,pageNo, FRAGMENT_NAME_A)
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

