package com.indiannewssroom.app.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.androidstudy.networkmanager.Tovuti
import com.indiannewssroom.app.R


class SplashActivity : AppCompatActivity() {
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
                    }, 4000
                )
            } else {
                hand.postDelayed(
                    {
                        startActivity(
                            Intent(this@SplashActivity, NetworkActivity::class.java)
                        )
                    }, 4000
                )
            }
        }

    }

    override fun onStop() {
        super.onStop()
        Tovuti.from(this).stop();
    }
}