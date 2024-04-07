package com.example.demopagination.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.demopagination.model.Items
import com.example.demopagination.R
import com.example.demopagination.databinding.ActivityDetailsBinding


class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    lateinit var linkUrl :String
    lateinit var   textViewUrl:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setContentView(R.layout.activity_details)
        val imageView= findViewById<ImageView>(R.id.imageView)
         textViewUrl = findViewById<TextView>(R.id.tv_url)
        val repository = intent.getParcelableExtra<Items>("repository")
        if (repository != null) {
            linkUrl= repository.cloneUrl.toString()
            binding.tvDescription.text=repository.description
            textViewUrl.text=repository.cloneUrl
            // Use repository data to populate UI or perform other actions
            Glide.with(this)
                .load(repository.owner?.avatarUrl)
                .placeholder(R.drawable.ic_launcher_background) // Optional placeholder image while loading
                .error(R.drawable.ic_launcher_foreground) // Optional error image if the loading fails
                .into(imageView)
        }
        uiUpdate()

    }

    private fun uiUpdate() {
        textViewUrl.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java).apply {
                putExtra(WebViewActivity.EXTRA_URL, linkUrl)
            }
            startActivity(intent)
        }
    }
}