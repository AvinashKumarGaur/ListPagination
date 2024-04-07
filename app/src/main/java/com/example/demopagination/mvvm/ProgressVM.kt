package com.example.demopagination

import android.view.View
import androidx.databinding.ObservableInt


class ProgressVM {
    var visibility = ObservableInt(View.GONE)
    /**
     * loaderColor value should be attr resource
     */
    val loaderColor = ObservableInt(R.color.text_color_dark_blue)
}