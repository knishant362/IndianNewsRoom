package com.indiannewssroom.app.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.indiannewssroom.app.R
import com.indiannewssroom.app.adapters.ViewPagerAdapter
import com.indiannewssroom.app.ui.about.AboutActivity
import com.indiannewssroom.app.ui.category.CategoryActivity
import com.indiannewssroom.app.util.Constants.Companion.breaking_news
import com.indiannewssroom.app.util.Constants.Companion.dilchasp
import com.indiannewssroom.app.util.Constants.Companion.entertainment
import com.indiannewssroom.app.util.Constants.Companion.game_and_player
import com.indiannewssroom.app.util.Constants.Companion.general_knowledge
import com.indiannewssroom.app.util.Constants.Companion.homeTabName
import com.indiannewssroom.app.util.Constants.Companion.latest_news
import com.indiannewssroom.app.util.Constants.Companion.lifestyle
import com.indiannewssroom.app.util.Constants.Companion.religion
import com.indiannewssroom.app.util.Constants.Companion.science_and_technology
import com.indiannewssroom.app.viewmodel.MainViewModel
import com.indiannewssroom.app.viewmodel.MainViewModelFactory

class MainActivity : FragmentActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toolbar: ImageView
    private lateinit var viewPager2:ViewPager2
    private lateinit var tablayout:TabLayout
    private lateinit var adapter : ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainViewModelFactory = MainViewModelFactory(this)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        toolbar = findViewById(R.id.drawerMenu)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        viewPager2 = findViewById(R.id.viewPagerHome)
        tablayout = findViewById(R.id.mainTabGroup)
        adapter = ViewPagerAdapter(this)
        viewPager2.adapter = adapter

        initViewPager2WithFragments()

//        mainViewModel.fetchPosts()

        toolbar.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START);
        }

        navView.setNavigationItemSelectedListener { myItem ->
            when(myItem.itemId){
                R.id.nav_youtube -> {
                    openApp("https://www.youtube.com/c/IndianNewsRoom","com.google.android.youtube" )
                    drawerState()
                    true
                }
                R.id.nav_instagram -> {
                    openApp("https://www.instagram.com/indian_news_room/","com.instagram.android" )
                    drawerState()
                    true
                }
                R.id.nav_facebook -> {
                    openApp("https://www.facebook.com/INRMedia/","com.facebook.katana" )
                    drawerState()
                    true
                }
                R.id.nav_twitter -> {
                    openApp("https://twitter.com/inrmedia","com.twitter.android" )
                    drawerState()
                    true
                }
                R.id.nav_visit_us -> {
                    startActivity(Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.indiannewsroom.com/")
                    ))
                    drawerState()
                    true
                }
                R.id.nav_share -> {
                    shareApp("https://play.google.com/store/apps/details?id=com.indiannewssroom.app")
                    drawerState()
                    true
                }
                R.id.nav_rate -> {
                    startActivity(Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=com.indiannewssroom.app")
                    ))
                    drawerState()
                    true
                }
                R.id.nav_about_app -> {
                    startActivity(Intent(this, AboutActivity::class.java))
                    true
                }
                R.id.nav_home_categories -> {
                    startActivity(Intent(this, CategoryActivity::class.java))
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun shareApp(link : String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,link)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun openApp(url: String, app: String) {
        val sendIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setPackage(app)
        startActivity(sendIntent)
    }

    private fun drawerState() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    private fun initViewPager2WithFragments() {
        val names:Array<String> = arrayOf(homeTabName,
            breaking_news.first,latest_news.first,
            religion.first,lifestyle.first,
            entertainment.first,dilchasp.first,
            game_and_player.first,science_and_technology.first,
            general_knowledge.first
        )
        TabLayoutMediator(tablayout,viewPager2){tab, position ->
            tab.text = names[position]
        }.attach()
    }


}