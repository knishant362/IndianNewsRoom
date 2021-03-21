package com.indiannewssroom.app.ui.fragments.c

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
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import com.indiannewssroom.app.adapters.VerticalAdapter2
import com.indiannewssroom.app.databinding.FragmentCBinding
import com.indiannewssroom.app.util.Constants
import com.indiannewssroom.app.util.Constants.Companion.CG_INVISIBLE
import com.indiannewssroom.app.util.Constants.Companion.CG_VISIBLE
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_C
import com.indiannewssroom.app.util.Constants.Companion.TYPE_SINGLE
import com.indiannewssroom.app.util.Constants.Companion.astrology
import com.indiannewssroom.app.util.Constants.Companion.chanakya_niti
import com.indiannewssroom.app.util.Constants.Companion.lent_and_festivals
import com.indiannewssroom.app.util.Constants.Companion.religion
import com.indiannewssroom.app.util.Constants.Companion.spiritual
import com.indiannewssroom.app.util.Constants.Companion.vastu_shastra
import com.indiannewssroom.app.util.Status
import com.indiannewssroom.app.viewmodel.MainViewModel
import com.todkars.shimmer.ShimmerRecyclerView

class FragmentC : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding : FragmentCBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: VerticalAdapter2
    private lateinit var mRecyclerView: ShimmerRecyclerView
    private lateinit var mLinearLayoutManager : LinearLayoutManager
    private var this_category = religion.second

    private var perPage = 20
    private var myTurn = true
    private var isLoading = true
    private var userScroll = true
    private var pageNo = 1
    private var postFinish = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCBinding.inflate(inflater, container, false)
        mAdapter = VerticalAdapter2(requireContext())
        mRecyclerView = binding.fragmentRecyclerC
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

        mainViewModel.getPostFC().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {

                    val postdata = it.data
                    if (postdata!=null)
                        mAdapter.setDataOther(postdata)
                    chipGroupVisibility(CG_VISIBLE)
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
                    binding.refreshDataC.isRefreshing = false
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


        binding.cgFragmentC.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            val selectedCategory = chip.text.toString()
            val myCat = myTranslator(selectedCategory)
            Log.d("Cut3", myCat)
            showShimmerEffect()
            chipGroupVisibility(CG_INVISIBLE)
            mAdapter.clearList()
            this_category = myCat
            pageNo = 1
            postFinish = 1
            mainViewModel.fetchPostSingle(this_category, TYPE_SINGLE, perPage,pageNo, FRAGMENT_NAME_C)
        }


        binding.refreshDataC.setOnRefreshListener {
            showShimmerEffect()
            chipGroupVisibility(CG_INVISIBLE)
            pageNo=1
            postFinish = 1
            mAdapter.clearList()
            mainViewModel.fetchPostSingle(this_category, TYPE_SINGLE, perPage,pageNo, FRAGMENT_NAME_C)
        }
        return binding.root
    }


    private fun firstApiCall() {
        /**this apiCall will only launch once(at startup)*/
        if (myTurn){
            isLoading = true
            mainViewModel.fetchPostSingle(this_category,TYPE_SINGLE, perPage, pageNo, FRAGMENT_NAME_C)
            myTurn = mainViewModel.isFirst
        }
    }


    /**Here isLoading checks that new call is made only after the previous data is loaded from observer*/
    private fun updateRecyclerView() {
        Log.d("MyCalll", isLoading.toString())
        if (isLoading){
            pageNo++
            Log.d("MyCalll", this_category)
            mainViewModel.fetchPostSingle(this_category,TYPE_SINGLE, perPage,pageNo, FRAGMENT_NAME_C)
            isLoading = false
        }
    }


    /**It compares the input category from chip and compares and give its category from its Pair*/
    private fun myTranslator(selectedCategory: String): String {
        var trans = this_category

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
        mRecyclerView.layoutManager = mLinearLayoutManager
        chipGroupVisibility(CG_INVISIBLE)
        showShimmerEffect()
    }


    private fun showSnackBar(message: String) {
        view?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }


    private fun showShimmerEffect() {
        mRecyclerView.showShimmer()
    }


    private fun hideShimmerEffect() {
        mRecyclerView.hideShimmer()
    }


    private fun chipGroupVisibility(s: String) {
        when(s){
            CG_INVISIBLE -> binding.cgFragmentC.visibility = View.INVISIBLE
            CG_VISIBLE -> binding.cgFragmentC.visibility = View.VISIBLE
        }
    }

}