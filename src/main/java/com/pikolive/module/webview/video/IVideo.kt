package com.pikolive.module.webview.video

import android.view.View
import android.webkit.WebChromeClient.CustomViewCallback

/**
 * Creator: ED
 * Date: 2020/11/17 10:34 AM
 * Mail: salahayo3192@gmail.com
 *
 * **/
interface IVideo {


    fun onShowCustomView(view: View?, callback: CustomViewCallback?)


    fun onHideCustomView()


    fun isVideoState(): Boolean
}