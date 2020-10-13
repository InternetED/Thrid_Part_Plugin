package com.pikolive.module.share.platform

import android.content.Context
import com.internet.boy.androidbase.kutils.share

/**
 * Creator: ED
 * Date: 2020/10/13 12:12 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class OtherSharePlatform : SharePlatform {
    override fun shareContent(context: Context, content: String) {

        context.share(content)

    }
}