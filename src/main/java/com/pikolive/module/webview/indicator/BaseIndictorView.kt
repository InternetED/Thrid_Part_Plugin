package com.pikolive.module.webview.indicator

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Creator: ED
 * Date: 2020/11/16 3:48 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
abstract class BaseIndicatorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), BaseIndicatorSpec {

    override fun reset() {}
    override fun setProgress(newProgress: Int) {}
    override fun show() {}
    override fun hide() {}
}