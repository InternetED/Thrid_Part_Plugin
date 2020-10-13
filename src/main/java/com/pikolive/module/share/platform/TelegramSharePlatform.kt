package com.pikolive.module.share.platform

import android.content.Context

/**
 * Creator: ED
 * Date: 2020/10/12 5:48 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class TelegramSharePlatform : SharePlatform {
    override fun shareContent(context: Context, content: String) {

        val intent = createShareIntent("org.telegram.messenger", content)

        startShareIntent(context, intent, "Telegram")
    }
}