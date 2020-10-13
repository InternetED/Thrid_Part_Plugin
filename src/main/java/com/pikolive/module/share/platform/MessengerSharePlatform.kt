package com.pikolive.module.share.platform

import android.content.Context


/**
 * Creator: ED
 * Date: 2020/10/12 5:48 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class MessengerSharePlatform : SharePlatform {
    override fun shareContent(context: Context, content: String) {

        val intent = createShareIntent("com.facebook.orca", content)

        startShareIntent(context, intent, "Messenger")

    }
}