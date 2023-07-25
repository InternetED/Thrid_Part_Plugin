package com.pikolive.module.webview.settings

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * Creator: ED
 * Date: 2020/11/17 2:10 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class XWebSettings : WebListenerManager, WebSettingManager {


    override fun toSetting(webView: WebView): WebSettings {

        return settings(webView)
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun settings(webView: WebView): WebSettings {
        return webView.settings.apply {
            javaScriptEnabled = true
            setSupportZoom(true)
            builtInZoomControls = false

            //根据cache-control获取数据。
            cacheMode = WebSettings.LOAD_DEFAULT
//            //没网，则从本地获取，即离线加载
//            mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK)


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //适配5.0不允许http和https混合使用情况
                this.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
            }

            textZoom = 100
            databaseEnabled = true
            cacheMode = WebSettings.LOAD_DEFAULT
            loadsImagesAutomatically = true
            setSupportMultipleWindows(false)
            // 是否阻塞加载网络图片  协议http or https
            blockNetworkImage = false
            // 允许加载本地文件html  file协议
            allowFileAccess = true

            // 通过 file url 加载的 Javascript 读取其他的本地文件 .建议关闭
            allowFileAccessFromFileURLs = false
            // 允许通过 file url 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源
            allowUniversalAccessFromFileURLs = false

            javaScriptCanOpenWindowsAutomatically = true

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN)
//            } else {
//                mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL)
//            }
            loadWithOverviewMode = false
            useWideViewPort = false
            domStorageEnabled = true
            setNeedInitialFocus(true)
//            defaultTextEncodingName = "utf-8" //设置编码格式
            defaultFontSize = 16
            minimumFontSize = 12 //设置 WebView 支持的最小字体大小，默认为 8
            setGeolocationEnabled(true)

//            setAppCachePath()
        }

    }


    override fun setWebViewChromeClient(webView: WebView, webChromeClient: WebChromeClient) {
        webView.webChromeClient = webChromeClient
    }

    override fun setWebViewClient(webView: WebView, webViewClient: WebViewClient) {
        webView.webViewClient = webViewClient
    }
}