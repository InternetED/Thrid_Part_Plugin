package com.pikolive.module.webview

import android.util.Log
import android.webkit.WebView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import java.lang.ref.WeakReference

/**
 * Creator: ED
 * Date: 2020/11/16 3:23 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class WebViewLifecycleObserver(webView: WebView) : LifecycleObserver {
    private val weakWebView = WeakReference(webView)

    private val webView: WebView?
        get() = weakWebView.get()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.d("asmdoasmdoa" ,"onResume:$webView")
        webView?.apply {
            onResume()
            resumeTimers()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {

        Log.d("asmdoasmdoa" ,"onPause:$webView")
        webView?.apply {
            onPause()
            pauseTimers()
        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {

        Log.d("asmdoasmdoa" ,"onDestroy:$webView")
        webView?.apply {
            this.resumeTimers()
            val context = webView?.context
            if (context is LifecycleOwner) {
                context.lifecycle.removeObserver(this@WebViewLifecycleObserver)
            }
        }
        WebUtils.clearWebView(webView)
    }
}