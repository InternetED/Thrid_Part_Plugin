package com.pikolive.module.webview.event

import android.view.KeyEvent
import android.webkit.WebView

/**
 * Creator: ED
 * Date: 2020/11/17 10:41 AM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class EventHandlerImpl(
    private val mWebView: WebView?,
    private val mEventInterceptor: EventInterceptor
) : IEventHandler {




    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            back()
        } else false
    }

    override fun back(): Boolean {
        if (mEventInterceptor.event()) {
            return true
        }
        if (mWebView != null && mWebView.canGoBack()) {
            mWebView.goBack()
            return true
        }
        return false
    }

    companion object {
        fun getInstance(
            view: WebView?,
            eventInterceptor: EventInterceptor
        ): EventHandlerImpl {
            return EventHandlerImpl(view, eventInterceptor)
        }
    }

}
