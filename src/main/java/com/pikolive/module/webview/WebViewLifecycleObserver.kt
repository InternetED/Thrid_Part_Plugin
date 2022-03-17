package com.pikolive.module.webview

import android.util.Log
import android.webkit.WebView
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.lang.ref.WeakReference

/**
 * Creator: ED
 * Date: 2020/11/16 3:23 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class WebViewLifecycleObserver(webView: WebView) : DefaultLifecycleObserver {
    private val TAG = javaClass.simpleName

    private val weakWebView = WeakReference(webView)

    private val webView: WebView?
        get() = weakWebView.get()

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)

        Log.d(TAG, "onResume")
        webView?.apply {
            onResume()
            resumeTimers()
        }
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)

        Log.d(TAG, "onPause")
        webView?.apply {
            onPause()
            pauseTimers()
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)

        Log.d(TAG, "onDestroy")
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