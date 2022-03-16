package com.pikolive.module.webview

import android.os.Looper
import android.view.ViewGroup
import android.webkit.WebView

/**
 * Creator: ED
 * Date: 2020/11/16 3:29 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/

object WebUtils {

    fun clearWebView(webView: WebView?) {

        webView ?: return
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return
        }
        webView.loadUrl("about:blank")
        webView.stopLoading()
        if (webView.handler != null) {
            webView.handler.removeCallbacksAndMessages(null)
        }
        webView.removeAllViews()
        val mViewGroup: ViewGroup? = webView.parent as? ViewGroup

        mViewGroup?.removeView(webView)

        webView.webChromeClient = null
//        webView.webViewClient = null
        webView.tag = null
        webView.clearHistory()
        webView.destroy()
    }

}