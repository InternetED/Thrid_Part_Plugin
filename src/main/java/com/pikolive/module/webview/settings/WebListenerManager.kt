package com.pikolive.module.webview.settings

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * Creator: ED
 * Date: 2020/11/17 2:01 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
interface WebListenerManager {

    fun setWebViewChromeClient(
        webView: WebView,
        webChromeClient: WebChromeClient
    )


    fun setWebViewClient(
        webView: WebView,
        webViewClient: WebViewClient
    )
}