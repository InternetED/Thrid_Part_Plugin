package com.pikolive.module.webview.indicator

/**
 * Creator: ED
 * Date: 2020/11/16 3:47 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
interface BaseIndicatorSpec {

    fun show()

    fun hide()

    fun reset()

    fun setProgress(newProgress: Int)
}