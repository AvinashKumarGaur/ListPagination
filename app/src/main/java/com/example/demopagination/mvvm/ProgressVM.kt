package com.example.demopagination.mvvm

import android.view.View
import androidx.databinding.ObservableInt
import com.example.demopagination.R


class ProgressVM {
    var visibility = ObservableInt(View.GONE)
    /**
     * loaderColor value should be attr resource
     */
    val loaderColor = ObservableInt(R.color.text_color_dark_blue)
}