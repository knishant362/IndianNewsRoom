
package com.indiannewssroom.app.ui.fragments.b

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.indiannewssroom.app.adapters.VerticalAdapter2
import com.indiannewssroom.app.databinding.FragmentBBinding
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_B
import com.indiannewssroom.app.util.Constants.Companion.TYPE_SINGLE
import com.indiannewssroom.app.util.Constants.Companion.latest_news
import com.indiannewssroom.app.util.NetworkListener
import com.indiannewssroom.app.util.observeOnce
import com.indiannewssroom.app.viewmodel.MainViewModel
import com.todkars.shimmer.ShimmerRecyclerView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

class FragmentB : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding : FragmentBBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: VerticalAdapter2
    private lateinit var mRecyclerView: ShimmerRecyclerView
    private var perPage = 20
    private var myTurn = true
    private val this_category = latest_news.second
    private var isLoading = true
    private var userScroll = true
    private var pageNo = 1
    private lateinit var mLinearLayoutManager : LinearLayoutManager
    private var postFinish = 1

    @ExperimentalCoroutinesApi
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentBBinding.inflate(inflater, container, false)
        mAdapter = VerticalAdapter2(requireContext())
        mRecyclerView = binding.fragmentRecyclerB
        mLinearLayoutManager = LinearLayoutManager(requireContext())


//        mainViewModel.networkText.observeOnce(viewLifecycleOwner, { isConnected ->
//            if (isConnected){
                firstApiCall()
//            }
//        })

        setupRecyclerView()

        mRecyclerView.apply {
//            linearLayoutManager.isSmoothScrollbarEnabled = true
//            adapter = mAdapter
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

                    Log.d("testRecyc", "Loding: $isLoading")
                    if (userScroll && (visibleItemCount + pastVisibleItems) == totalItem && dy > 0) {
                        userScroll = false

                        Log.d("postFinish", "$postFinish")
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

        mainViewModel.postResponseB.observe(viewLifecycleOwner, {
            val postdata = it.data
            if (postdata!=null)
                mAdapter.setDataOther(postdata)
            if (pageNo==1){
                Log.d("Thiss","htiss")
            }else{
                val pos = (mAdapter.itemCount)
                val myff = 9+(((pageNo-1)*20)-12)
                val mf = pos-23
                val diff = mf-myff
                postFinish = diff
                if (diff < 0){
                    Log.d("ThisPosFinal", "${mf-myff}  $pageNo  $myff  $pos")
                }
                Log.d("ThisPosFinal", "${mf-myff}  $pageNo  $myff  $pos")
                mRecyclerView.scrollToPosition(myff);
            }
            binding.refreshDataB.isRefreshing = false
            hideShimmerEffect()
        })

        binding.refreshDataB.setOnRefreshListener {
            showShimmerEffect()
            pageNo=1
            mAdapter.clearList()
            mainViewModel.apiCall(this_category,TYPE_SINGLE, perPage,pageNo, FRAGMENT_NAME_B)
        }
        return binding.root
    }

    private fun updateRecyclerView() {
//        showShimmerEffect()

        pageNo++
        mainViewModel.apiCall(this_category,TYPE_SINGLE, perPage,pageNo, FRAGMENT_NAME_B)
    }

    private fun showSnackBar(message: String) {
        view?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun firstApiCall() {
        /**this apiCall will only launch once(at startup)*/
        if (myTurn){
            mainViewModel.apiCall(this_category,TYPE_SINGLE, perPage, pageNo, FRAGMENT_NAME_B)
            myTurn = mainViewModel.isFirst
        }
    }

    private fun setupRecyclerView() {
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = mLinearLayoutManager
    }

    private fun showShimmerEffect() {
        mRecyclerView.showShimmer()
    }

    private fun hideShimmerEffect() {
        mRecyclerView.hideShimmer()
    }



}