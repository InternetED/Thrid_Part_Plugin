package com.pikolive.module.webview.container

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

/**
 * Creator: ED
 * Date: 2020/11/16 4:39 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class WebViewParentLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    @LayoutRes
    private var errorLayoutRes: Int? = null

    @IdRes
    private var errorChildRes: Int? = null

    private var mErrorLayout: FrameLayout? = null


    fun setErrorLayoutRes(@LayoutRes resLayout: Int) {
        this.errorLayoutRes = resLayout
    }

    fun setErrorReloadChildViewId(@IdRes resId: Int) {
        this.errorChildRes = resId
    }


    fun showPageMainFrameError() {
//        val container = this.mErrorLayout ?: run {
//            createErrorLayout()
//        }
//
//        container.visibility = VISIBLE
//
//
//        val view = if (errorChildRes != null) {
//            container.findViewById<View>(errorChildRes!!)
//        } else {
//            container
//        }
//        view.isClickable = true

    }

    private fun createErrorLayout(): FrameLayout {
        val mFrameLayout = FrameLayout(context)
        mFrameLayout.setBackgroundColor(Color.WHITE)
        mFrameLayout.id = View.generateViewId()

        LayoutInflater.from(context).inflate(errorLayoutRes!!, mFrameLayout, true)

//        val mViewStub = findViewById<View>(R.id.mainframe_error_viewsub_id) as ViewStub
//        val index = indexOfChild(mViewStub)
//        removeViewInLayout(mViewStub)
        val layoutParams = layoutParams
        this.addView(mFrameLayout, layoutParams)
        mFrameLayout.visibility = VISIBLE


        if (errorChildRes != null) {
            val clickView = mFrameLayout.findViewById<View>(errorChildRes!!)
            if (clickView != null) {
                clickView.setOnClickListener {
//                    if (getWebView() != null) {
                    clickView.isClickable = false
//                        getWebView().reload()
//                    }
                }
                return mFrameLayout
            } else {
//                if (LogUtils.isDebug()) {
//                    LogUtils.e(
//                        WebParentLayout.TAG,
//                        "ClickView is null , cannot bind accurate view to refresh or reload ."
//                    )
//                }
            }
        }
        mFrameLayout.setOnClickListener {
//            if (getWebView() != null) {
            mFrameLayout.isClickable = false
//                getWebView().reload()
//            }
        }

        return mFrameLayout
    }
}