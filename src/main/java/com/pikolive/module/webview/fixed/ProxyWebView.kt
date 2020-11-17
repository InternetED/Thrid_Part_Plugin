package com.pikolive.module.webview.fixed

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.util.Pair
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import com.pikolive.module.webview.WebViewLifecycleObserver

/**
 * Creator: ED
 * Date: 2020/11/16 2:29 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
open class ProxyWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LollipopFixedWebView(context, attrs, defStyleAttr) {


    override fun setOverScrollMode(mode: Int) {
        try {
            super.setOverScrollMode(mode)
        } catch (e: Throwable) {
            val pair: Pair<Boolean, String> = isWebViewPackageException(e)
            if (pair.first) {
                Toast.makeText(context, pair.second, Toast.LENGTH_SHORT).show()
                destroy()
            } else {
                throw e
            }
        }
    }

    private fun isWebViewPackageException(e: Throwable): Pair<Boolean, String> {
        val messageCause = if (e.cause == null) e.toString() else e.cause.toString()
        val trace = Log.getStackTraceString(e)
        if (trace.contains("android.content.pm.PackageManager\$NameNotFoundException")
            || trace.contains("java.lang.RuntimeException: Cannot load WebView")
            || trace.contains("android.webkit.WebViewFactory\$MissingWebViewPackageException: Failed to load WebView provider: No WebView installed")
        ) {
            return Pair(true, "WebView load failed, $messageCause")
        }
        return Pair(false, messageCause)
    }


    override fun destroy() {
        visibility = View.GONE

        removeAllViewsInLayout()
        fixedStillAttached()

        super.destroy()
    }

    // Activity在onDestory时调用webView的destroy，可以停止播放页面中的音频
    private fun fixedStillAttached() {
        // java.lang.Throwable: Error: WebView.destroy() called while still attached!
        // at android.webkit.WebViewClassic.destroy(WebViewClassic.java:4142)
        // at android.webkit.WebView.destroy(WebView.java:707)
        val parent = parent
        if (parent is ViewGroup) { // 由于自定义webView构建时传入了该Activity的context对象，因此需要先从父容器中移除webView，然后再销毁webView；
            val mWebViewContainer = getParent() as ViewGroup
            mWebViewContainer.removeAllViewsInLayout()
        }
    }


}