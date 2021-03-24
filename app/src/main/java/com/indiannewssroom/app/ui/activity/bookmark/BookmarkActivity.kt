package com.indiannewssroom.app.ui.activity.bookmark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.indiannewssroom.app.R
import com.indiannewssroom.app.adapters.BookmarkAdapter
import com.indiannewssroom.app.databinding.ActivityBookmarkBinding
import com.indiannewssroom.app.viewmodel.PostViewModel

class BookmarkActivity : AppCompatActivity() {

    private var _binding : ActivityBookmarkBinding? = null
    private val binding get() = _binding!!
    private lateinit var postViewModel: PostViewModel
    private lateinit var mAdapter : BookmarkAdapter
    private lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAdapter = BookmarkAdapter(this)
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        mRecyclerView = binding.bookmarkRecycler
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        postViewModel.readPosts.observe(this, {
            Log.d("NIsDb", it.toString())
            mAdapter.setData(it)
            mAdapter.notifyDataSetChanged()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.bookmark_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.nav_delete_all -> {

                val builder = AlertDialog.Builder(this)
                builder.setMessage("Do you want to delete all posts ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes"
                    ) { dialog, which -> postViewModel.clearDatabase(); finish() }
                    .setNegativeButton("No"
                    ){ dialog, which -> dialog.cancel()
                    }

                builder.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}