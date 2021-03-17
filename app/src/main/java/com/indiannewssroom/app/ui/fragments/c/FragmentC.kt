package com.indiannewssroom.app.ui.fragments.c

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.indiannewssroom.app.R
import com.indiannewssroom.app.adapters.VerticalAdapter
import com.indiannewssroom.app.data.NetworkResults
import com.indiannewssroom.app.databinding.FragmentBBinding
import com.indiannewssroom.app.databinding.FragmentCBinding
import com.indiannewssroom.app.model.PostData
import com.indiannewssroom.app.util.Constants
import com.indiannewssroom.app.util.Constants.Companion.FRAGMENT_NAME_C
import com.indiannewssroom.app.util.Constants.Companion.astrology
import com.indiannewssroom.app.util.Constants.Companion.chanakya_niti
import com.indiannewssroom.app.util.Constants.Companion.lent_and_festivals
import com.indiannewssroom.app.util.Constants.Companion.religion
import com.indiannewssroom.app.util.Constants.Companion.spiritual
import com.indiannewssroom.app.util.Constants.Companion.vastu_shastra
import com.indiannewssroom.app.viewmodel.MainViewModel
import com.indiannewssroom.app.viewmodel.PostViewModel

class FragmentC : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding : FragmentCBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: VerticalAdapter
    private lateinit var mRecyclerView: RecyclerView
    private var perPage = 10
    private var this_category = religion.second
    private var this_cat = arrayListOf<Pair<String, String>>()
    private lateinit var postViewModel: PostViewModel
    private var myTurn = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (myTurn){
            mainViewModel.apiCall(this_category, perPage, FRAGMENT_NAME_C)
            Log.d("Cut12", "called")
        }

        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        myTurn = postViewModel.isFirst

        _binding = FragmentCBinding.inflate(inflater, container, false)
        mAdapter = VerticalAdapter()
        mRecyclerView = binding.fragmentRecyclerC
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        /** here tv_and_serial is a Pair<>() */

//        val chipID = binding.cgFragmentC.checkedChipId
//        val chip = binding.cgFragmentC.findViewById<Chip>(chipID)
//        val selectedCategory = chip.text.toString()
//        Log.d("Cut33", selectedCategory)
//
//        binding.cgFragmentC

//        cat.observe(viewLifecycleOwner, {
//            Log.d("Cut4", this_category)
//            Log.d("Cut5", it)
//            mainViewModel.apiCall(this_category, perPage, FRAGMENT_NAME_C)
//        })

        binding.cgFragmentC.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            val selectedCategory = chip.text.toString()
            val my = myTranslator(selectedCategory)
            Log.d("Cut3", my)
//            _cat.value = my
//            this_category = my
            mainViewModel.apiCall(my, perPage, FRAGMENT_NAME_C)
        }
        mainViewModel.postResponseC.observe(viewLifecycleOwner, {
            val postdata = it.data
            if (postdata!=null){
                mAdapter.setDataOther(postdata)
                mAdapter.notifyDataSetChanged()
            }
        })

        return binding.root
    }

    private fun myTranslator(selectedCategory: String): String {
        var trans = religion.second

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

}