package com.indiannewssroom.app.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
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

private var _binding : ActivityDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var myWebView: WebView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myIntent = intent.getBundleExtra(INTENT_DATA)
        val myBundle = myIntent!!.getParcelable<PostData>(BUNDLE_DATA)

        supportActionBar?.hide()

        if (myBundle != null) {
            binding.txtPostTitl.text =  myBundle.title?.rendered
            binding.txtPostDesc.setHtml(
                myBundle.content?.rendered!!,
                HtmlHttpImageGetter(
                    binding.txtPostDesc,
                    null,
                    true )
            )
            binding.txtAuthorName.text =  myBundle.embedded?.author?.get(0)?.name
            binding.txtUploadTime.text = myBundle.date?.toDate()?.formatTo("dd MMM yyyy")
            myBundle.date?.toDate()?.formatTo("dd MMM yyyy")?.let { Log.d("MYDATee", it) }
        }

        binding.imgShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, myBundle!!.links?.self?.get(0)?.href)
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

        binding.imgPostPoster.apply {
            if (myBundle?.embedded?.wpFeaturedmedia != null){
                load(myBundle.embedded.wpFeaturedmedia[0]?.sourceUrl){
                    crossfade(600)
                }
            }
        }

        binding.txtPostDesc.setOnClickATagListener { widget, spannedText, href ->
            Toast.makeText(this@DetailsActivity, postCategory, Toast.LENGTH_SHORT).show()
            true
        }
    }

    /**Dynamically create a single inside a chipGroup]*/
    private fun createSingleChip(chipName: String, myChipGroup: ChipGroup) {
        val chip = Chip(this)
        chip.text = chipName
        chip.setTextAppearanceResource(R.style.ChipTextStyle_Selected)
        myChipGroup.addView(chip)
        Log.d("MYCaT", chipName.toString())
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