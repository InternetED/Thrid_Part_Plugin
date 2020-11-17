package com.pikolive.module.webview.fixed

import android.graphics.Bitmap
import android.webkit.WebBackForwardList
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * Creator: ED
 * Date: 2020/11/16 2:55 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
//class FixedOnReceivedTitle : WebViewClient()  {
//    private var mWebChromeClient: WebChromeClient? = null
//    private var mIsOnReceivedTitle = false
//
//    fun setWebChromeClient(webChromeClient: WebChromeClient?) {
//        mWebChromeClient = webChromeClient
//    }
//
//    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
//        mIsOnReceivedTitle = false
//    }
//
//    override fun onPageFinished(view: WebView?, url: String?) {
//        if (!mIsOnReceivedTitle && mWebChromeClient != null) {
//            var list: WebBackForwardList? = null
//            try {
//                list = view.copyBackForwardList()
//            } catch (e: NullPointerException) {
//                e.printStackTrace()
//            }
//            if (list != null && list.size > 0 && list.currentIndex >= 0 && list.getItemAtIndex(list.currentIndex) != null) {
//                val previousTitle = list.getItemAtIndex(list.currentIndex).title
//                mWebChromeClient!!.onReceivedTitle(view, previousTitle)
//            }
//        }
//    }
//
//    fun onReceivedTitle() {
//        mIsOnReceivedTitle = true
//    }
//
//
//}