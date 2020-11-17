package com.pikolive.module.webview.settings

import android.webkit.WebSettings
import android.webkit.WebView

/**
 * Creator: ED
 * Date: 2020/11/17 2:02 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
interface WebSettingManager {

    fun toSetting(webView: WebView): WebSettings

}