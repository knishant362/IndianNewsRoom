package com.indiannewssroom.app.ui.activity.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AbsListView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.indiannewssroom.app.adapters.VerticalAdapter2
import com.indiannewssroom.app.databinding.ActivityCategoryDetailsBinding
import com.indiannewssroom.app.util.Constants
import com.indiannewssroom.app.util.Constants.Companion.INTENT_DATA
import com.indiannewssroom.app.util.Status
import com.indiannewssroom.app.viewmodel.CategoryViewModel
import com.todkars.shimmer.ShimmerRecyclerView

class CategoryDetailsActivity : AppCompatActivity() {

    private var _binding : ActivityCategoryDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var mAdapter: VerticalAdapter2
    private lateinit var mRecyclerView: ShimmerRecyclerView
    private lateinit var mLinearLayoutManager : LinearLayoutManager
    private var this_category = Constants.breaking_news.second

    private var myTurn = true
    private var isLoading = false
    private var userScroll = true
    private var postFinish = 1
    private var pageNo = 1
    private var perPage = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCategoryDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAdapter = VerticalAdapter2(this)
        mRecyclerView = binding.fragmentRecyclerCat
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        mLinearLayoutManager = LinearLayoutManager(this)

        Log.d("KKKK", "Hello")
//
        val muIntent = intent.getStringExtra(INTENT_DATA)
        if (muIntent != null) {
            this_category = muIntent
            Log.d("KKKK",muIntent )
        }
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

        categoryViewModel.getPosts().observe(this, {
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
                    binding.refreshDataCat.isRefreshing = false
                    hideShimmerEffect()
                }
                Status.LOADING -> {
                    showSnackBar("Loading")
                }
                Status.ERROR -> {
                    //Handle Error
                    hideShimmerEffect()
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

        binding.refreshDataCat.setOnRefreshListener {
            showShimmerEffect()
            pageNo = 1
            postFinish = 1
            mAdapter.clearList()
            categoryViewModel.fetchPostSingle(this_category,perPage,pageNo)
        }

    }

    private fun firstApiCall() {
        /**this apiCall will only launch once(at startup)*/
        if (myTurn){
            isLoading = true
            categoryViewModel.fetchPostSingle(this_category, perPage, pageNo)
            Log.d("MYKat", "called")
            myTurn = categoryViewModel.isFirst
        }
    }


    private fun updateRecyclerView() {
        Log.d("MyCalll", isLoading.toString())
        if (isLoading){
            pageNo++
            categoryViewModel.fetchPostSingle(this_category,perPage,pageNo)
            isLoading = false
        }
    }


    private fun showSnackBar(message: String) {
        Snackbar.make(binding.detailCat, message, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}