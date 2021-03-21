package com.indiannewssroom.app.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.androidstudy.networkmanager.Tovuti
import com.indiannewssroom.app.R
import com.indiannewssroom.app.viewmodel.MainViewModel
import com.indiannewssroom.app.viewmodel.MainViewModelFactory


class SplashActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()

        val hand = Handler()
        Tovuti.from(this).monitor { connectionType, isConnected, isFast ->
            Log.d("SpLA", isFast.toString() + connectionType.toString())
            if (isConnected) {
                hand.postDelayed(
                    {
                        startActivity(
                            Intent(this@SplashActivity, MainActivity::class.java)
                        )
                        finish()
                    }, 4000
                )
            } else {
                hand.postDelayed(
                    {
                        startActivity(
                            Intent(this@SplashActivity, NetworkActivity::class.java)
                        )
                        finish()
                    }, 4000
                )
            }
        }

        val mainViewModelFactory = MainViewModelFactory(this)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

//        mainViewModel.readHomeCategories.asLiveData().observe(this,{
//            mainViewModel.cat1 = it.category1
//            mainViewModel.cat2 = it.category2
//            mainViewModel.cat3 = it.category3
//            mainViewModel.cat4 = it.category4
//            mainViewModel.cat5 = it.category5
//        })
    }

    override fun onStop() {
        super.onStop()
        Tovuti.from(this).stop();
    }
}