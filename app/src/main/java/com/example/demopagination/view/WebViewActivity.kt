package com.example.demopagination

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.view.View
import android.widget.ProgressBar


class WebViewActivity : AppCompatActivity() {
    lateinit var  webView: WebView
    lateinit var progressBar:ProgressBar
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view_activity)

         webView = findViewById(R.id.webView)
        progressBar=findViewById(R.id.progressBar)
        webView.settings.javaScriptEnabled = true // Enable JavaScript
        webView.webViewClient = WebViewClient() // Open links in the same WebView
        progressBar.visibility=ProgressBar.VISIBLE
        val url = intent.getStringExtra(EXTRA_URL)
        if (url != null) {
            setupWebView(url)
        }
    }
    private fun setupWebView(url: String) {
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility= ProgressBar.INVISIBLE
            }
        }
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)
    }
    companion object {
        const val EXTRA_URL = "extra_url"
    }
}

