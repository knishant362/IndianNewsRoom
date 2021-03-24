package com.indiannewssroom.app.ui.activity.bookmark

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.indiannewssroom.app.R
import com.indiannewssroom.app.data.database.PostEntity
import com.indiannewssroom.app.databinding.ActivityBookmarkDetailBinding
import com.indiannewssroom.app.databinding.ActivityDetailsBinding
import com.indiannewssroom.app.model.BookmarkData
import com.indiannewssroom.app.model.PostData
import com.indiannewssroom.app.util.Constants
import com.indiannewssroom.app.util.Constants.Companion.BUNDLE_DATA
import com.indiannewssroom.app.util.Constants.Companion.INTENT_DATA
import com.indiannewssroom.app.viewmodel.PostViewModel
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter
import java.text.SimpleDateFormat
import java.util.*

class BookmarkDetailActivity : AppCompatActivity() {

    private var _binding : ActivityBookmarkDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBookmarkDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val myIntent = intent.getBundleExtra(INTENT_DATA)
        val myBundle = myIntent!!.getParcelable<BookmarkData>(BUNDLE_DATA)

        if (myBundle != null) {
            binding.txtPostTitl.text =  myBundle.title
            binding.txtPostDesc.setHtml(
                myBundle.content,
                HtmlHttpImageGetter(
                    binding.txtPostDesc,
                    null,
                    true )
            )
            binding.txtUploadTime.text = myBundle.date
        }

        footerClickListeners()

        binding.imgPostPoster.apply {
            if (myBundle != null){
                load(myBundle.postImage){
                    crossfade(600)
                }
            }
        }
        binding.txtPostDesc.setOnClickATagListener { widget, spannedText, href ->
            true
        }

        binding.imgBackButton.setOnClickListener {
            finish()
        }

    }

    private fun footerClickListeners() {

        binding.imgFacebook.setOnClickListener {
            openApp("https://www.facebook.com/INRMedia/","com.facebook.katana" )
        }
        binding.imgTwit.setOnClickListener {
            openApp("https://twitter.com/inrmedia","com.twitter.android" )
        }
        binding.imgYt.setOnClickListener {
            openApp("https://www.youtube.com/c/IndianNewsRoom","com.google.android.youtube" )
        }
        binding.imgInsta.setOnClickListener {
            openApp("https://www.instagram.com/indian_news_room/","com.instagram.android" )
        }
        binding.imgPlay.setOnClickListener {
            openApp("https://play.google.com/store/apps/details?id=com.indiannewssroom.app","com.instagram.android" )
        }
        binding.imgFeed.setOnClickListener {
            startActivity(Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.indiannewsroom.com/feed/")
            ))
        }
        binding.txtHome.setOnClickListener {
            startActivity(Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.indiannewsroom.com/")
            ))
        }
        binding.txtaboutus.setOnClickListener {
            startActivity(Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.indiannewsroom.com/about-us/")
            ))
        }
        binding.txtcontact.setOnClickListener {
            startActivity(Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.indiannewsroom.com/contact-us/")
            ))
        }
        binding.txtDisc.setOnClickListener {
            startActivity(Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.indiannewsroom.com/disclaimer/")
            ))
        }
        binding.txtPolicy.setOnClickListener {
            startActivity(Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.indiannewsroom.com/privacy-policy/")
            ))
        }
        binding.txtTerms.setOnClickListener {
            startActivity(Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.indiannewsroom.com/terms-conditions/")
            ))
        }
        binding.txtFollowUs.setOnClickListener {
            startActivity(Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.indiannewsroom.com/follow-us/")
            ))
        }
        binding.txtWork.setOnClickListener {
            startActivity(Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.indiannewsroom.com/work-with-us/")
            ))
        }
        binding.txtSite.setOnClickListener {
            startActivity(Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.indiannewsroom.com/site-map/")
            ))
        }

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
}