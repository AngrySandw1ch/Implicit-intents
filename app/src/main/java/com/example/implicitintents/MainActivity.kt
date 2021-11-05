package com.example.implicitintents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ShareCompat
import com.example.implicitintents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun openWebsite(view: View) {
        val webpage = Uri.parse(binding.webSiteEddittext.text.toString())
        Intent(Intent.ACTION_VIEW, webpage).apply {
            if (this.resolveActivity((packageManager)) != null) {
                startActivity(this)
            } else {
                Log.d("ImplicitIntents", "Can't handle this!")
            }
        }
    }
    fun openLocation(view: View) {
        val addressUri = Uri.parse("geo:0,0?q=${binding.locationEdittext.text.toString()}")
        Intent(Intent.ACTION_VIEW, addressUri).apply {
            if (this.resolveActivity(packageManager) != null) {
                startActivity(this)
            } else {
                Log.d("ImplicitIntents", "Can't handle this!")
            }
        }
    }
    fun shareText(view: View) {
        val txt = binding.shareEdittext.text.toString()
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle("Share this text with: ")
            .setText(txt)
            .startChooser()
    }
    fun takeAPicture(view: View) {
        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE).apply {
            if (this.resolveActivity(packageManager) != null) {
                startActivity(this)
            } else {
                Log.d("ImplicitIntents", "Can't handle this!")
            }
        }
    }
}