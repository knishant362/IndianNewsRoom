
package com.indiannewssroom.app.ui.fragments.b

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.indiannewssroom.app.databinding.FragmentBBinding
import com.indiannewssroom.app.model.PostData
import com.indiannewssroom.app.util.Constants.Companion.CG_INVISIBLE
import com.indiannewssroom.app.util.Constants.Companion.CG_VISIBLE
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_B
import com.indiannewssroom.app.util.Constants.Companion.international_news
import com.indiannewssroom.app.util.Constants.Companion.latest_news
import com.indiannewssroom.app.util.Constants.Companion.national_news
import com.indiannewssroom.app.util.Constants.Companion.social_media
import com.indiannewssroom.app.util.Constants.Companion.viral_news
import com.indiannewssroom.app.util.Status
import com.indiannewssroom.app.viewmodel.MainViewModel
import com.todkars.shimmer.ShimmerRecyclerView
import java.util.*

class FragmentB : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding : FragmentBBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: VerticalAdapter2
    private lateinit var mRecyclerView: ShimmerRecyclerView
    private lateinit var mLinearLayoutManager : LinearLayoutManager
    private var this_category = latest_news.second

    private var myTurn = true
    private var isLoading = false
    private var userScroll = true
    private var postFinish = 1
    private var pageNo = 1
    private var perPage = 20
    val allPost = mutableListOf<PostData>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?)
    : View? {

        _binding = FragmentBBinding.inflate(inflater, container, false)
        mAdapter = VerticalAdapter2(requireContext())
        mRecyclerView = binding.fragmentRecyclerB
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


        binding.cgFragmentB.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            val selectedCategory = chip.text.toString()
            val chipCat = myTranslator(selectedCategory)
            Log.d("Cut3", chipCat)
            showShimmerEffect()
            chipGroupVisibility(CG_INVISIBLE)
            mAdapter.clearList()
            allPost.clear()
            this_category = chipCat
            pageNo = 1
            postFinish = 1
            mainViewModel.fetchPostSingle( this_category, perPage, pageNo, FRAGMENT_NAME_B )
        }

        mainViewModel.getPostFB().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {

                    val postdata = it.data
                    if (postdata!=null){
                        allPost.addAll(postdata)
                        mAdapter.setDataOther(postdata)
                    }
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
                        mRecyclerView.scrollToPosition(myff);
                        isLoading = true
                    }
                    binding.refreshDataB.isRefreshing = false
                    hideShimmerEffect()
                }
                Status.LOADING -> {
                    showSnackBar("Loading")
                }
                Status.ERROR -> {
                    //Handle Error
                    hideShimmerEffect()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        })



        /**Edit text for list filtering list in recycler view*/
        binding.etSearchPostB.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                filterPost(s.toString())
            }
        })



        binding.refreshDataB.setOnRefreshListener {
            showShimmerEffect()
            chipGroupVisibility(CG_INVISIBLE)
            pageNo = 1
            postFinish = 1
            allPost.clear()
            mAdapter.clearList()
            mainViewModel.fetchPostSingle(this_category,perPage,pageNo, FRAGMENT_NAME_B)
        }
        return binding.root
    }

    /** Filter list with EditText entered*/
    private fun filterPost(text: String) {
        val filtereList = mutableListOf<PostData>()
        for (item in allPost){
            if (item.title?.rendered?.toLowerCase(Locale.ROOT)
                    ?.contains(text.toLowerCase(Locale.ROOT)) == true
            ) {
                filtereList.add(item)
                Log.d("TTTT", item.title.toString())
            }
        }
        mAdapter.filteredList(filtereList)
    }


    private fun firstApiCall() {
        /**this apiCall will only launch once(at startup)*/
        if (myTurn){
            isLoading = true
            mainViewModel.fetchPostSingle(this_category,perPage, pageNo, FRAGMENT_NAME_B)
            myTurn = mainViewModel.isFirst
        }
    }


    /**Here isLoading checks that new call is made only after the previous data is loaded from observer*/
    private fun updateRecyclerView() {
        Log.d("MyCalll", isLoading.toString())
        if (isLoading){
            pageNo++
            Log.d("MyCalll", this_category)
            mainViewModel.fetchPostSingle(this_category, perPage,pageNo, FRAGMENT_NAME_B)
            isLoading = false
        }
    }


    /**It compares the input category from chip and compares and give its category from its Pair*/
    private fun myTranslator(selectedCategory: String): String {
        var trans = latest_news.second

        when(selectedCategory){
            "All" -> {
                trans =  latest_news.second
            }
            national_news.first -> {
                trans = national_news.second
            }
            international_news.first -> {
                trans = international_news.second
            }
            viral_news.first -> {
                trans = viral_news.second
            }
            social_media.first -> {
                trans = social_media.second
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
            CG_INVISIBLE -> binding.cgFragmentB.visibility = View.INVISIBLE
            CG_VISIBLE -> binding.cgFragmentB.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}