package com.pikolive.module.webview.indicator

import android.webkit.WebView

/**
 * Creator: ED
 * Date: 2020/11/17 9:53 AM
 * Mail: salahayo3192@gmail.com
 *
 * **/

interface IndicatorController {
    fun progress(v: WebView?, newProgress: Int)

    fun offerIndicator(): WebIndicator

    fun showIndicator()
    fun setProgress(newProgress: Int)
    fun finish()
}
