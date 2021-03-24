package com.indiannewssroom.app.ui.activity.category

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.indiannewssroom.app.databinding.CategoryActivityBinding
import com.indiannewssroom.app.util.Constants
import com.indiannewssroom.app.util.Constants.Companion.INTENT_DATA
import com.indiannewssroom.app.util.Constants.Companion.breaking_news
import com.indiannewssroom.app.viewmodel.CategoryViewModel

class CategoryActivity : AppCompatActivity() {

    private lateinit var categoryViewModel: CategoryViewModel
    private var _binding : CategoryActivityBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = CategoryActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        binding.cat1.setOnClickListener {
            Log.d("TYTY", "called")
            myNavigate(breaking_news.second)
        }

        binding.cat2.setOnClickListener {
            myNavigate(Constants.latest_news.second)

        }

        binding.cat3.setOnClickListener {
            myNavigate(Constants.religion.second)

        }

        binding.cat4.setOnClickListener {
            myNavigate(Constants.lifestyle.second)

        }

        binding.cat5.setOnClickListener {
            myNavigate(Constants.entertainment.second)

        }

        binding.cat6.setOnClickListener {
            myNavigate(Constants.dilchasp.second)

        }

        binding.cat7.setOnClickListener {
            myNavigate(Constants.game_and_player.second)

        }

        binding.cat8.setOnClickListener {
            myNavigate(Constants.science_and_technology.second)
        }

        binding.cat9.setOnClickListener {
            myNavigate(Constants.general_knowledge.second)
        }

    }

    private fun myNavigate(id: String) {
        val intent = Intent(this@CategoryActivity, CategoryDetailsActivity::class.java )
        intent.putExtra(INTENT_DATA, id)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}