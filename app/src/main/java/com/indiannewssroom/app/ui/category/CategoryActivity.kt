package com.indiannewssroom.app.ui.category

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.indiannewssroom.app.R
import com.indiannewssroom.app.databinding.ActivityCategoryBinding
import com.indiannewssroom.app.ui.MainActivity
import com.indiannewssroom.app.util.Constants
import com.indiannewssroom.app.util.Constants.Companion.astrology
import com.indiannewssroom.app.util.Constants.Companion.bollywood_cinema
import com.indiannewssroom.app.util.Constants.Companion.breaking_news
import com.indiannewssroom.app.util.Constants.Companion.chanakya_niti
import com.indiannewssroom.app.util.Constants.Companion.digital_duniya
import com.indiannewssroom.app.util.Constants.Companion.dilchasp
import com.indiannewssroom.app.util.Constants.Companion.entertainment
import com.indiannewssroom.app.util.Constants.Companion.fashion_and_style
import com.indiannewssroom.app.util.Constants.Companion.food_and_recipes
import com.indiannewssroom.app.util.Constants.Companion.game_and_player
import com.indiannewssroom.app.util.Constants.Companion.general_knowledge
import com.indiannewssroom.app.util.Constants.Companion.ghrelu_nuskhe
import com.indiannewssroom.app.util.Constants.Companion.health
import com.indiannewssroom.app.util.Constants.Companion.humour
import com.indiannewssroom.app.util.Constants.Companion.international_news
import com.indiannewssroom.app.util.Constants.Companion.latest_news
import com.indiannewssroom.app.util.Constants.Companion.lent_and_festivals
import com.indiannewssroom.app.util.Constants.Companion.lifestyle
import com.indiannewssroom.app.util.Constants.Companion.national_news
import com.indiannewssroom.app.util.Constants.Companion.relationship
import com.indiannewssroom.app.util.Constants.Companion.religion
import com.indiannewssroom.app.util.Constants.Companion.science_and_technology
import com.indiannewssroom.app.util.Constants.Companion.social_media
import com.indiannewssroom.app.util.Constants.Companion.spiritual
import com.indiannewssroom.app.util.Constants.Companion.tour_and_travel
import com.indiannewssroom.app.util.Constants.Companion.tv_and_serial
import com.indiannewssroom.app.util.Constants.Companion.vastu_shastra
import com.indiannewssroom.app.util.Constants.Companion.viral_news
import com.indiannewssroom.app.viewmodel.MainViewModel
import com.indiannewssroom.app.viewmodel.MainViewModelFactory

class CategoryActivity : AppCompatActivity() {

    private var _binding : ActivityCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel
    private var defaultCat1 = viral_news.second
    private var defaultCat2 = entertainment.second
    private var defaultCat3 = bollywood_cinema.second
    private var defaultCat4 = latest_news.second
    private var defaultCat5 = humour.second


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityCategoryBinding.inflate(layoutInflater)
        val mainViewModelFactory = MainViewModelFactory(this)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

        setContentView(binding.root)

        setupAdapter()

        binding.categoryMenu1.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent?.getItemAtPosition(position).toString()
            val myCat = myTranslator(selectedItem)
            defaultCat1 = myCat
            Toast.makeText(applicationContext,"Selected : $myCat", Toast.LENGTH_SHORT).show()
        }

        binding.categoryMenu2.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent?.getItemAtPosition(position).toString()
            val myCat = myTranslator(selectedItem)
            defaultCat2 = myCat
            Toast.makeText(applicationContext,"Selected : $myCat", Toast.LENGTH_SHORT).show()
        }

        binding.categoryMenu3.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent?.getItemAtPosition(position).toString()
            val myCat = myTranslator(selectedItem)
            defaultCat3 = myCat
            Toast.makeText(applicationContext,"Selected : $myCat", Toast.LENGTH_SHORT).show()
        }

        binding.categoryMenu4.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent?.getItemAtPosition(position).toString()
            val myCat = myTranslator(selectedItem)
            defaultCat4 = myCat
            Toast.makeText(applicationContext,"Selected : $myCat", Toast.LENGTH_SHORT).show()
        }

        binding.categoryMenu5.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent?.getItemAtPosition(position).toString()
            val myCat = myTranslator(selectedItem)
            defaultCat5 = myCat
            Toast.makeText(applicationContext,"Selected : $myCat", Toast.LENGTH_SHORT).show()
        }

        binding.btnConfirm.setOnClickListener {
            mainViewModel.saveHomeCategories(
                defaultCat1,
                defaultCat2,
                defaultCat3,
                defaultCat4,
                defaultCat5
            )
            startActivity(Intent(this,MainActivity::class.java))
            Log.d("Kumar", "$defaultCat1 $defaultCat2 $defaultCat3 $defaultCat4 $defaultCat5")
            Toast.makeText(this,"Successful", Toast.LENGTH_SHORT).show()
//            finish()
        }
    }

    private fun setupAdapter() {
        val categories = resources.getStringArray(R.array.categories)
        val arrayAdapter = ArrayAdapter(this, R.layout.exposed_menu_item, categories)
        binding.categoryMenu1.setAdapter(arrayAdapter)
        binding.categoryMenu2.setAdapter(arrayAdapter)
        binding.categoryMenu3.setAdapter(arrayAdapter)
        binding.categoryMenu4.setAdapter(arrayAdapter)
        binding.categoryMenu5.setAdapter(arrayAdapter)
    }

    /**It compares the input category from chip and compares and give its category from its Pair*/
    private fun myTranslator(selectedCategory: String): String {
        var trans = breaking_news.second

        when(selectedCategory) {
            breaking_news.second -> {
                trans = breaking_news.second
            }
            latest_news.second -> {
                trans = latest_news.second
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
            religion.first -> {
                trans = religion.second
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
            lifestyle.first -> {
                trans = lifestyle.second
            }
            health.first -> {
                trans = health.second
            }
            relationship.first -> {
                trans = relationship.second
            }
            fashion_and_style.first -> {
                trans = fashion_and_style.second
            }
            tour_and_travel.first -> {
                trans = tour_and_travel.second
            }
            food_and_recipes.first -> {
                trans = food_and_recipes.second
            }
            ghrelu_nuskhe.first -> {
                trans = ghrelu_nuskhe.second
            }
            entertainment.first -> {
                trans = entertainment.second
            }
            tv_and_serial.first -> {
                trans = tv_and_serial.second
            }
            bollywood_cinema.first -> {
                trans = bollywood_cinema.second
            }
            humour.first -> {
                trans = humour.second
            }
            dilchasp.first -> {
                trans = dilchasp.second
            }
            game_and_player.first -> {
                trans = game_and_player.second
            }
            science_and_technology.first -> {
                trans = science_and_technology.second
            }
            digital_duniya.first -> {
                trans = digital_duniya.second
            }
            general_knowledge.first -> {
                trans = general_knowledge.second
            }
        }

        return trans
    }

}