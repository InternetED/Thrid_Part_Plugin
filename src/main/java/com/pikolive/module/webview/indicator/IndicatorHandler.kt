package com.pikolive.module.webview.indicator

import android.webkit.WebView

/**
 * Creator: ED
 * Date: 2020/11/17 9:54 AM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class IndicatorHandler(private val mWebIndicator: WebIndicator) : IndicatorController {


    override fun progress(v: WebView?, newProgress: Int) {
        when (newProgress) {
            0 -> {
                reset()
            }
            in 1..10 -> {
                showIndicator()
            }
            in 11..94 -> {
                setProgress(newProgress)
            }
            else -> {
                setProgress(newProgress)
                finish()
            }
        }

    }

    override fun offerIndicator(): WebIndicator {
        return mWebIndicator
    }

    private fun reset() {
        mWebIndicator.reset()
    }

    override fun showIndicator() {
        mWebIndicator.show()
    }

    override fun setProgress(newProgress: Int) {
        mWebIndicator.setProgress(newProgress)
    }

    override fun finish() {
        mWebIndicator.hide()
    }

}