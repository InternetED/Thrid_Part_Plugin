package com.pikolive.module.webview.video

import android.app.Activity
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.view.View
import android.view.WindowManager
import android.webkit.WebChromeClient.CustomViewCallback
import android.webkit.WebView
import android.widget.FrameLayout
import com.pikolive.module.webview.event.EventInterceptor
import java.util.*

class VideoImpl(
    private var mActivity: Activity,
    private var mWebView: WebView
) : IVideo, EventInterceptor {


    private var mFlags: MutableSet<Pair<Int, Int>> = HashSet()
    private var mMovieView: View? = null
    private var mMovieParentView: FrameLayout? = null
    private var mCallback: CustomViewCallback? = null

    override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {

        if (mActivity.isFinishing || view == null || callback == null) {
            return
        }
        mActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        val mWindow = mActivity.window
        val mPair: Pair<Int, Int>?
        // 保存当前屏幕的状态
        if (mWindow.attributes.flags and WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON == 0) {
            mPair = Pair(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, 0)
            mWindow.setFlags(
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
            )
            mFlags.add(mPair)
        }

        if (mMovieView != null) {
            callback.onCustomViewHidden()
            return
        }
        mWebView.visibility = View.GONE

        if (mMovieParentView == null) {
            val mDecorView = mActivity.window.decorView as FrameLayout
            mMovieParentView = FrameLayout(mActivity)
            mMovieParentView!!.setBackgroundColor(Color.BLACK)
            mDecorView.addView(mMovieParentView)
        }
        mCallback = callback
        mMovieParentView!!.addView(view.also { mMovieView = it })
        mMovieParentView!!.visibility = View.VISIBLE
    }

    override fun onHideCustomView() {
        if (mMovieView == null) {
            return
        }
        if (mActivity.requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            mActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        if (mFlags.isNotEmpty()) {
            for ((first, second) in mFlags) {
                mActivity.window.setFlags(second, first)
            }
            mFlags.clear()
        }
        mMovieView!!.visibility = View.GONE
        if (mMovieParentView != null && mMovieView != null) {
            mMovieParentView!!.removeView(mMovieView)
        }
        if (mMovieParentView != null) {
            mMovieParentView!!.visibility = View.GONE
        }
        if (mCallback != null) {
            mCallback!!.onCustomViewHidden()
        }
        mMovieView = null
        mWebView.visibility = View.VISIBLE
    }

    override fun isVideoState(): Boolean {
        return mMovieView != null
    }

    override fun event(): Boolean {
        return if (isVideoState()) {
            onHideCustomView()
            true
        } else {
            false
        }
    }
}