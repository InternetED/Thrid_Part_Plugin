package com.pikolive.module.share.platform

import android.content.ClipData
import android.content.Context
import com.internet.boy.androidbase.kutils.clipboardManager
import com.internet.boy.androidbase.toast

/**
 * Creator: ED
 * Date: 2020/10/13 12:12 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class CopyLinkSharePlatform : SharePlatform {
    override fun shareContent(context: Context, content: String) {

        context.clipboardManager?.apply {
            val clipData = ClipData.newPlainText(null, content)
            setPrimaryClip(clipData)
        }
        context.toast("複製成功")

    }
}