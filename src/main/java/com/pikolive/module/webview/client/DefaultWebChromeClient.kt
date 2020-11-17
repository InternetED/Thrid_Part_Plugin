package com.pikolive.module.webview.client

import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.pikolive.module.webview.indicator.IndicatorController
import com.pikolive.module.webview.video.IVideo

/**
 * Creator: ED
 * Date: 2020/11/17 9:16 AM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class DefaultWebChromeClient(
    webChromeClient: WebChromeClient?,
    private val mIndicatorController: IndicatorController,
    private val mVideo : IVideo
) : WebChromeClientDelegate(webChromeClient) {
    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
        mIndicatorController.progress(view,newProgress)
    }

    override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
        mVideo.onShowCustomView(view,callback)
    }

    override fun onHideCustomView() {
        mVideo.onHideCustomView()
    }
}