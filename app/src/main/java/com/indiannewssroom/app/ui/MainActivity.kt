package com.indiannewssroom.app.ui

import android.os.Bundle
import android.view.Menu
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.indiannewssroom.app.R
import com.indiannewssroom.app.adapters.ViewPagerAdapter
import com.indiannewssroom.app.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
//        navController = findNavController(R.id.fragmentHost)
//        navView.setupWithNavController(navController,)
//
//        appBarConfiguration = AppBarConfiguration(setOf(
//                R.id.nav_home, R.id.nav_home,
//                R.id.nav_fragment_a,R.id.nav_fragment_b,
//                R.id.nav_fragment_c,R.id.nav_fragment_d,
//                R.id.nav_fragment_e,R.id.nav_fragment_f,
//                R.id.nav_fragment_g,R.id.nav_fragment_h,
//        ), drawerLayout)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

        initViewPager2WithFragments()

    }

    private fun initViewPager2WithFragments() {
        val viewPager2:ViewPager2 = findViewById(R.id.viewPagerHome)
        val adapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
        viewPager2.adapter = adapter

        val tablayout:TabLayout = findViewById(R.id.mainTabGroup)
        val names:Array<String> = arrayOf("होम","ब्रेकिंग न्यूज","खबरें","धर्म-कर्म","लाइफस्टाइल","मनोरंजन","दिलचस्प","खेल जगत","विज्ञान और तकनीक")
        TabLayoutMediator(tablayout,viewPager2){tab, position ->
            tab.text = names[position]
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

}