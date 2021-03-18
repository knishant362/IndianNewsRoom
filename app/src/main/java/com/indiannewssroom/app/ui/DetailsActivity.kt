package com.indiannewssroom.app.ui

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import coil.load
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.indiannewssroom.app.R
import com.indiannewssroom.app.databinding.ActivityDetailsBinding
import com.indiannewssroom.app.model.PostData
import com.indiannewssroom.app.util.Constants.Companion.BUNDLE_DATA
import com.indiannewssroom.app.util.Constants.Companion.INTENT_DATA
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter
import java.text.SimpleDateFormat
import java.util.*

class DetailsActivity : AppCompatActivity() {

//    private var _binding : ActivityDetailsWebviewBinding? = null
private var _binding : ActivityDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var myWebView: WebView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        _binding = ActivityDetailsWebviewBinding.inflate(layoutInflater)
        _binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myIntent = intent.getBundleExtra(INTENT_DATA)
        val myBundle = myIntent!!.getParcelable<PostData>(BUNDLE_DATA)
//        myWebView= binding.detailWebView

//        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()

//        webViewSetup(myBundle?.link!!)
//        webViewSetup(myBundle?.content!!.rendered!!)
        binding.txtPostTitl.text =  myBundle?.title?.rendered
        binding.txtAuthorName.text =  myBundle?.embedded?.author?.get(0)?.name

        if (myBundle != null) {
            binding.txtUploadTime.text = myBundle.date?.toDate()?.formatTo("dd MMM yyyy")
            myBundle.date?.toDate()?.formatTo("dd MMM yyyy")?.let { Log.d("MYDATee", it) }
        }

        binding.imgShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, myBundle!!.link)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }



        val myChipGroup = binding.cgCategory
        val postCategory = myBundle?.embedded?.wpTerm?.get(0)?.get(0)?.name
        if(postCategory != null){
            createSingleChip(postCategory, myChipGroup)
        }

//
//        binding.txtPostDesc.text = HtmlCompat.fromHtml(myBundle?.content!!.rendered!!, HtmlCompat.FROM_HTML_MODE_COMPACT)
        binding.imgPostPoster.apply {
//            .mediaDetails?.sizes?.full?.sourceUrl
            if (myBundle?.embedded?.wpFeaturedmedia != null){
                load(myBundle.embedded.wpFeaturedmedia[0]?.sourceUrl){
                    crossfade(600)
                }
            }
        }
////        val imageGetter = MyImageGetter(lifecycleScope, resources, RequestManager(Glide.get(this), lifecycle), binding.txtPostDesc)
////        val styledText = HtmlCompat.fromHtml(myBundle?.content!!.rendered!!, HtmlCompat.FROM_HTML_MODE_COMPACT, imageGetter, null)
////        binding.txtPostDesc.text = styledText
//        val pat = HtmlHttpImageGetter(binding.txtPostDesc)
        if (myBundle != null) {
            binding.txtPostDesc.setHtml(myBundle.content?.rendered!!,
                HtmlHttpImageGetter(binding.txtPostDesc, null, true ))
        }
    }

    /**Dynamically create multiple chips inside a chipGroup]*/
//    private fun createMultipleChips(categories: List<Int?>?, myChipGroup: ChipGroup) {
//        if (categories != null) {
//            for (element in categories){
//                val chip = Chip(this)
//                chip.text = element.toString()
//                myChipGroup.addView(chip)
//                Log.d("MYCaT", element.toString())
//            }
//        }
//    }

    /**Dynamically create a single inside a chipGroup]*/
    private fun createSingleChip(chipName: String, myChipGroup: ChipGroup) {
        val chip = Chip(this)
        chip.text = chipName
        chip.setTextAppearanceResource(R.style.ChipTextStyle_Selected)
        myChipGroup.addView(chip)
        Log.d("MYCaT", chipName.toString())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun webViewSetup(url: String) {
        myWebView.webChromeClient = WebChromeClient()
        myWebView.webViewClient = WebViewClient()
        myWebView.apply {
//            loadUrl(url)
            loadDataWithBaseURL(null, url, "text/html", "UTF-8", null)

        }
    }

    private fun String.toDate(dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss", timeZone: TimeZone = TimeZone.getTimeZone("UTC")): Date {
        val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
        parser.timeZone = timeZone
        return parser.parse(this)
    }

    private fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
        formatter.timeZone = timeZone
        return formatter.format(this)
    }

}