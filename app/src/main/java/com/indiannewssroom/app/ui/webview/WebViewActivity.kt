package com.indiannewssroom.app.ui.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.indiannewssroom.app.R
import com.indiannewssroom.app.databinding.ActivityWebViewBinding
import com.indiannewssroom.app.util.Constants
import com.indiannewssroom.app.util.Constants.Companion.INTENT_DATA

class WebViewActivity : AppCompatActivity() {

    private var _binding : ActivityWebViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra(INTENT_DATA)

        if (url != null) {
            webViewSetup(url)
        }

    }

    private fun webViewSetup(url:String) {
        binding.myWebview.webViewClient = WebViewClient()
        binding.myWebview.apply {
            loadUrl(url)
        }
    }

    override fun onBackPressed() {
        if (binding.myWebview.canGoBack() ) binding.myWebview.goBack() else super.onBackPressed()
        finish()
    }

}