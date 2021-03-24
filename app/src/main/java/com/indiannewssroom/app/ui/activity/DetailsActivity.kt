package com.indiannewssroom.app.ui.activity

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
import com.indiannewssroom.app.databinding.ActivityDetailsBinding
import com.indiannewssroom.app.model.PostData
import com.indiannewssroom.app.util.Constants.Companion.BUNDLE_DATA
import com.indiannewssroom.app.util.Constants.Companion.INTENT_DATA
import com.indiannewssroom.app.viewmodel.PostViewModel
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter
import java.text.SimpleDateFormat
import java.util.*

class DetailsActivity : AppCompatActivity() {

    private var _binding : ActivityDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var postViewModel: PostViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailsBinding.inflate(layoutInflater)
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        setContentView(binding.root)
        supportActionBar?.hide()

        val myIntent = intent.getBundleExtra(INTENT_DATA)
        val myBundle = myIntent!!.getParcelable<PostData>(BUNDLE_DATA)

        val myDate = myBundle?.date?.toDate()?.formatTo("dd MMM yyyy")

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

            binding.txtUploadTime.text = myDate
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

        binding.imgSavePost.setOnClickListener {

                if (myBundle != null) {
                    myBundle.embedded?.wpFeaturedmedia?.get(0)?.let { it1 ->
                        it1.sourceUrl?.let { it2 ->
                            PostEntity(0,
                                myBundle.title?.rendered!!,
                                myBundle.content?.rendered!!,
                                it2,
                                myDate!!,
                                )
                        }
                    }?.let { it2 -> postViewModel.addPost(it2) }
                }

            Toast.makeText(this , "Post saved", Toast.LENGTH_SHORT ).show()
        }

        footerClickListeners()

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

        binding.imgBackButton.setOnClickListener {
            finish()
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